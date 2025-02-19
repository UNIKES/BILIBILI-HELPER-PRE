package top.misec.task;

import com.google.gson.Gson;

import lombok.extern.log4j.Log4j2;
import top.misec.KeyValueClass;
import top.misec.config.ConfigLoader;
import top.misec.login.ServerVerify;
import top.misec.login.Verify;
import top.misec.utils.VersionInfo;

/**
 * @author Junzhou Liu
 * @create 2021/1/15 23:16
 */
@Log4j2
public class UnitTest {

    public static void main(String[] args) {
        if (args.length < 3) {
            log.info("任务启动失败");
            log.warn("Cookies参数缺失，请检查是否在Github Secrets中配置Cookies参数");
        }
        //读取环境变量
        Verify.verifyInit(args[0], args[1], args[2]);

        if (args.length > 4) {
            ServerVerify.verifyInit(args[3], args[4]);
        } else if (args.length > 3) {
            ServerVerify.verifyInit(args[3]);
        }

        VersionInfo.printVersionInfo();
        //每日任务65经验

        //初始化配置
        ConfigLoader.configInit();

        KeyValueClass kv = new Gson().fromJson(ConfigLoader.getDefaultConfig(), KeyValueClass.class);


        ServerPush.doServerPush();


    }
}
