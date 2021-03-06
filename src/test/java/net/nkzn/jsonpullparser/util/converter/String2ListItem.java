package net.nkzn.jsonpullparser.util.converter;

import java.util.List;

import net.nkzn.jsonpullparser.util.converter.String2ListConverter;
import net.vvakame.util.jsonpullparser.annotation.JsonKey;
import net.vvakame.util.jsonpullparser.annotation.JsonModel;

@JsonModel(decamelize=true)
public class String2ListItem {

	@JsonKey(converter=String2ListConverter.class)
	List<String> valueList1;
	
	@JsonKey(converter=String2ListConverter.class)
	List<String> valueList2;
	
	@JsonKey
	String valueSingle;

	public List<String> getValueList1() {
		return valueList1;
	}

	public void setValueList1(List<String> valueList) {
		this.valueList1 = valueList;
	}

	public List<String> getValueList2() {
		return valueList2;
	}

	public void setValueList2(List<String> valueList2) {
		this.valueList2 = valueList2;
	}

	public String getValueSingle() {
		return valueSingle;
	}

	public void setValueSingle(String valueSingle) {
		this.valueSingle = valueSingle;
	}
}
