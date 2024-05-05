# JXM

**JXM (Java XOPSManipulator)** は[X operations](https://hp.vector.co.jp/authors/VA022962/xops/)に関連するデータを操作するためのJavaライブラリです。

## 必要環境

Java ≥ 17

## インストール

### Maven

#### BD1モジュール

BD1モジュールを使うとX operationsのBD1形式を扱うことができます。

```xml
<groupId>com.github.dabasan</groupId>
<artifactId>jxm-bd1</artifactId>
<version>2.2.0-SNAPSHOT</version>
```

#### MIFモジュール

MIFモジュールを使うとX operationsのMIF形式を扱うことができます。

```xml
<groupId>com.github.dabasan</groupId>
<artifactId>jxm-mif</artifactId>
<version>2.2.0-SNAPSHOT</version>
```

#### PD1モジュール

PD1モジュールを使うとX operationsのPD1形式を扱うことができます。

```xml
<groupId>com.github.dabasan</groupId>
<artifactId>jxm-pd1</artifactId>
<version>2.2.0-SNAPSHOT</version>
```

#### Propertiesモジュール

Propertiesモジュールを使うと武器およびキャラクターデータを扱うことができます。
これにはXMS (X operations Mod Supporter)でサポートされる形式、OpenXOPS、および複数バージョンのX operations実行ファイルが含まれます。

```xml
<groupId>com.github.dabasan</groupId>
<artifactId>jxm-properties</artifactId>
<version>2.2.0-SNAPSHOT</version>
```

## 使用例

要更新

## サンプルコード

- [jxm-samples-v2](https://github.com/maeda6uiui/jxm-samples-v2)
- [jxm-samples-v1](https://github.com/maeda6uiui/jxm-samples-v1)

## テストの実行方法

Mavenが利用できる環境であれば、`mvn test`でテストを実行できます。

テストに必要なファイルは各モジュールのTestDataフォルダに配置します。
Gitで管理していないファイルについては、開発者側でテストを実行する前に別途用意していただく必要があります。
以下に、別途用意する必要のあるファイルを列挙します。

### BD1モジュール

- ./jxm-bd1/TestData/SnowBase/map.bd1
- ./jxm-bd1/TestData/SnowBase/map_2.bd1

map.bd1はXOPSの標準マップであるSNOW BASE (data/map2/temp.bd1)を使用します。
map_2.bd1はmap.bd1と違うマップなら何でも構いません。

### PD1モジュール

- ./jxm-pd1/TestData/SnowBase/point.pd1
- ./jxm-pd1/TestData/SnowBase/point_2.pd1

point.pd1はXOPSの標準ミッションであるSNOW BASE_EXT (data/map2/ext.pd1)を使用します。
point_2.pd1はpoint.pd1と違うポイントデータなら何でも構いません。

### Propertiesモジュール

- ./jxm-properties/TestData/Character/xops0975t.exe
- ./jxm-properties/TestData/Weapon/xops0975t.exe
- ./jxm-properties/TestData/XOPS/Original/xops096.exe
- ./jxm-properties/TestData/XOPS/Original/xops096t.exe
- ./jxm-properties/TestData/XOPS/Original/xops097ft.exe
- ./jxm-properties/TestData/XOPS/Original/xops0975t.exe
- ./jxm-properties/TestData/XOPS/Original/xopsolt18f2.exe
- ./jxm-properties/TestData/XOPS/Original/xopsolt19f2.exe

