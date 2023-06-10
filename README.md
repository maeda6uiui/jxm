# JXM

**JXM (Java XOPSManipulator)** is a Java library that enables you to work with the data relating to X operations.

---

**JXM (Java XOPSManipulator)** はX operationsに関連するデータを操作するためのJavaライブラリです。

## 必要環境(Requirements)

Java ≥ 11

## インストール(Installation)

### Maven

#### BD1モジュール(BD1 module)

BD1 module enables you to work with the BD1 format of X operations.

BD1モジュールを使うとX operationsのBD1形式を扱うことができます。

```xml

<groupId>com.github.dabasan</groupId>
<artifactId>jxm-bd1</artifactId>
<version>2.0.0</version>
```

#### MIFモジュール(MIF module)

MIF module enables you to work with the MIF format of X operations.

MIFモジュールを使うとX operationsのMIF形式を扱うことができます。

```xml

<groupId>com.github.dabasan</groupId>
<artifactId>jxm-mif</artifactId>
<version>2.0.0</version>
```

#### PD1モジュール(PD1 module)

PD1 module enables you to work with the PD1 format of X operations.

PD1モジュールを使うとX operationsのPD1形式を扱うことができます。

```xml

<groupId>com.github.dabasan</groupId>
<artifactId>jxm-pd1</artifactId>
<version>2.0.0</version>
```

#### Propertiesモジュール(Properties module)

Properties module enables you to work with weapon and character data, including formats supported in XMS (X operations
Mod Supporter), OpenXOPS, and multiple versions of EXE files of X operations itself.

Propertiesモジュールを使うと武器およびキャラクターデータを扱うことができます。
これにはXMS (X operations Mod Supporter)でサポートされる形式、OpenXOPS、および複数バージョンのX operations実行ファイルが含まれます。

```xml

<groupId>com.github.dabasan</groupId>
<artifactId>jxm-properties</artifactId>
<version>2.0.0</version>
```

## 使用例(Example)

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

## サンプルコード(Code samples)

- [jxm-samples-v2](https://github.com/maeda6uiui/jxm-samples-v2)
- [jxm-samples-v1](https://github.com/maeda6uiui/jxm-samples-v1)

## JXMを使用したプログラム(Applications using JXM)

- [BD1ToOBJ](https://github.com/maeda6uiui/BD1ToOBJ)
  BD1形式のモデルをOBJ形式に変換します。
- [MPSY](https://github.com/maeda6uiui/MPSY)
  XOPSのマップとポイントに対して拡大や回転などの処理を行います。

