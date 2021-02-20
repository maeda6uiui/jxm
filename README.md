# jxm

**JXM (Java XOPSManipulator)**はX operationsに関連するデータを操作するためのJavaライブラリです。

# 必要環境

Java ≥ 11

# インストール

## Maven

### BD1モジュール

```xml
<groupId>com.github.dabasan</groupId>
<artifactId>jxm-bd1</artifactId>
<version>0.0.1</version>
```

### MIFモジュール

```xml
<groupId>com.github.dabasan</groupId>
<artifactId>jxm-mif</artifactId>
<version>0.0.1</version>
```

### PD1モジュール

```xml
<groupId>com.github.dabasan</groupId>
<artifactId>jxm-pd1</artifactId>
<version>0.0.1</version>
```

### Propertiesモジュール

```xml
<groupId>com.github.dabasan</groupId>
<artifactId>jxm-properties</artifactId>
<version>0.0.1</version>
```

# 使用例

```java
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.github.dabasan.jxm.bd1.BD1Block;
import com.github.dabasan.jxm.bd1.BD1Manipulator;

public class BD1ManipulatorSample {
	public static void main(String[] args) {
		// BD1ファイルを読み込む。
		BD1Manipulator manipulator;
		try {
			manipulator = new BD1Manipulator("./Data/map.bd1");
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}

		// ブロックの数を取得する。
		int numBlocks = manipulator.getNumBlocks();
		System.out.println(numBlocks);

		// テクスチャのファイル名をすべて取得する。
		Map<Integer, String> textureFilenames = manipulator.getTextureFilenames();
		for (var entry : textureFilenames.entrySet()) {
			System.out.printf("%d: %s\n", entry.getKey(), entry.getValue());
		}

		// テクスチャのファイル名を変更する。
		manipulator.setTextureFilename(0, "test.bmp");

		// マップを操作する。
		// ここでは、移動→Y軸回りの回転→スケールの変更
		manipulator.translate(0.0, 100.0, 0.0).rotY(Math.PI / 4.0).rescale(1.0, 2.0, 1.0);

		// Z軸反転 (鏡像マップの作成)
		manipulator.invertZ();

		// BD1形式で保存する。
		manipulator.saveAsBD1("./Data/map2.bd1");

		// OBJ形式で保存する。
		manipulator.saveAsOBJ("./Data/map2.obj", "./Data/map2.mtl", "map2.mtl", true);
	}
}
```

# サンプルコード

サンプルコードは[jxm-samples](https://github.com/Dabasan/jxm-samples)を参照してください。

