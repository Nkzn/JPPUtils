JPPUtils
========

Utilities for JsonPullParser

## ListAllConverter

あるkeyに対して、string値、string配列のどちらが割り振られるかわからない場合に利用します。

該当のkeyをマッピングする先の `List<String>` へ `@JsonKey(converter=ListAllConverter.class)` を指定することで利用することができます。

各JSON内の値に対するマッピングの挙動は以下の通り。

* string値 -> サイズ１の `ArrayList<String>`
* string配列 -> デフォルトと同じ挙動（のつもり）
* 空配列 -> サイズ０の `ArrayList<String>`（デフォルトと同じ（ｒｙ）
* 該当のkeyが存在しない -> `null`