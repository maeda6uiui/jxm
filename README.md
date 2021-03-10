# JXM

**JXM (Java XOPSManipulator)** はX operationsに関連するデータを操作するためのJavaライブラリです。

## 必要環境

Java ≥ 11

## インストール

### Maven

#### BD1モジュール

```xml
<groupId>com.github.dabasan</groupId>
<artifactId>jxm-bd1</artifactId>
<version>1.0.0</version>
```

#### MIFモジュール

```xml
<groupId>com.github.dabasan</groupId>
<artifactId>jxm-mif</artifactId>
<version>1.0.0</version>
```

#### PD1モジュール

```xml
<groupId>com.github.dabasan</groupId>
<artifactId>jxm-pd1</artifactId>
<version>1.0.0</version>
```

#### Propertiesモジュール

```xml
<groupId>com.github.dabasan</groupId>
<artifactId>jxm-properties</artifactId>
<version>1.0.0</version>
```

## 使用例

```java
import java.io.IOException;

import com.github.dabasan.jxm.bd1.BD1Manipulator;

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
        manipulator.translate(0.0f, 100.0f, 0.0f).rotY((float) Math.toRadians(45)).rescale(1.0f,
                2.0f, 1.0f);

        // Z軸反転(鏡像マップの作成)
        manipulator.invertZ();

        // BD1形式で保存する
        manipulator.saveAsBD1("./Data/map_2.bd1");

        // OBJ形式で保存する
        manipulator.saveAsOBJ("./Data/map_2.obj", "./Data/map_2.mtl", "map_2.mtl", true);
    }
}
```

## サンプルコード

サンプルコードは[jxm-samples-v1](https://github.com/Dabasan/jxm-samples-v1)を参照してください。

## JXMを使用したプログラム

- [BD1ToOBJ](https://github.com/Dabasan/BD1ToOBJ)
  BD1形式のモデルをOBJ形式に変換します。
- [MPSY](https://github.com/Dabasan/MPSY)
  XOPSのマップとポイントに対して拡大や回転などの処理を行います。

