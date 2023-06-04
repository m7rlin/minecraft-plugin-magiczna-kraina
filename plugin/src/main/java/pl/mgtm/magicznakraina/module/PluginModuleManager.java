package pl.mgtm.magicznakraina.module;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

public class PluginModuleManager {
    private Map<Class<?>, Object> modules;

    public PluginModuleManager() {
        modules = new HashMap<>();
    }

    public <T> void registerModule(Class<T> moduleInterface, T moduleImplementation) {
        modules.put(moduleInterface, moduleImplementation);
    }

    @SuppressWarnings("unchecked")
    public <T> T getModule(Class<T> moduleInterface) {
        if (!modules.containsKey(moduleInterface)) {
            throw new IllegalStateException("Module not registered: " + moduleInterface.getName());
        }

        return (T) modules.get(moduleInterface);
    }

    public <T> T createModuleProxy(Class<T> moduleInterface) {
        return moduleInterface.cast(
                Proxy.newProxyInstance(
                        moduleInterface.getClassLoader(),
                        new Class[]{moduleInterface},
                        new ModuleInvocationHandler(moduleInterface)
                )
        );
    }

    private class ModuleInvocationHandler implements InvocationHandler {
        private Class<?> moduleInterface;

        public ModuleInvocationHandler(Class<?> moduleInterface) {
            this.moduleInterface = moduleInterface;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (!modules.containsKey(moduleInterface)) {
                throw new IllegalStateException("Module not registered: " + moduleInterface.getName());
            }

            Object module = modules.get(moduleInterface);
            return method.invoke(module, args);
        }
    }
}