package net.nkzn.jsonpullparser.util.converter;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import net.vvakame.util.jsonpullparser.JsonFormatException;
import net.vvakame.util.jsonpullparser.JsonPullParser;
import net.vvakame.util.jsonpullparser.JsonPullParser.State;
import net.vvakame.util.jsonpullparser.util.JsonUtil;
import net.vvakame.util.jsonpullparser.util.OnJsonObjectAddListener;
import net.vvakame.util.jsonpullparser.util.TokenConverter;
import static net.vvakame.util.jsonpullparser.util.JsonUtil.*;

public class String2ListConverter extends TokenConverter<List<String>> {

	static String2ListConverter conv = null;
	
	public static String2ListConverter getInstance() {
		if(conv == null) {
			conv = new String2ListConverter();
		}
		return conv;
	}
	
	@Override
	public List<String> parse(JsonPullParser parser,
			OnJsonObjectAddListener listener) throws IOException,
			JsonFormatException {

		switch (parser.getEventType()) {
		case VALUE_NULL:
			return null;
		case VALUE_STRING:
			List<String> listForStrValue = new ArrayList<String>();			
			listForStrValue.add(parser.getValueString());
			return listForStrValue;
		case START_ARRAY:
			List<String> list = parse(parser, new ArrayList<String>());
			if(listener != null) {
				listener.onAdd(list);
			}
			return list;
		default:
			throw new IllegalStateException();
		}
		
	}
	
	List<String> parse(JsonPullParser parser,
			ArrayList<String> list) throws IOException, JsonFormatException {

		State state;
		while((state = parser.lookAhead()) != State.END_ARRAY) {
			switch (state) {
			case VALUE_STRING:
				parser.getEventType();
				list.add(parser.getValueString());
				break;
			default:
				throw new IllegalStateException();
			}
		}
		parser.getEventType();
		
		return list;
	}

	@Override
	public void encodeNullToNull(Writer writer, List<String> obj)
			throws IOException {

		if(obj == null) {
			writer.write("null");
			return;
		}
		
		startArray(writer);
		
		int size = obj.size();
		for(int i = 0; i < size; i++) {
			JsonUtil.put(writer, obj.get(i));
			if(i + 1 < size) {
				addSeparator(writer);
			}
		}
		
		endArray(writer);
	}
}
