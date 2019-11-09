package dev.jorgecastillo.androidcolorx.library

import android.graphics.Color
import androidx.annotation.ColorInt
import androidx.annotation.NonNull
import androidx.core.graphics.ColorUtils
import androidx.core.graphics.ColorUtils.HSLToColor

/**
 * HSLA stands for hue-saturation-lightness-alpha.
 *
 * Hue is a value from 0...360
 * Saturation is a value from 0...1
 * Lightness is a value from 0...1
 * Alpha is a value from 0...1
 */
data class HSLAColor(
    val hue: Float,
    val saturation: Float,
    val lightness: Float,
    val alpha: Float
) {
    override fun toString(): String {
        return "${String.format("%.2f", hue)}º / " +
                "${String.format("%.2f", saturation)} / " +
                "${String.format("%.2f", lightness)} / " +
                String.format("%.2f", alpha)
    }

    override fun equals(other: Any?): Boolean {
        return other is HSLAColor && this.toString() == other.toString()
    }
}

@NonNull
fun @receiver:ColorInt Int.asHSLA(): HSLAColor = this.let { color ->
    FloatArray(3).apply { ColorUtils.colorToHSL(color, this) }.let {
        HSLAColor(it[0], it[1], it[2], Color.alpha(color) / 255f)
    }
}

fun HSLAColor.asFloatArray(): FloatArray = floatArrayOf(hue, saturation, lightness, alpha)

@ColorInt
fun HSLAColor.asColorInt(): Int =
    HSLToColor(asFloatArray().dropLast(1).toFloatArray()).let {
        Color.argb((alpha * 255).toInt(), Color.red(it), Color.green(it), Color.blue(it))
    }

fun HSLAColor.asRgb(): RGBColor = asColorInt().asRGB()

fun HSLAColor.asArgb(): ARGBColor = asColorInt().asArgb()

fun HSLAColor.asCmyk(): CMYKColor = asColorInt().asCmyk()

fun HSLAColor.asHex(): HEXColor = asColorInt().asHex()

fun HSLAColor.asHsl(): HSLColor = asColorInt().asHSL()

/**
 * @param value amount to lighten in the range 0...1
 */
fun HSLAColor.lighten(value: Float): HSLAColor = this.asColorInt().lighten(value).asHSLA()

/**
 * @param value amount to lighten in the range 0...100
 */
fun HSLAColor.lighten(value: Int): HSLAColor = this.asColorInt().lighten(value).asHSLA()

/**
 * @param value amount to darken in the range 0...1
 */
fun HSLAColor.darken(value: Float): HSLAColor = this.asColorInt().darken(value).asHSLA()

/**
 * @param value amount to darken in the range 0...100
 */
fun HSLAColor.darken(value: Int): HSLAColor = this.asColorInt().darken(value).asHSLA()

/**
 * @return a list of shades for the given color like the ones in https://www.color-hex.com/color/e91e63.
 * Each one of the colors is a HSLColor.
 */
fun HSLAColor.getShades(): List<HSLAColor> = asColorInt().getShades().map { it.asHSLA() }

/**
 * @return a list of tints for the given color like the ones in https://www.color-hex.com/color/e91e63.
 * Each one of the colors is a HSLColor.
 */
fun HSLAColor.getTints(): List<HSLAColor> = asColorInt().getTints().map { it.asHSLA() }

/**
 * The Hue is the colour's position on the colour wheel, expressed in degrees from 0° to 359°, representing the 360° of
 * the wheel; 0° being red, 180° being red's opposite colour cyan, and so on. The complimentary color stands for the
 * color in the opposite side of the circle, so it's (hue + 180) % 360.
 */
fun HSLAColor.complimentary(): HSLAColor = asColorInt().complimentary().asHSLA()

/**
 * The Hue is the colour's position on the colour wheel, expressed in degrees from 0° to 359°, representing the 360° of
 * the wheel; 0° being red, 180° being red's opposite colour cyan, and so on. The triadic colors stand for 3 colors that
 * compose a perfect triangle (equal sides) over the circle, so they are equally far from each other.
 *
 * Triadic colors for h0 would be (hue + 120) % 360 and (hue + 240) % 360.
 */
fun HSLAColor.triadic(): Pair<HSLAColor, HSLAColor> =
    asColorInt().triadic().let { Pair(it.first.asHSLA(), it.second.asHSLA()) }

/**
 * The Hue is the colour's position on the colour wheel, expressed in degrees from 0° to 359°, representing the 360° of
 * the wheel; 0° being red, 180° being red's opposite colour cyan, and so on. The tetradic colors stand for 4 colors
 * that compose a perfect square (equal sides) over the circle, so they are equally far from each other.
 *
 * Tetradic colors for h0 would be (hue + 90) % 360, (hue + 180) % 360 and (hue + 270) % 360.
 */
fun HSLAColor.tetradic(): Triple<HSLAColor, HSLAColor, HSLAColor> =
    asColorInt().tetradic().let { Triple(it.first.asHSLA(), it.second.asHSLA(), it.third.asHSLA()) }

/**
 * The Hue is the colour's position on the colour wheel, expressed in degrees from 0° to 359°, representing the 360° of
 * the wheel; 0° being red, 180° being red's opposite colour cyan, and so on. The analogous colors stand for 3 colors
 * that are together in the circle, separated by 30 degrees each.
 *
 * Analogous colors for h0 would be (hue + 30) % 360 & (hue - 30) % 360.
 */
fun HSLAColor.analogous(): Pair<HSLAColor, HSLAColor> =
    asColorInt().analogous().let { Pair(it.first.asHSLA(), it.second.asHSLA()) }

/**
 * Check if a color is dark (convert to XYZ & check Y component)
 */
fun HSLAColor.isDark(): Boolean = ColorUtils.calculateLuminance(this.asColorInt()) < 0.5
