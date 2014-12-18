import com.peaceful.common.util.Util;

/**
 * Created by wangjun on 14/12/12.
 */
public class Test {

    @org.junit.Test
    public void a(){
        Long i = Long.valueOf("15652636152");
        for (int n=0;n<5000;n++){
            System.out.println(i + n);
        }
    }

}
