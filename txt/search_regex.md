Mark Views without color or style
-------------------------------------------------

find CardView with app:cardBackgroundColor (Android Studio):\
`(\<androidx\.cardview\.widget\.CardView)((\n\s*[^\>]*)*)(app\:cardBackgroundColor[^\>]*)((\n\s*([^\>]*))*)(\>)`

find CardView with style (Android Studio):\
`(\<androidx\.cardview\.widget\.CardView)((\n\s*[^\>]*)*)(style[^\>]*)((\n\s*([^\>]*))*)(\>)`

find MaterialCardView with app:cardBackgroundColor (Android Studio):\
`(\<com\.google\.android\.material\.card\.MaterialCardView)((\n\s*[^\>]*)*)(app\:cardBackgroundColor[^\>]*)((\n\s*([^\>]*))*)(\>)`

find MaterialCardView with style (Android Studio):\
`(\<com\.google\.android\.material\.card\.MaterialCardView)((\n\s*[^\>]*)*)(style[^\>]*)((\n\s*([^\>]*))*)(\>)`


replace:\
`<!--ignore_mark-->$0`

Insert style in unmarked views
-------------------------------------------------

find non marked CardView (Android Studio):\
`^\s*(?!\<\!\-\-ignore_mark\-\-\>)(\<androidx\.cardview\.widget\.CardView)`

find non marked MaterialCardView (Android Studio):\
`^\s*(?!\<\!\-\-ignore_mark\-\-\>)(\<com\.google\.android\.material\.card\.MaterialCardView)`

replace:\
`$0`
style="@style/UiCardView"

Format inserted style
-------------------------------------------------
find inserted styles (VS Code): \
`^style="@style/UiCardView"`
(\s+)android:

replace:\
`$1$0`

Remove ignore_mark
-------------------------------------------------
find ignore_mark (Android Studio):\
`\<\!\-\-ignore_mark\-\-\>`

replace with nothing
