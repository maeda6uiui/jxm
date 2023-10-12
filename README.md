# JXM

**JXM (Java XOPSManipulator)** はX operationsに関連するデータを操作するためのJavaライブラリです。

## 必要環境

Java ≥ 11

## インストール

### Maven

#### BD1モジュール

BD1モジュールを使うとX operationsのBD1形式を扱うことができます。

```xml
<groupId>com.github.dabasan</groupId>
<artifactId>jxm-bd1</artifactId>
<version>2.0.1</version>
```

#### MIFモジュール

MIFモジュールを使うとX operationsのMIF形式を扱うことができます。

```xml
<groupId>com.github.dabasan</groupId>
<artifactId>jxm-mif</artifactId>
<version>2.0.1</version>
```

#### PD1モジュール

PD1モジュールを使うとX operationsのPD1形式を扱うことができます。

```xml
<groupId>com.github.dabasan</groupId>
<artifactId>jxm-pd1</artifactId>
<version>2.0.1</version>
```

#### Propertiesモジュール

Propertiesモジュールを使うと武器およびキャラクターデータを扱うことができます。
これにはXMS (X operations Mod Supporter)でサポートされる形式、OpenXOPS、および複数バージョンのX operations実行ファイルが含まれます。

```xml
<groupId>com.github.dabasan</groupId>
<artifactId>jxm-properties</artifactId>
<version>2.0.1</version>
```

## 使用例

```java
package com.github.maeda6uiui.jxmsamplesv2;

import com.github.dabasan.jxm.bd1.BD1Manipulator;

import java.io.IOException;

/**
 * JXMのReadmeに掲載するサンプルコード
 *
 * @author maeda6uiui
 */
public class ReadmeSample {
    public static void main(String[] args) {
        // BD1ファイルを読み込む
        BD1Manipulator manipulator;
        try {
            manipulator = new BD1Manipulator("./Data/map.bd1");
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        // ブロックの数を取得する
        int numBlocks = manipulator.getNumBlocks();
        System.out.println(numBlocks);

        // テクスチャのファイル名をすべて取得する
        manipulator.getTextureFilenames().forEach((k, v) -> System.out.printf("%d: %s\n", k, v));

        // テクスチャのファイル名を変更する
        manipulator.setTextureFilename(0, "test.bmp");
        manipulator.setTextureFilename(1, "test_2.bmp");

        // マップを操作する
        // ここでは、移動→Y軸回りの回転→スケールの変更
        manipulator
                .translate(0.0f, 100.0f, 0.0f)
                .rotY((float) Math.toRadians(45))
                .rescale(1.0f, 2.0f, 1.0f);

        // Z軸反転(鏡像マップの作成)
        manipulator.invertZ();

        try {
            // BD1形式で保存する
            manipulator.saveAsBD1("./Data/map2.bd1");

            // OBJ形式で保存する
            manipulator.saveAsOBJ(
                    "./Data/map2.obj",
                    "./Data/map2.mtl",
                    "map2.mtl",
                    true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

## サンプルコード

- [jxm-samples-v2](https://github.com/maeda6uiui/jxm-samples-v2)
- [jxm-samples-v1](https://github.com/maeda6uiui/jxm-samples-v1)

## JXMを使用したプログラム

- [BD1ToOBJ](https://github.com/maeda6uiui/BD1ToOBJ)
  BD1形式のモデルをOBJ形式に変換します。
- [MPSY](https://github.com/maeda6uiui/MPSY)
  XOPSのマップとポイントに対して拡大や回転などの処理を行います。

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

