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
	
	public int codePointAt(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index > str.length) {
			throw new IndexOutOfBoundsException();
		}
		if (str[index] < 0) {
			return str[index] & 0xFF;
		} else {
			return str[index];
		}
	}
	
	public int codePointBefore(int index) throws IndexOutOfBoundsException {
		if (index < 1 || index > str.length) {
			throw new IndexOutOfBoundsException();
		}
		if (str[index - 1] < 0) {
			return str[index - 1] & 0xFF;
		} else {
			return str[index - 1];
		}
	}

	public String8Bit concat(String str) {
		byte[] concatStr = new byte[this.str.length + str.length()];
		System.arraycopy(this.str, 0, concatStr, 0, this.str.length);
		for (int i = this.str.length; i < this.str.length + str.length(); i++) {
			concatStr[i] = (byte) str.charAt(i - this.str.length);
		}
		return new String8Bit(concatStr);
	}

	public String8Bit concat(String8Bit str) {
		byte[] concatStr = new byte[this.str.length + str.length()];
		System.arraycopy(this.str, 0, concatStr, 0, this.str.length);
		System.arraycopy(str.str, 0, concatStr, this.str.length, str.length());
		return new String8Bit(concatStr);
	}
	
	public static String8Bit copyValueOf(char[] data) {
		return new String8Bit(data);
	}
	
	public static String8Bit copyValueOf(char [] data, int offset, int count) throws IndexOutOfBoundsException {
		if (offset < 0) {
			throw new IndexOutOfBoundsException("Offset is negative.");
		}
		if (count < 0) {
			throw new IndexOutOfBoundsException("Count is negative.");
		}
		if (offset + count > data.length) {
			throw new IndexOutOfBoundsException("Offset + count is larger than data.length.");
		}
		char[] subStr = new char[count];
		System.arraycopy(data, offset, subStr, 0, count);
		return new String8Bit(subStr);
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
		byte[] strByte = new byte[str.length];
		System.arraycopy(str, 0, strByte, 0, this.str.length);
		return strByte;
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
	
	public int indexOf(int ch, int fromIndex) {
		if (ch >= 256 || ch < 0) {
			return -1;
		}
		for (int i = fromIndex; i < str.length; i++) {
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
	
	public int lastIndexOf(int ch, int fromIndex) {
		if (ch >= 256 || ch < 0) {
			return -1;
		}
		for (int i = fromIndex; i >= 0; i--) {
			if (ch == (str[i] & 0xFF)) {
				return i;
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
		char[] strChar = new char[str.length];
		for (int i = 0; i < str.length; i++) {
			if (str[i] < 0) {
				strChar[i] = (char) (str[i] & 0xFF);
			} else {
				strChar[i] = (char) str[i];
			}
		}
		return strChar;
	}
	
	public String8Bit toLowerCase() {
		byte[] lowerStr = new byte[str.length];
		for (int i = 0; i < str.length; i++) {
			if (str[i] >= 65 && str[i] <= 90) {
				lowerStr[i] = (byte) (str[i] + 32);
			} else {
				lowerStr[i] = str[i];
			}
		}
		return new String8Bit(lowerStr);
	}

	public String toString() {
		return new String(str);
	}
	
	public String8Bit toUpperCase() {
		byte[] upperStr = new byte[str.length];
		for (int i = 0; i < str.length; i++) {
			if (str[i] >= 97 && str[i] <= 122) {
				upperStr[i] = (byte) (str[i] - 32);
			} else {
				upperStr[i] = str[i];
			}
		}
		return new String8Bit(upperStr);
	}
	
	public static String8Bit valueOf(boolean b) {
		return b ? new String8Bit(new byte[] {'t','r','u','e'}) : new String8Bit(new byte[] {'f','a','l','s','e'});
	}
	
	public static String8Bit valueOf(char c) {
		return new String8Bit(new byte[] {(byte) c});
	}
	
	public static String8Bit valueOf(char[] data) {
		return new String8Bit(data);
	}
	
	public static String8Bit valueOf(char[] data, int offset, int count) throws IndexOutOfBoundsException {
		if (offset < 0) {
			throw new IndexOutOfBoundsException("Offset is negative.");
		}
		if (count < 0) {
			throw new IndexOutOfBoundsException("Count is negative.");
		}
		if (offset + count > data.length) {
			throw new IndexOutOfBoundsException("Offset + count is greater than data.length");
		}
		char[] subStr = new char[count];
		System.arraycopy(data, offset, subStr, 0, count);
		return new String8Bit(subStr);
	}
	
	public static String8Bit valueOf(int i) {
		int lengthNumber = (int) Math.log10(i) + 1;
		byte[] numberStr = new byte[lengthNumber];
		for (int j = lengthNumber - 1; j >= 0; j--) {
			numberStr[j] = (byte) ((i % 10) + 48);
			i = i / 10;
		}
		return new String8Bit(numberStr);
	}
	
	public static String8Bit valueOf(long l) {
		int lengthNumber = (int) Math.log10(l) + 1;
		byte[] numberStr = new byte[lengthNumber];
		for (int j = lengthNumber - 1; j >= 0; j--) {
			numberStr[j] = (byte) ((l % 10) + 48);
			l = l / 10;
		}
		return new String8Bit(numberStr);
	}
	
	public static String8Bit valueOf(Object obj) {
		return obj == null ? new String8Bit(new byte[] {'n','u','l','l'}) : new String8Bit(obj.toString());
	}
}