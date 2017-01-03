public class String8Bit {
	private byte[] str;

	public String8Bit() {
		str = new byte[0];
	}

	public String8Bit(String str) {
		this.str = str.getBytes();
	}

	public String8Bit(byte[] str) {
		this.str = new byte[str.length];
		System.arraycopy(str, 0, this.str, 0, str.length);
	}

	public String8Bit(char[] str) {
		this.str = new byte[str.length];
		for (int i = 0; i < str.length; i++) {
			this.str[i] = (byte) str[i];
		}
	}
	
	public String8Bit(String8Bit original) {
		this.str = new byte[original.str.length];
		System.arraycopy(original.str, 0, this.str, 0, original.str.length);
	}

	public char charAt(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index > str.length) {
			throw new IndexOutOfBoundsException("Admirable but mistaken.");
		}
		if (str[index] < 0) {
			return (char) (str[index] & 0xFF);
		} else {
			return (char) str[index];
		}
	}

	public String8Bit concat(String str) {
		byte[] concatstr = new byte[this.str.length + str.length()];
		System.arraycopy(this.str, 0, concatstr, 0, this.str.length);
		for (int i = this.str.length; i < this.str.length + str.length(); i++) {
			concatstr[i] = (byte) str.charAt(i - this.str.length);
		}
		return new String8Bit(concatstr);
	}

	public String8Bit concat(String8Bit str) {
		byte[] concatstr = new byte[this.str.length + str.length()];
		System.arraycopy(this.str, 0, concatstr, 0, this.str.length);
		System.arraycopy(str.str, 0, concatstr, this.str.length, str.length());
		return new String8Bit(concatstr);
	}
	
	public boolean equals(String8Bit anObject) {
		if (anObject instanceof String8Bit) {
			if (this.str.length != anObject.str.length) {
				return false;
			}
			for (int i = 0; i < this.str.length; i++) {
				if (this.str[i] != anObject.str[i]) {
					return false;
				}
			}
			return true;
		} else {
			return false;
		}
	}
	
	public byte[] getBytes() {
		byte[] strbyte = new byte[str.length];
		System.arraycopy(str, 0, strbyte, 0, this.str.length);
		return strbyte;
	}
	
	public void getChars(int srcBegin, int srcEnd, char[] dst, int dstBegin) throws IndexOutOfBoundsException {
		for (int i = srcBegin; i <= srcEnd; i++) {
			try {
				dst[dstBegin + i - srcBegin] = (char) str[i];
			} catch(IndexOutOfBoundsException e) {
				throw new IndexOutOfBoundsException("Indexes are not correct.");
			}
		}
	}
	
	public int hashCode() {
		int hash = 0;
		for (int i = 0; i < str.length; i++) {
			hash = hash + str[i] * ((int) Math.pow(31, str.length - i + 1)); 
		}
		hash = hash + str[str.length - 1];
		return hash;
	}
	
	public int indexOf(int ch) {
		if (ch >= 256 || ch < 0) {
			return -1;
		}
		for (int i = 0; i < str.length; i++) {
			if (str[i] < 0) {
				if (ch == (str[i] & 0xFF)) {
					return i;
				}
			} else {
				if (ch == str[i]) {
					return i;
				}
			}
		}
		return -1;
	}
	
	public boolean isEmpty() {
		return (str.length == 0) ? true : false;
	}
	
	public int lastIndexOf(int ch) {
		if (ch >= 256 || ch < 0) {
			return -1;
		}
		for (int i = str.length - 1; i >= 0; i--) {
			if (str[i] < 0) {
				if (ch == (str[i] & 0xFF)) {
					return i;
				}
			} else {
				if (ch == str[i]) {
					return i;
				}
			} 
		}
		return -1;
	}
	
	public int length() {
		return str.length;
	}
	
	public char[] toCharArray() {
		char[] strchar = new char[str.length];
		for (int i = 0; i < str.length; i++) {
			if (str[i] < 0) {
				strchar[i] = (char) (str[i] & 0xFF);
			} else {
				strchar[i] = (char) str[i];
			}
		}
		return strchar;
	}

	public String toString() {
		return new String(str);
	}
	
	public static String8Bit valueOf(boolean b) {
		return b ? new String8Bit("true") : new String8Bit("false");
	}
}