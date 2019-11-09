AndroidColorX [![CircleCI](https://circleci.com/gh/JorgeCastilloPrz/AndroidColorX/tree/master.svg?style=svg&circle-token=5cd5f9a1d941936290fde62a8321f9bf9d60f2c5)](https://circleci.com/gh/JorgeCastilloPrz/AndroidColorX/tree/master)
======

AndroidColorX (i.e: Android Color Extensions) is an Android library written in Kotlin that provides color utilities as [Kotlin extension functions](https://kotlinlang.org/docs/tutorials/kotlin-for-py/extension-functionsproperties.html). The library relies on AndroidX [`ColorUtils`](https://developer.android.com/reference/kotlin/androidx/core/graphics/ColorUtils) for some of its calculations.


### Color conversion

This library provides seamless conversion between the following color types:

* `android.graphics.Color (ColorInt)`
* `RGBColor`
* `ARGBColor`
* `HEXColor`
* `HSLColor`
* `CMYKColor`

To convert a color type to any of the other types, you can use the extensions provided for it.

```kotlin
val color = Color.RED

val rgb = color.asRgb()
val argb = color.asArgb()
val hex = color.asHex()
val hsl = color.asHsl()
val cmyk = color.asCmyk()

val colorHsl = HSLColor(hue = 210, saturation = 0.5, lightness = 0.5)

val colorInt = colorHsl.asColorInt()
val rgb = colorHsl.asRgb()
val argb = colorHsl.asArgb()
val hex = colorHsl.asHex()
val cmyk = colorHsl.asCmyk()
```

The same extensions **are available for all the mentioned color types**.

### Shades and tints

If you're aware of webs like [Color-Hex](https://www.color-hex.com/color/6dc066) you've probably seen those shades and tints palettes. AndroidColorX provides extensions for calculating those:

<img src="./assets/shades_and_tints.gif" width="300px"/>

Here's the code for all the color types available:

```kotlin

val color = Color.parseColor("#e91e63")

// shades
val shades: List<Int> = color.getShades()
val shadesHsl: List<HSLColor> = color.asHsl().getShades()
val shadesCmyk: List<CMYKColor> = color.asCmyk().getShades()
val shadesHex: List<HEXColor> = color.asHex().getShades()
val shadesRgb: List<RGBColor> = color.asRgb().getShades()
val shadesArgb: List<ARGBColor> = color.asArgb().getShades()

// tints
val tints: List<Int> = color.getTints()
val tintsHsl: List<HSLColor> = color.asHsl().getTints()
val tintsCmyk: List<CMYKColor> = color.asCmyk().getTints()
val tintsHex: List<HEXColor> = color.asHex().getTints()
val tintsRgb: List<RGBColor> = color.asRgb().getTints()
val tintsArgb: List<ARGBColor> = color.asArgb().getTints()
```

As you can see, these extensions **are available for all the mentioned color types**.

License
-------

    Copyright 2019 Jorge Castillo Pérez

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.


