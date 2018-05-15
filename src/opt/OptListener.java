package opt;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.Date;

import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef.HMODULE;
import com.sun.jna.platform.win32.WinDef.LRESULT;
import com.sun.jna.platform.win32.WinDef.WPARAM;
import com.sun.jna.platform.win32.WinUser;
import com.sun.jna.platform.win32.WinUser.HHOOK;
import com.sun.jna.platform.win32.WinUser.KBDLLHOOKSTRUCT;
import com.sun.jna.platform.win32.WinUser.LowLevelKeyboardProc;
import com.sun.jna.platform.win32.WinUser.MSG;
import com.sun.jna.platform.win32.WinUser.POINT;

import opt.WinUser.LowLevelMouseProc;
import opt.WinUser.MOUSEHOOKSTRUCT;

public class OptListener implements Runnable{

	public Thread t;

	public boolean copy1,copy2,paste1;

	public boolean enableHotKey,enableCaptrue;

	public static HHOOK mouseHHK, keyboardHHK;
    public static LowLevelMouseProc mouseHook;
    public static LowLevelKeyboardProc keyboardHook;

	static void setHook() {
        HMODULE hMod = Kernel32.INSTANCE.GetModuleHandle(null);
        mouseHHK = User32.INSTANCE.SetWindowsHookEx(WinUser.WH_MOUSE_LL, mouseHook, hMod, 0);
        keyboardHHK = User32.INSTANCE.SetWindowsHookEx(WinUser.WH_KEYBOARD_LL, keyboardHook, hMod, 0);
    }

    static void unhook() {
        User32.INSTANCE.UnhookWindowsHookEx(keyboardHHK);
        User32.INSTANCE.UnhookWindowsHookEx(mouseHHK);
    }

    public void startHotKey() {

    	enableHotKey = true;

    	if (t == null) {
    		t = new Thread (this,"OptListener");
    		t.start ();
    	}

    }

    public void stopHotKey() {

    	enableHotKey = false;

    }

    public void startCaptrue() {

    	enableCaptrue = true;

    	if (t == null) {
    		t = new Thread (this,"OptListener");
    		t.start ();
    	}

    }

    public void stopCaptrue() {

    	enableCaptrue = false;

    }

	@Override
	public void run() {

		keyboardHook = new LowLevelKeyboardProc() {

            @Override
            public LRESULT callback(int nCode, WPARAM wParam, KBDLLHOOKSTRUCT lParam) {

            	if(enableHotKey == true){
            		changeHotKey(wParam.intValue(),lParam.vkCode);
            	}

                return User32.INSTANCE.CallNextHookEx(keyboardHHK, nCode, wParam, lParam.getPointer());
            }


        };

        mouseHook = new LowLevelMouseProc() {

        	//int[] memo = new int[5];

        	Date[] memo = new Date[2];

			@Override
	         public LRESULT callback(int nCode, WPARAM wParam, MOUSEHOOKSTRUCT lParam) {

				if(enableHotKey == true){
            		changeHotKey(memo,wParam.intValue(),lParam.pt);
				}

//	        	 if (mn.jb2.isVisible()) {
//	        		 //‰Ÿ‚·513@•ú‚·514
//	        		 if(wParam.intValue() != 512){
//		        		 System.out.println("š" + wParam.intValue() + "š");
//				         System.out.println("(" + lParam.pt.x + "," + lParam.pt.y + ")");
//
//				         JOptionPane.showConfirmDialog(mn, "(" + lParam.pt.x + "," + lParam.pt.y + ")", "À•W", JOptionPane.PLAIN_MESSAGE);
//	        		 }
//
//	        	 }

		         return User32.INSTANCE.CallNextHookEx(mouseHHK, nCode, wParam, lParam.getPointer());
	         }

        };

        setHook();
        int result = 0;
        MSG msg = new MSG();
        while ( (result = User32.INSTANCE.GetMessage(msg, null, 0, 0)) != 0) {

            if (result == -1) {
                System.err.println("error in GetMessage");
                unhook();
                break;
            } else{
                User32.INSTANCE.TranslateMessage(msg);
                User32.INSTANCE.DispatchMessage(msg);
            }

        }

        unhook();

	}

	private void changeHotKey(Date[] memo, int mouseOpt, POINT pt) {

		if(copy2 == true){

			// ‰Ÿ‚·
			if(mouseOpt == 513){
				memo[0] = new Date();
				memo[1] = null;
			}

			// •ú‚·
			if(mouseOpt == 514 && memo[0] != null){

				memo[1] = new Date();
				long a = memo[1].getTime() - memo[0].getTime();

				if(a > 500){

					try {
						Robot rbt = new Robot();
						rbt.delay(200);
						rbt.keyPress(KeyEvent.VK_CONTROL);
						rbt.keyPress(KeyEvent.VK_C);

						rbt.keyRelease(KeyEvent.VK_C);
						rbt.keyRelease(KeyEvent.VK_CONTROL);
					} catch (AWTException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

			}
		}

	}

	private void changeHotKey(int keyOpt, int vkCode) {

		if(copy1 == true){

    		if (keyOpt == WinUser.WM_SYSKEYDOWN && (vkCode == 164)) {

				try {
					Robot rbt = new Robot();
					rbt.delay(200);
					rbt.keyPress(KeyEvent.VK_CONTROL);
					rbt.keyPress(KeyEvent.VK_C);

					rbt.keyRelease(KeyEvent.VK_C);
					rbt.keyRelease(KeyEvent.VK_CONTROL);
				} catch (AWTException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

    		}

		}

		if(paste1 == true){

    		if (keyOpt == WinUser.WM_KEYDOWN && (vkCode == 29 || vkCode == 235)) {

				try {
					Robot rbt = new Robot();
					rbt.delay(200);
					rbt.keyPress(KeyEvent.VK_CONTROL);
					rbt.keyPress(KeyEvent.VK_V);

					rbt.keyRelease(KeyEvent.VK_V);
					rbt.keyRelease(KeyEvent.VK_CONTROL);
				} catch (AWTException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

    		}

		}

	}

}


