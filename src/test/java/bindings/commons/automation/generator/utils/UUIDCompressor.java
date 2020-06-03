package bindings.commons.automation.generator.utils;

import java.util.UUID;

public class UUIDCompressor {

  private static final String SEPARATOR = "-";

  private UUIDCompressor() {
  }

  public static String compressUUID(String uuid) {
    final UUID u = UUID.fromString(uuid);
    long most = u.getMostSignificantBits();
    long least = u.getLeastSignificantBits();
    return Long.toUnsignedString(most, Character.MAX_RADIX) + SEPARATOR
        + Long.toUnsignedString(least, Character.MAX_RADIX);
  }

  public static String deCompressUUID(String compressedUUID) {
    String[] uuidValues = compressedUUID.split(SEPARATOR);
    return new UUID(Long.parseUnsignedLong(uuidValues[0], Character.MAX_RADIX),
        Long.parseUnsignedLong(uuidValues[1], Character.MAX_RADIX)).toString();
  }

}
