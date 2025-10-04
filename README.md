<!-- @formatter:off -->

# JXM

**JXM (Java XOPSManipulator)** is a Java library to handle data related to [X operations](https://hp.vector.co.jp/authors/VA022962/xops/).

## Requirements

Java ≥ 17

## Installation

### BD1 module

Supports operations of the BD1 format.

```xml
<dependencies>
    <dependency>
        <groupId>io.github.maeda6uiui</groupId>
        <artifactId>jxm-bd1</artifactId>
        <version>3.0.0</version>
    </dependency>
</dependencies>
```

### MIF module

Supports operations of the MIF format.

```xml
<dependencies>
    <dependency>
        <groupId>io.github.maeda6uiui</groupId>
        <artifactId>jxm-mif</artifactId>
        <version>3.0.0</version>
    </dependency>
</dependencies>
```

### PD1 module

Supports operations of the PD1 format.

```xml
<dependencies>
    <dependency>
        <groupId>io.github.maeda6uiui</groupId>
        <artifactId>jxm-pd1</artifactId>
        <version>3.0.0</version>
    </dependency>
</dependencies>
```

### Properties module

Supports operations of

- XMS (X operations Mod Supporter)
    - IDS (Individual weapon data)
    - XGS (Set of all weapon data)
    - XCS (Set of all character data)
- OpenXOPS source code format
    - Weapon data
    - Character data
- X operations binaries
    - 0.96
    - 0.96t
    - 0.97ft
    - 0.975t
    - olt18f2
    - olt19f2

```xml
<dependencies>
    <dependency>
        <groupId>io.github.maeda6uiui</groupId>
        <artifactId>jxm-properties</artifactId>
        <version>3.0.0</version>
    </dependency>
</dependencies>
```

## Code samples

### Older versions

- [jxm-samples-v2](https://github.com/maeda6uiui/jxm-samples-v2)
- [jxm-samples-v1](https://github.com/maeda6uiui/jxm-samples-v1)

## For developers

### How to run tests

You can run tests with `mvn test` if you have Maven installed.

Place required files under the `TestData` folder of each module before executing tests.
Some files are not available on GitHub due to copyright reasons.
Listed below is the list of files you have to prepare by yourself:

#### BD1 module

- ./jxm-bd1/TestData/SnowBase/map.bd1
- ./jxm-bd1/TestData/SnowBase/map_2.bd1
- ./jxm-bd1/TestData/TrainingYard/map.bd1

`SnowBase/map.bd1` is a standard map file of SNOW BASE (from `data/map2/temp.bd1`).
`SnowBase/map_2.bd1` can be any map file as long as it is different from `SnowBase/map.bd1`.

`TrainingYard/map.bd1` is a standard map file of TRAINING YARD (from `data/map0/temp.bd1`).

#### PD1 module

- ./jxm-pd1/TestData/SnowBase/point.pd1
- ./jxm-pd1/TestData/SnowBase/point_2.pd1

`point.pd1` is a standard point file of SNOW BASE_EXT (from `data/map2/ext.pd1`).
`point_2.pd1` can be any point file as long it is different from `point.pd1`.

#### Properties module

- ./jxm-properties/TestData/Character/xops0975t.exe
- ./jxm-properties/TestData/Weapon/xops0975t.exe
- ./jxm-properties/TestData/XOPS/Original/xops096.exe
- ./jxm-properties/TestData/XOPS/Original/xops096t.exe
- ./jxm-properties/TestData/XOPS/Original/xops097ft.exe
- ./jxm-properties/TestData/XOPS/Original/xops0975t.exe
- ./jxm-properties/TestData/XOPS/Original/xopsolt18f2.exe
- ./jxm-properties/TestData/XOPS/Original/xopsolt19f2.exe

