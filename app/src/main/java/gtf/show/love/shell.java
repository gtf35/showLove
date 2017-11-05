package gtf.show.love;

/**
 * Created by acer on 2017/6/6.
 */

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class shell {
    public static class CommandResult {
        public String errorMsg;
        public int result;
        public String successMsg;

        public CommandResult(int i) {
            result = i;
        }

        public CommandResult(int i, String s, String s1) {
            result = i;
            successMsg = s;
            errorMsg = s1;
        }
    }

    public static final String COMMAND_EXIT = "exit\n";
    public static final String COMMAND_LINE_END = "\n";
    public static final String COMMAND_SH = "sh";
    public static final String COMMAND_SU = "su";

    public static boolean checkRootPermission() {
        return execCommand("echo root", true, false).result == 0;
    }

    public static CommandResult execCommand(List<String> list, boolean flag) {
        String as[];
        if (list == null) as = null;
        else as = list.toArray(new String[0]);
        return execCommand(as, flag, true);
    }

    public static CommandResult execCommand(List<String> list, boolean flag, boolean flag1) {
        String as[];
        if (list == null) as = null;
        else as = list.toArray(new String[0]);
        return execCommand(as, flag, flag1);
    }

    public static CommandResult execCommand(String s, boolean flag) {
        return execCommand(new String[]{s}, flag, true);
    }

    public static CommandResult execCommand(String as[], boolean flag) {
        return execCommand(as, flag, true);
    }

    public static CommandResult execCommand(String s, boolean flag, boolean flag1) {
        return execCommand(new String[]{s}, flag, flag1);
    }

    public static CommandResult execCommand(String commands[], boolean useRoot, boolean recordOutput) {
        if (commands == null || commands.length == 0) return new CommandResult(-1, null, null);
        BufferedReader inputStreamReader = null;
        BufferedReader errorStreamReader = null;
        Process process = null;
        DataOutputStream outputStream = null;
        String shell = useRoot ? "su" : "sh";
        StringBuilder successMsg = new StringBuilder();
        StringBuilder errorMsg = new StringBuilder();
        int result = 0;
        try {
            process = Runtime.getRuntime().exec(shell);
            outputStream = new DataOutputStream(process.getOutputStream());
            int j = 0;
            while (j < commands.length) {
                String s3 = commands[j];
                if (s3 != null) {
                    outputStream.write(s3.getBytes());
                    outputStream.writeBytes("\n");
                    outputStream.flush();
                }
                j++;
            }
            outputStream.writeBytes("exit\n");
            outputStream.flush();
            result = process.waitFor();
            if (recordOutput) {
                inputStreamReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                errorStreamReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
                String s4;
                while ((s4 = inputStreamReader.readLine()) != null) {
                    successMsg.append(s4 + "\n");
                }
                String s5;
                while ((s5 = errorStreamReader.readLine()) != null) {
                    errorMsg.append(s5 + "\n");
                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        //_L11:
        if (outputStream != null) try {
				outputStream.close();
			} catch (IOException e2) {
				e2.printStackTrace();
			}
        if (inputStreamReader != null) try {
				inputStreamReader.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
        if (errorStreamReader != null) try {
				errorStreamReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
        if (process != null) process.destroy();
        return new CommandResult(result, successMsg.toString(), errorMsg.toString());
    }

    shell() {
        throw new AssertionError();
    }
}

/*   public static String 无root执行脚本(String 执行内容) {
        shell.CommandResult 输出值 = shell.execCommand(执行内容, false, true);
        输出 = 输出值.successMsg;
        return 输出;
    }

    public static String 需要root执行脚本(String 执行内容) {
        shell.CommandResult 输出值 = shell.execCommand(执行内容, true, true);
        输出 = 输出值.successMsg;
        return 输出;
    }
	*/
