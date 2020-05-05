package nz.net.osnz.common.jackson;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

/**
 * This is a static helper utility class for quickly serialising and de-serialising JAXB-mapped structures. Uses a
 * Jackson {@link com.fasterxml.jackson.core.JsonFactory} to perform the operations.
 */
public class JacksonHelper {

  private static ObjectMapper mapper;

  static {
    JsonFactory factory = new JsonFactory();
    mapper = new ObjectMapper(factory)
      .registerModule(new JavaTimeModule());  // Register Module for Java 8 Time

    mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    mapper.disable(MapperFeature.AUTO_DETECT_IS_GETTERS);
    mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

  }

  /**
   * Serialises a provided Object to JSON.
   *
   * @param dataToSerialize The Object to serialise.
   * @return The provided Object structure as a JSON String.
   * @throws JacksonException
   */
  @SuppressWarnings("unused")
  public static String serialize(Object dataToSerialize) throws JacksonException {
    try {
      return mapper.writeValueAsString(dataToSerialize);
    } catch (Exception e) {
      throw new JacksonException(e);
    }
  }

  /**
   * Constructs an Object from JSON String data.
   *
   * @param text The JSON String to parse.
   * @param type The Class of the Object to construct.
   * @param <T>  The type of Object to construct.
   * @return The constructed Object if the JSON String input parses correctly.
   * @throws JacksonException
   */
  @SuppressWarnings("unused")
  public static <T> T deserialize(String text, Class<T> type) throws JacksonException {
    try {
      return mapper.readValue(text, type);
    } catch (Exception e) {
      throw new JacksonException(e);
    }
  }

}
