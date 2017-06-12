package blatella.Algorithms.Common;

public enum EnumLanguage implements java.io.Serializable
{
	undetermined(0)
	,
	english(1)
	,
	castilian(2)
	,
	french(3)
	,
	portuguese(4)
	,
	german(5)
	,
	italian(6)
	,
	catalan(7)
	,
	dutch(8)
	,
	latin(9);
	     
	private final int id;
	EnumLanguage(int id) { this.id = id; }
	public int getValue() { return id; }
}
