package net.nkzn.jsonpullparser.util.listall;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

import net.vvakame.util.jsonpullparser.JsonFormatException;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;

import static org.junit.Assert.*; 

public class String2ListConverterTest {

	/**
	 * 配列がListへマッピングされる
	 * value_single:string
	 * @throws IOException
	 * @throws JsonFormatException
	 */
	@Test
	public void arrayOnly() throws IOException, JsonFormatException {
		String json = "{\"value_list1\":[\"hoge1\",\"fuga1\"],\"value_list2\":[\"hoge2\",\"fuga2\"],\"value_single\":\"piyo\"}";
		
		// JSON->POJO
		Item item = ItemGen.get(json);
		
		List<String> valueList1 = item.getValueList1();
		assertThat(valueList1.get(0), is("hoge1"));
		assertThat(valueList1.get(1), is("fuga1"));

		List<String> valueList2 = item.getValueList2();
		assertThat(valueList2.get(0), is("hoge2"));
		assertThat(valueList2.get(1), is("fuga2"));		
		
		assertThat(item.getValueSingle(), is("piyo"));
		
		// POJO->JSON
		// 「めんどいからもっかい復号しておｋだったらおｋ」方式
		StringWriter writer = new StringWriter();
		ItemGen.encode(writer, item);
		json = writer.toString();
		
		// JSON->POJO
		item = ItemGen.get(json);
		
		valueList1 = item.getValueList1();
		assertThat(valueList1.get(0), is("hoge1"));
		assertThat(valueList1.get(1), is("fuga1"));

		valueList2 = item.getValueList2();
		assertThat(valueList2.get(0), is("hoge2"));
		assertThat(valueList2.get(1), is("fuga2"));		
		
		assertThat(item.getValueSingle(), is("piyo"));

	}
	
	/**
	 * 単一のstring値がListへマッピングされる
	 * @throws IOException
	 * @throws JsonFormatException
	 */
	@Test
	public void valueOnly() throws IOException, JsonFormatException {
		String json = "{\"value_list1\":\"hoge1\",\"value_list2\":\"fuga2\",\"value_single\":\"piyo\"}";

		// JSON->POJO
		Item item = ItemGen.get(json);
		
		List<String> valueList1 = item.getValueList1();
		assertThat(valueList1.size(), is(1));
		assertThat(valueList1.get(0), is("hoge1"));

		List<String> valueList2 = item.getValueList2();
		assertThat(valueList2.size(), is(1));
		assertThat(valueList2.get(0), is("fuga2"));		
		
		assertThat(item.getValueSingle(), is("piyo"));

		// POJO->JSON
		// 「めんどいからもっかい復号しておｋだったらおｋ」方式
		StringWriter writer = new StringWriter();
		ItemGen.encode(writer, item);
		json = writer.toString();

		// JSON->POJO
		item = ItemGen.get(json);
		
		valueList1 = item.getValueList1();
		assertThat(valueList1.size(), is(1));
		assertThat(valueList1.get(0), is("hoge1"));

		valueList2 = item.getValueList2();
		assertThat(valueList2.size(), is(1));
		assertThat(valueList2.get(0), is("fuga2"));		
		
		assertThat(item.getValueSingle(), is("piyo"));
		
	}

	/**
	 * 配列とstring値がそれぞれListへマッピングされる
	 * @throws IOException
	 * @throws JsonFormatException
	 */
	@Test
	public void mix() throws IOException, JsonFormatException {
		String json = "{\"value_list1\":[\"hoge1\",\"fuga1\"],\"value_list2\":\"fuga2\",\"value_single\":\"piyo\"}";
		
		// JSON->POJO
		Item item = ItemGen.get(json);
		
		List<String> valueList1 = item.getValueList1();
		assertThat(valueList1.get(0), is("hoge1"));
		assertThat(valueList1.get(1), is("fuga1"));

		List<String> valueList2 = item.getValueList2();
		assertThat(valueList2.size(), is(1));
		assertThat(valueList2.get(0), is("fuga2"));
		
		assertThat(item.getValueSingle(), is("piyo"));

		// POJO->JSON
		// 「めんどいからもっかい復号しておｋだったらおｋ」方式
		StringWriter writer = new StringWriter();
		ItemGen.encode(writer, item);
		json = writer.toString();

		// JSON->POJO
		item = ItemGen.get(json);
		
		valueList1 = item.getValueList1();
		assertThat(valueList1.get(0), is("hoge1"));
		assertThat(valueList1.get(1), is("fuga1"));

		valueList2 = item.getValueList2();
		assertThat(valueList2.size(), is(1));
		assertThat(valueList2.get(0), is("fuga2"));
		
		assertThat(item.getValueSingle(), is("piyo"));
		
	}
	
	/**
	 * 空の配列が存在する場合
	 * @throws IOException
	 * @throws JsonFormatException
	 */
	@Test
	public void hasEmptyArray() throws IOException, JsonFormatException {
		String json = "{\"value_list1\":[],\"value_list2\":[\"hoge2\",\"fuga2\"],\"value_single\":\"piyo\"}";
		
		// JSON->POJO
		Item item = ItemGen.get(json);
		
		List<String> valueList1 = item.getValueList1();
		assertThat(valueList1.size(), is(0));

		List<String> valueList2 = item.getValueList2();
		assertThat(valueList2.get(0), is("hoge2"));
		assertThat(valueList2.get(1), is("fuga2"));		
		
		assertThat(item.getValueSingle(), is("piyo"));
		
		// POJO->JSON
		// 「めんどいからもっかい復号しておｋだったらおｋ」方式
		StringWriter writer = new StringWriter();
		ItemGen.encode(writer, item);
		json = writer.toString();
		
		// JSON->POJO
		item = ItemGen.get(json);
		
		valueList1 = item.getValueList1();
		assertThat(valueList1.size(), is(0));

		valueList2 = item.getValueList2();
		assertThat(valueList2.get(0), is("hoge2"));
		assertThat(valueList2.get(1), is("fuga2"));		
		
		assertThat(item.getValueSingle(), is("piyo"));

	}
	
	/**
	 * keyが無い場合
	 * @throws IOException
	 * @throws JsonFormatException
	 */
	@Test
	public void hasAbsentKey() throws IOException, JsonFormatException {
		String json = "{\"value_list1\":[\"hoge1\",\"fuga1\"],\"value_single\":\"piyo\"}";
		
		// JSON->POJO
		Item item = ItemGen.get(json);
		
		List<String> valueList1 = item.getValueList1();
		assertThat(valueList1.get(0), is("hoge1"));
		assertThat(valueList1.get(1), is("fuga1"));

		List<String> valueList2 = item.getValueList2();
		assertNull(valueList2);
		
		assertThat(item.getValueSingle(), is("piyo"));
		
		// POJO->JSON
		// 「めんどいからもっかい復号しておｋだったらおｋ」方式
		StringWriter writer = new StringWriter();
		ItemGen.encode(writer, item);
		json = writer.toString();

		// JSON->POJO
		item = ItemGen.get(json);
		
		valueList1 = item.getValueList1();
		assertThat(valueList1.get(0), is("hoge1"));
		assertThat(valueList1.get(1), is("fuga1"));

		valueList2 = item.getValueList2();
		assertNull(valueList2);
		
		assertThat(item.getValueSingle(), is("piyo"));
		
	}
}
