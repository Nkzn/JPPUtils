JPPUtils
========

Utilities for JsonPullParser

## ListAllConverter

あるkeyに対して、string値、string配列のどちらが割り振られるかわからない場合に利用します。

該当のkeyをマッピングする先の変数へ `@JsonKey(converter=ListAllConverter.class)` を指定することで利用することができます。