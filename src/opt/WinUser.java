package opt;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;
import com.sun.jna.platform.win32.BaseTSD.ULONG_PTR;

public interface WinUser extends com.sun.jna.platform.win32.WinUser {

	public interface LowLevelMouseProc extends HOOKPROC {
	    LRESULT callback(int nCode, WPARAM wParam, MOUSEHOOKSTRUCT lParam);


	}

    public class MOUSEHOOKSTRUCT extends Structure{

    	public POINT pt;
    	public HWND hwnd;
    	public int wHitTestCode;
    	public ULONG_PTR dwExtraInfo;

    	protected List<String> getFieldOrder() {
    		return Arrays.asList(new String[] { "pt", "hwnd", "wHitTestCode",
    				"dwExtraInfo" });
    	}

    }
}
