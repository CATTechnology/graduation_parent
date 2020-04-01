import com.alibaba.druid.filter.config.ConfigTools;

/**
 * @author 戴灵飞 dailingfei
 * @version 1.0
 * @date 2020/3/3 21:51
 */
public class PasswdDemo {
    public static void main(String[] args) throws Exception {
        System.out.println(ConfigTools.encrypt("980518"));
        System.out.println(ConfigTools.decrypt("729a698197f772c87deb43c46bd1392be2642cfb"));
    }
}
