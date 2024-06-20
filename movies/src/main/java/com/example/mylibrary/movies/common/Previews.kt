

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.Devices

//----------------------------------------------------------------------------------------------


@PhonePreviews
@TabletPreviews
@TvPreviews
@DesktopPreviews
@WearOsPreviews
@AutomotivePreviews
annotation class AllDeviceType

@PhonePreviews
annotation class AllPhoneType

@TabletPreviews
annotation class AllTabletType

@TvPreviews
annotation class AllTvType

@DesktopPreviews
annotation class AllDesktopType

@WearOsPreviews
annotation class AllWearOsType

@AutomotivePreviews
annotation class AllAutomotiveType


@PhonePreviews
@Preview(name = "Phone", device = Devices.PHONE, showBackground = true)
@Preview(name = "Phone LANDSCAPE",
    device = "spec:width=411dp,height=891dp,orientation=landscape", showBackground = true)
@Preview(name = "Small Phone",
    device = "spec:width=320dp,height=480dp", showBackground = true)
@Preview(name = "XSmall Phone",
    device = "spec:width=280dp,height=420dp", showBackground = true)
@Preview(name = "Medium Phone",
    device = "spec:width=360dp,height=640dp", showBackground = true)
@Preview(name = "Large Phone",
    device = "spec:width=480dp,height=800dp", showBackground = true)
@Preview(name = "Small Phone Landscape",
    device = "spec:width=480dp,height=320dp,orientation=landscape", showBackground = true)
@Preview(name = "XSmall Phone Landscape",
    device = "spec:width=420dp,height=280dp,orientation=landscape", showBackground = true)
@Preview(name = "Medium Phone Landscape",
    device = "spec:width=640dp,height=360dp,orientation=landscape", showBackground = true)
@Preview(name = "Large Phone Landscape",
    device = "spec:width=800dp,height=480dp,orientation=landscape", showBackground = true)
annotation class PhonePreviews



@TabletPreviews
@Preview(name = "TABLET", device = Devices.TABLET, showBackground = true)
@Preview(name = "TABLET PORTRAIT",
    device = "spec:width=1280dp,height=800dp,dpi=240,orientation=portrait", showBackground = true)
annotation class TabletPreviews



@TvPreviews
@Preview(name = "TV", device = Devices.TV_1080p, showBackground = true)
@Preview(name = "TVP ORTRAIT",
    device = "spec:width=1920dp,height=1080dp,orientation=portrait", showBackground = true)
annotation class TvPreviews



@DesktopPreviews
@Preview(name = "DESKTOP", device = Devices.DESKTOP, showBackground = true)
@Preview(name = "DESKTOP PORTRAIT",
    device = "spec:width=1920dp,height=1080dp,dpi=160,orientation=portrait", showBackground = true)
annotation class DesktopPreviews


@WearOsPreviews
@Preview(name = "WEAR OS", device = Devices.WEAR_OS_LARGE_ROUND, showBackground = true)
annotation class WearOsPreviews


@AutomotivePreviews
@Preview(name = "AUTOMOTIVE", device = Devices.AUTOMOTIVE_1024p, showBackground = true)
@Preview(name = "AUTOMOTIVE PORTRAIT",
    device = "spec:parent=automotive_1024p_landscape,orientation=portrait", showBackground = true)
annotation class AutomotivePreviews

//----------------------------------------------------------------------------------------------

//----------------------------------------------------------------------------------------------
// Font Scales
@Preview(name = "Phone", device = Devices.PHONE, showBackground = true, fontScale = 0.75f)
annotation class SmallFontScalePhone

@Preview(name = "Phone", device = Devices.PHONE, showBackground = true, fontScale = 1.0f)
annotation class NormalFontScalePhone

@Preview(name = "Phone", device = Devices.PHONE, showBackground = true, fontScale = 1.25f)
annotation class LargeFontScalePhone

@Preview(name = "Phone", device = Devices.PHONE, showBackground = true, fontScale = 1.5f)
annotation class XLargeFontScalePhone

@Preview(name = "Phone", device = Devices.PHONE, showBackground = true, fontScale = 2.0f)
annotation class XXLargeFontScalePhone

@SmallFontScalePhone
@NormalFontScalePhone
@LargeFontScalePhone
@XLargeFontScalePhone
@XXLargeFontScalePhone
annotation class AllFontScalesPhone

//----------------------------------------------------------------------------------------------


/**
 * @PreviewScreenSizes
 * @Composable
 * fun MyComposablePreview() {
 *     MyComposable()
 * }
 */

/**
@Preview(name = "NEXUS_7", device = Devices.NEXUS_7, showBackground = true)
@Preview(name = "NEXUS_7_2013", device = Devices.NEXUS_7_2013, showBackground = true)
@Preview(name = "NEXUS_5", device = Devices.NEXUS_5, showBackground = true)
@Preview(name = "NEXUS_6", device = Devices.NEXUS_6, showBackground = true)
@Preview(name = "NEXUS_9", device = Devices.NEXUS_9, showBackground = true)
@Preview(name = "NEXUS_10", device = Devices.NEXUS_10, showBackground = true)
@Preview(name = "NEXUS_5X", device = Devices.NEXUS_5X, showBackground = true)
@Preview(name = "NEXUS_6P", device = Devices.NEXUS_6P, showBackground = true)
@Preview(name = "PIXEL_C", device = Devices.PIXEL_C, showBackground = true)
@Preview(name = "PIXEL", device = Devices.PIXEL, showBackground = true)
@Preview(name = "PIXEL_XL", device = Devices.PIXEL_XL, showBackground = true)
@Preview(name = "PIXEL_2", device = Devices.PIXEL_2, showBackground = true)
@Preview(name = "PIXEL_2_XL", device = Devices.PIXEL_2_XL, showBackground = true)
@Preview(name = "PIXEL_3", device = Devices.PIXEL_3, showBackground = true)
@Preview(name = "PIXEL_3_XL", device = Devices.PIXEL_3_XL, showBackground = true)
@Preview(name = "PIXEL_3A", device = Devices.PIXEL_3A, showBackground = true)
@Preview(name = "PIXEL_3A_XL", device = Devices.PIXEL_3A_XL, showBackground = true)
@Preview(name = "PIXEL_4", device = Devices.PIXEL_4, showBackground = true)
@Preview(name = "PIXEL_4_XL", device = Devices.PIXEL_4_XL, showBackground = true)
@Preview(name = "WEAR OS", device = Devices.WEAR_OS_LARGE_ROUND, showBackground = true)
@Preview(name = "Phone", device = Devices.PHONE, showBackground = true)
@Preview(name = "TABLET", device = Devices.TABLET, showBackground = true)
@Preview(name = "DESKTOP", device = Devices.DESKTOP, showBackground = true)
@Preview(name = "AUTOMOTIVE", device = Devices.AUTOMOTIVE_1024p, showBackground = true)
@Preview(name = "TV", device = Devices.TV_1080p, showBackground = true)
@Preview(name = "Phone LANDSCAPE",
device = "spec:width=411dp,height=891dp,orientation=landscape", showBackground = true)
@Preview(name = "TABLET PORTRAIT",
device = "spec:width=1280dp,height=800dp,dpi=240,orientation=portrait", showBackground = true)

@Preview(name = "DESKTOP PORTRAIT",
device = "spec:width=1920dp,height=1080dp,dpi=160,orientation=portrait", showBackground = true)
@Preview(name = "AUTOMOTIVE PORTRAIT",
device = "spec:parent=automotive_1024p_landscape,orientation=portrait", showBackground = true)
@Preview(name = "TVP ORTRAIT",
device = "spec:width=1920dp,height=1080dp,orientation=portrait", showBackground = true)

 */

/*

@AllDeviceType
@Preview(name = "WEAR OS", device = Devices.WEAR_OS_LARGE_ROUND, showBackground = true)
@Preview(name = "Phone", device = Devices.PHONE, showBackground = true)
@Preview(name = "TABLET", device = Devices.TABLET, showBackground = true)
@Preview(name = "DESKTOP", device = Devices.DESKTOP, showBackground = true)
@Preview(name = "AUTOMOTIVE", device = Devices.AUTOMOTIVE_1024p, showBackground = true)
@Preview(name = "TV", device = Devices.TV_1080p, showBackground = true)
@Preview(name = "Phone LANDSCAPE",
    device = "spec:width=411dp,height=891dp,orientation=landscape", showBackground = true)
@Preview(name = "TABLET PORTRAIT",
    device = "spec:width=1280dp,height=800dp,dpi=240,orientation=portrait", showBackground = true)
@Preview(name = "DESKTOP PORTRAIT",
    device = "spec:width=1920dp,height=1080dp,dpi=160,orientation=portrait", showBackground = true)
@Preview(name = "AUTOMOTIVE PORTRAIT",
    device = "spec:parent=automotive_1024p_landscape,orientation=portrait", showBackground = true)
@Preview(name = "TVP ORTRAIT",
    device = "spec:width=1920dp,height=1080dp,orientation=portrait", showBackground = true)
@Preview(name = "Small Phone",
    device = "spec:width=320dp,height=480dp", showBackground = true)
@Preview(name = "XSmall Phone",
    device = "spec:width=280dp,height=420dp", showBackground = true)
@Preview(name = "Medium Phone",
    device = "spec:width=360dp,height=640dp", showBackground = true)
@Preview(name = "Large Phone",
    device = "spec:width=480dp,height=800dp", showBackground = true)
@Preview(name = "Small Phone Landscape",
    device = "spec:width=480dp,height=320dp,orientation=landscape", showBackground = true)
@Preview(name = "XSmall Phone Landscape",
    device = "spec:width=420dp,height=280dp,orientation=landscape", showBackground = true)
@Preview(name = "Medium Phone Landscape",
    device = "spec:width=640dp,height=360dp,orientation=landscape", showBackground = true)
@Preview(name = "Large Phone Landscape",
    device = "spec:width=800dp,height=480dp,orientation=landscape", showBackground = true)

annotation class AllDeviceType

 */



