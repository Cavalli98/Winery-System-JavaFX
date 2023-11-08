package utilities;

import java.util.Objects;

public class Elements {
	private String vals1, vals2;
	private int val;
	
	public Elements(String v1, String v2, int v) {
		vals1 = v1;
		vals2 = v2;
		val = v;
	}
	
	public String getVals1() {
		return vals1;
	}
	
	public void setVals1(String vals1) {
		this.vals1 = vals1;
	}
	
	public String getVals2() {
		return vals2;
	}
	
	public void setVals2(String vals2) {
		this.vals2 = vals2;
	}
	
	public String getVals() {
		return vals1 + " " + vals2;
	}
	
	public int getVal() {
		return val;
	}
	
	public void setVal(int val) {
		this.val = val;
	}
	
	public void incrementVal() {
		val++;
	}

	@Override
	public boolean equals(Object o) {

        if (o == this) return true;
        if (!(o instanceof Elements)) {
            return false;
        }
        Elements el = (Elements) o;
        return Objects.equals(vals1, el.vals1) &&
        	   Objects.equals(vals2, el.vals2);
    }
	
	@Override
    public int hashCode() {
        return Objects.hash(vals1, vals2);
    }
	
	@Override
	public String toString() {
		return vals1 + " " + vals2;
	}
}
