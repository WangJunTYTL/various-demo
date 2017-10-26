import sun.jvm.hotspot.oops.InstanceKlass;
import sun.jvm.hotspot.tools.jcore.ClassFilter;

/**
 * Created by wangjun38 on 2017/8/11.
 */
class MyFilter implements ClassFilter {

    @Override
    public boolean canInclude(InstanceKlass instanceKlass) {
        String klassName = instanceKlass.getName().asString();
        return klassName.startsWith("com/peaceful/cglib/demo");
    }
}
