declare i32 @getint()
declare void @putint(i32)
declare void @putch(i32)
declare void @putstr(i8*)
@q = dso_local global i32 66
@r = dso_local global i32 155
@s = dso_local global i32 15
define dso_local i32 @main(){

B31:
    %ld32 = load i32, i32* @q
    %def130 = alloca i32
    %def131 = alloca i32
    %def132 = alloca i32
    %def133 = alloca i32
    %def134 = alloca i32
    %def135 = alloca i32
    %def136 = alloca i32
    %def137 = alloca i32
    %def138 = alloca i32
    %def139 = alloca i32
    %def140 = alloca i32
    %def141 = alloca i32
    %def142 = alloca i32
    %def143 = alloca i32
    %def144 = alloca i32
    %def145 = alloca i32
    %def146 = alloca i32
    %def147 = alloca i32
    %def148 = alloca i32
    %def149 = alloca i32
    %def150 = alloca i32
    %def151 = alloca i32
    %def152 = alloca i32
    %def153 = alloca i32
    %def154 = alloca i32
    %def155 = alloca i32
    %def156 = alloca i32
    %def157 = alloca i32
    %def158 = alloca i32
    %def159 = alloca i32
    %def160 = alloca i32
    %def161 = alloca i32
    %def162 = alloca i32
    %def163 = alloca i32
    %def164 = alloca i32
    %def165 = alloca i32
    %def166 = alloca i32
    %def167 = alloca i32
    %def168 = alloca i32
    %def169 = alloca i32
    %def170 = alloca i32
    %def171 = alloca i32
    %def172 = alloca i32
    %def173 = alloca i32
    %def174 = alloca i32
    %def175 = alloca i32
    %def176 = alloca i32
    %def177 = alloca i32
    %def178 = alloca i32
    %def179 = alloca i32
    %def180 = alloca i32
    %def181 = alloca i32
    %def182 = alloca i32
    %def183 = alloca i32
    %def184 = alloca i32
    %def185 = alloca i32
    %def186 = alloca i32
    %def187 = alloca i32
    %def188 = alloca i32
    %def189 = alloca i32
    %def190 = alloca i32
    %def191 = alloca i32
    %def192 = alloca i32
    %def193 = alloca i32
    %def194 = alloca i32
    %def195 = alloca i32
    %def196 = alloca i32
    %def197 = alloca i32
    %def198 = alloca i32
    %def199 = alloca i32
    %def200 = alloca i32
    %def201 = alloca i32
    %def202 = alloca i32
    %def203 = alloca i32
    %def204 = alloca i32
    %def205 = alloca i32
    %def206 = alloca i32
    %def207 = alloca i32
    %def208 = alloca i32
    %def209 = alloca i32
    %def210 = alloca i32
    %def211 = alloca i32
    %def212 = alloca i32
    %def213 = alloca i32
    %def214 = alloca i32
    %def215 = alloca i32
    %def216 = alloca i32
    %def217 = alloca i32
    %def218 = alloca i32
    %def219 = alloca i32
    %def220 = alloca i32
    %def221 = alloca i32
    %def222 = alloca i32
    %def223 = alloca i32
    %def224 = alloca i32
    %def225 = alloca i32
    %def226 = alloca i32
    %def227 = alloca i32
    %def228 = alloca i32
    %def229 = alloca i32
    %def230 = alloca i32
    %def231 = alloca i32
    %def232 = alloca i32
    %def233 = alloca i32
    %def234 = alloca i32
    %def235 = alloca i32
    %def236 = alloca i32
    %def237 = alloca i32
    %def238 = alloca i32
    %def239 = alloca i32
    %def240 = alloca i32
    %def241 = alloca i32
    %def242 = alloca i32
    %def243 = alloca i32
    %def244 = alloca i32
    %def245 = alloca i32
    %def246 = alloca i32
    %def247 = alloca i32
    %def248 = alloca i32
    %def249 = alloca i32
    %def250 = alloca i32
    %def251 = alloca i32
    %def252 = alloca i32
    %def253 = alloca i32
    %def254 = alloca i32
    %def255 = alloca i32
    %def256 = alloca i32
    %def257 = alloca i32
    %def258 = alloca i32
    %def259 = alloca i32
    %def260 = alloca i32
    %def261 = alloca i32
    %def262 = alloca i32
    %def263 = alloca i32
    %def264 = alloca i32
    %def265 = alloca i32
    %def266 = alloca i32
    %def267 = alloca i32
    %def268 = alloca i32
    %def269 = alloca i32
    %def270 = alloca i32
    %def271 = alloca i32
    %def272 = alloca i32
    %def273 = alloca i32
    %def274 = alloca i32
    %def275 = alloca i32
    %def276 = alloca i32
    %def277 = alloca i32
    %def278 = alloca i32
    %def279 = alloca i32
    %def280 = alloca i32
    %def281 = alloca i32
    %def282 = alloca i32
    %def283 = alloca i32
    %def284 = alloca i32
    %def285 = alloca i32
    %def286 = alloca i32
    %def287 = alloca i32
    %def288 = alloca i32
    %def289 = alloca i32
    %def290 = alloca i32
    %def291 = alloca i32
    %def292 = alloca i32
    %def293 = alloca i32
    %def294 = alloca i32
    %def295 = alloca i32
    %def296 = alloca i32
    %def297 = alloca i32
    %def298 = alloca i32
    %def299 = alloca i32
    %def300 = alloca i32
    %def301 = alloca i32
    %def302 = alloca i32
    %def303 = alloca i32
    %def304 = alloca i32
    %def305 = alloca i32
    %def306 = alloca i32
    %def307 = alloca i32
    %def308 = alloca i32
    %def309 = alloca i32
    %def310 = alloca i32
    %def311 = alloca i32
    %def312 = alloca i32
    %def313 = alloca i32
    %def314 = alloca i32
    %def315 = alloca i32
    %def316 = alloca i32
    %def317 = alloca i32
    %def318 = alloca i32
    %def319 = alloca i32
    %def320 = alloca i32
    %def321 = alloca i32
    %def322 = alloca i32
    %def323 = alloca i32
    %def324 = alloca i32
    %def325 = alloca i32
    %def326 = alloca i32
    %def327 = alloca i32
    %def328 = alloca i32
    %def329 = alloca i32
    %def330 = alloca i32
    %def331 = alloca i32
    %def332 = alloca i32
    %def333 = alloca i32
    %def334 = alloca i32
    %def335 = alloca i32
    %def336 = alloca i32
    %def337 = alloca i32
    %def338 = alloca i32
    %def339 = alloca i32
    %def340 = alloca i32
    %def341 = alloca i32
    %def342 = alloca i32
    br label %B65

B65:
    br label %B66

B66:
    %cmp23 = icmp slt i32 88, %ld32
    %zot10 = zext i1 %cmp23 to i32
    %cmp24 = icmp ne i32 %zot10, 0
    br i1 %cmp24, label %B67, label %B68

B67:
    store i32 88, i32* %def130
    br label %B64

B68:
    store i32 %ld32, i32* %def130
    br label %B64

B64:
    %ld252 = load i32, i32* %def130
    %ld33 = load i32, i32* @s
    br label %B70

B70:
    %call102 = call i32 @getint()
    %calc13 = add i32 0, %call102
    br label %B69

B69:
    br label %B72

B72:
    br label %B73

B73:
    %cmp25 = icmp sgt i32 %ld33, %calc13
    %zot11 = zext i1 %cmp25 to i32
    %cmp26 = icmp ne i32 %zot11, 0
    br i1 %cmp26, label %B74, label %B75

B74:
    store i32 %ld33, i32* %def131
    br label %B71

B75:
    store i32 %calc13, i32* %def131
    br label %B71

B71:
    %ld253 = load i32, i32* %def131
    br label %B77

B77:
    br label %B78

B78:
    %cmp27 = icmp sgt i32 %ld252, %ld253
    %zot12 = zext i1 %cmp27 to i32
    %cmp28 = icmp ne i32 %zot12, 0
    br i1 %cmp28, label %B79, label %B80

B79:
    store i32 %ld252, i32* %def132
    br label %B76

B80:
    store i32 %ld253, i32* %def132
    br label %B76

B76:
    %ld254 = load i32, i32* %def132
    %ld34 = load i32, i32* @r
    br label %B82

B82:
    %call103 = call i32 @getint()
    %calc14 = add i32 0, %call103
    br label %B81

B81:
    br label %B84

B84:
    br label %B85

B85:
    %cmp29 = icmp slt i32 %ld34, %calc14
    %zot13 = zext i1 %cmp29 to i32
    %cmp30 = icmp ne i32 %zot13, 0
    br i1 %cmp30, label %B86, label %B87

B86:
    store i32 %ld34, i32* %def133
    br label %B83

B87:
    store i32 %calc14, i32* %def133
    br label %B83

B83:
    %ld255 = load i32, i32* %def133
    call void @putch(i32 49)
    call void @putch(i32 57)
    call void @putch(i32 51)
    call void @putch(i32 55)
    call void @putch(i32 51)
    call void @putch(i32 48)
    call void @putch(i32 50)
    call void @putch(i32 50)
    call void @putch(i32 10)
    call void @putint(i32 %ld254)
    call void @putch(i32 44)
    call void @putint(i32 %ld255)
    call void @putch(i32 10)
    %calc3 = add i32 %ld255, 58
    %calc4 = sub i32 %calc3, %ld254
    call void @putch(i32 98)
    call void @putch(i32 58)
    call void @putint(i32 %calc4)
    call void @putch(i32 10)
    br label %B32

B32:
    %cmp9 = icmp eq i32 0, 0
    %zot3 = zext i1 %cmp9 to i32
    %cmp10 = icmp ne i32 %zot3, 0
    br i1 %cmp10, label %B33, label %B35

B33:
    call void @putch(i32 66)
    call void @putch(i32 111)
    call void @putch(i32 111)
    call void @putch(i32 108)
    call void @putch(i32 49)
    call void @putch(i32 32)
    call void @putch(i32 105)
    call void @putch(i32 115)
    call void @putch(i32 32)
    call void @putch(i32 102)
    call void @putch(i32 97)
    call void @putch(i32 108)
    call void @putch(i32 115)
    call void @putch(i32 101)
    call void @putch(i32 33)
    call void @putch(i32 10)
    br label %B35

B35:
    br label %B89

B89:
    %call104 = call i32 @getint()
    %calc15 = add i32 0, %call104
    br label %B88

B88:
    %calc5 = add i32 10, %calc15
    br label %B91

B91:
    call void @putint(i32 %calc5)
    call void @putch(i32 10)
    br label %B90

B90:
    br label %B93

B93:
    %call107 = call i32 @getint()
    %calc16 = add i32 0, %call107
    br label %B92

B92:
    br label %B95

B95:
    %call108 = call i32 @getint()
    %calc17 = add i32 0, %call108
    br label %B94

B94:
    br label %B97

B97:
    %call109 = call i32 @getint()
    %calc18 = add i32 0, %call109
    br label %B96

B96:
    br label %B36

B36:
    br label %B99

B99:
    br label %B100

B100:
    br label %B101

B101:
    br label %B102

B102:
    %cmp31 = icmp sgt i32 %calc16, %calc17
    %zot14 = zext i1 %cmp31 to i32
    %cmp32 = icmp ne i32 %zot14, 0
    br i1 %cmp32, label %B103, label %B104

B103:
    store i32 %calc16, i32* %def134
    br label %B105

B104:
    store i32 %calc17, i32* %def134
    br label %B105

B105:
    %ld256 = load i32, i32* %def134
    br label %B106

B106:
    br label %B107

B107:
    %cmp33 = icmp slt i32 %calc17, %calc18
    %zot15 = zext i1 %cmp33 to i32
    %cmp34 = icmp ne i32 %zot15, 0
    br i1 %cmp34, label %B108, label %B109

B108:
    store i32 %calc17, i32* %def135
    br label %B110

B109:
    store i32 %calc18, i32* %def135
    br label %B110

B110:
    %ld257 = load i32, i32* %def135
    %cmp35 = icmp eq i32 %ld256, %ld257
    br i1 %cmp35, label %B111, label %B112

B111:
    store i32 0, i32* %def138
    store i32 0, i32* %def139
    store i32 %calc17, i32* %def140
    store i32 0, i32* %def141
    store i32 0, i32* %def142
    store i32 0, i32* %def143
    store i32 0, i32* %def144
    br label %B98

B112:
    br label %B113

B113:
    br label %B114

B114:
    %cmp36 = icmp sgt i32 %calc16, %calc17
    %zot16 = zext i1 %cmp36 to i32
    %cmp37 = icmp ne i32 %zot16, 0
    br i1 %cmp37, label %B115, label %B116

B115:
    store i32 %calc16, i32* %def136
    br label %B117

B116:
    store i32 %calc17, i32* %def136
    br label %B117

B117:
    %ld258 = load i32, i32* %def136
    br label %B118

B118:
    br label %B119

B119:
    %cmp38 = icmp slt i32 %calc16, %calc18
    %zot17 = zext i1 %cmp38 to i32
    %cmp39 = icmp ne i32 %zot17, 0
    br i1 %cmp39, label %B120, label %B121

B120:
    store i32 %calc16, i32* %def137
    br label %B122

B121:
    store i32 %calc18, i32* %def137
    br label %B122

B122:
    %ld259 = load i32, i32* %def137
    %cmp40 = icmp ne i32 %ld258, %ld259
    br i1 %cmp40, label %B123, label %B124

B123:
    store i32 %calc16, i32* %def138
    store i32 %ld259, i32* %def139
    store i32 %calc18, i32* %def140
    store i32 %calc17, i32* %def141
    store i32 %calc16, i32* %def142
    store i32 %ld258, i32* %def143
    store i32 %calc18, i32* %def144
    br label %B98

B124:
    store i32 %calc16, i32* %def138
    store i32 %ld259, i32* %def139
    store i32 %calc16, i32* %def140
    store i32 %calc17, i32* %def141
    store i32 %calc16, i32* %def142
    store i32 %ld258, i32* %def143
    store i32 %calc18, i32* %def144
    br label %B98

B98:
    %ld266 = load i32, i32* %def144
    %ld265 = load i32, i32* %def143
    %ld264 = load i32, i32* %def142
    %ld263 = load i32, i32* %def141
    %ld262 = load i32, i32* %def140
    %ld261 = load i32, i32* %def139
    %ld260 = load i32, i32* %def138
    %cmp11 = icmp sle i32 %ld262, %calc16
    %zot4 = zext i1 %cmp11 to i32
    %cmp12 = icmp ne i32 %zot4, 0
    br i1 %cmp12, label %B37, label %B40

B37:
    call void @putch(i32 71)
    call void @putch(i32 111)
    call void @putch(i32 111)
    call void @putch(i32 100)
    call void @putch(i32 33)
    call void @putch(i32 44)
    call void @putch(i32 78)
    call void @putch(i32 117)
    call void @putch(i32 109)
    call void @putch(i32 32)
    call void @putch(i32 105)
    call void @putch(i32 115)
    call void @putch(i32 32)
    br label %B126

B126:
    br label %B127

B127:
    br label %B128

B128:
    br label %B129

B129:
    %cmp41 = icmp sgt i32 %calc16, %calc17
    %zot18 = zext i1 %cmp41 to i32
    %cmp42 = icmp ne i32 %zot18, 0
    br i1 %cmp42, label %B130, label %B131

B130:
    store i32 %calc16, i32* %def145
    br label %B132

B131:
    store i32 %calc17, i32* %def145
    br label %B132

B132:
    %ld267 = load i32, i32* %def145
    br label %B133

B133:
    br label %B134

B134:
    %cmp43 = icmp slt i32 %calc17, %calc18
    %zot19 = zext i1 %cmp43 to i32
    %cmp44 = icmp ne i32 %zot19, 0
    br i1 %cmp44, label %B135, label %B136

B135:
    store i32 %calc17, i32* %def146
    br label %B137

B136:
    store i32 %calc18, i32* %def146
    br label %B137

B137:
    %ld268 = load i32, i32* %def146
    %cmp45 = icmp eq i32 %ld267, %ld268
    br i1 %cmp45, label %B138, label %B139

B138:
    store i32 0, i32* %def149
    store i32 %calc17, i32* %def150
    store i32 0, i32* %def151
    store i32 0, i32* %def152
    store i32 0, i32* %def153
    store i32 0, i32* %def154
    store i32 0, i32* %def155
    br label %B125

B139:
    br label %B140

B140:
    br label %B141

B141:
    %cmp46 = icmp sgt i32 %calc16, %calc17
    %zot20 = zext i1 %cmp46 to i32
    %cmp47 = icmp ne i32 %zot20, 0
    br i1 %cmp47, label %B142, label %B143

B142:
    store i32 %calc16, i32* %def147
    br label %B144

B143:
    store i32 %calc17, i32* %def147
    br label %B144

B144:
    %ld269 = load i32, i32* %def147
    br label %B145

B145:
    br label %B146

B146:
    %cmp48 = icmp slt i32 %calc16, %calc18
    %zot21 = zext i1 %cmp48 to i32
    %cmp49 = icmp ne i32 %zot21, 0
    br i1 %cmp49, label %B147, label %B148

B147:
    store i32 %calc16, i32* %def148
    br label %B149

B148:
    store i32 %calc18, i32* %def148
    br label %B149

B149:
    %ld270 = load i32, i32* %def148
    %cmp50 = icmp ne i32 %ld269, %ld270
    br i1 %cmp50, label %B150, label %B151

B150:
    store i32 %calc18, i32* %def149
    store i32 %calc18, i32* %def150
    store i32 %calc16, i32* %def151
    store i32 %ld270, i32* %def152
    store i32 %calc17, i32* %def153
    store i32 %calc16, i32* %def154
    store i32 %ld269, i32* %def155
    br label %B125

B151:
    store i32 %calc18, i32* %def149
    store i32 %calc16, i32* %def150
    store i32 %calc16, i32* %def151
    store i32 %ld270, i32* %def152
    store i32 %calc17, i32* %def153
    store i32 %calc16, i32* %def154
    store i32 %ld269, i32* %def155
    br label %B125

B125:
    %ld277 = load i32, i32* %def155
    %ld276 = load i32, i32* %def154
    %ld275 = load i32, i32* %def153
    %ld274 = load i32, i32* %def152
    %ld273 = load i32, i32* %def151
    %ld272 = load i32, i32* %def150
    %ld271 = load i32, i32* %def149
    %calc6 = sdiv i32 %ld272, 6
    %calc7 = mul i32 %calc6, %calc18
    %calc8 = srem i32 %calc7, 2
    %calc9 = add i32 %calc16, %calc8
    %calc10 = mul i32 0, -1
    %calc11 = sub i32 %calc9, %calc10
    call void @putint(i32 %calc11)
    call void @putch(i32 10)
    br label %B213

B40:
    br label %B153

B153:
    br label %B154

B154:
    br label %B155

B155:
    br label %B156

B156:
    %cmp51 = icmp sgt i32 %calc16, %calc17
    %zot22 = zext i1 %cmp51 to i32
    %cmp52 = icmp ne i32 %zot22, 0
    br i1 %cmp52, label %B157, label %B158

B157:
    store i32 %calc16, i32* %def156
    br label %B159

B158:
    store i32 %calc17, i32* %def156
    br label %B159

B159:
    %ld278 = load i32, i32* %def156
    br label %B160

B160:
    br label %B161

B161:
    %cmp53 = icmp slt i32 %calc17, %calc18
    %zot23 = zext i1 %cmp53 to i32
    %cmp54 = icmp ne i32 %zot23, 0
    br i1 %cmp54, label %B162, label %B163

B162:
    store i32 %calc17, i32* %def157
    br label %B164

B163:
    store i32 %calc18, i32* %def157
    br label %B164

B164:
    %ld279 = load i32, i32* %def157
    %cmp55 = icmp eq i32 %ld278, %ld279
    br i1 %cmp55, label %B165, label %B166

B165:
    store i32 0, i32* %def160
    store i32 0, i32* %def161
    store i32 0, i32* %def162
    store i32 0, i32* %def163
    store i32 0, i32* %def164
    store i32 0, i32* %def165
    store i32 %calc17, i32* %def166
    br label %B152

B166:
    br label %B167

B167:
    br label %B168

B168:
    %cmp56 = icmp sgt i32 %calc16, %calc17
    %zot24 = zext i1 %cmp56 to i32
    %cmp57 = icmp ne i32 %zot24, 0
    br i1 %cmp57, label %B169, label %B170

B169:
    store i32 %calc16, i32* %def158
    br label %B171

B170:
    store i32 %calc17, i32* %def158
    br label %B171

B171:
    %ld280 = load i32, i32* %def158
    br label %B172

B172:
    br label %B173

B173:
    %cmp58 = icmp slt i32 %calc16, %calc18
    %zot25 = zext i1 %cmp58 to i32
    %cmp59 = icmp ne i32 %zot25, 0
    br i1 %cmp59, label %B174, label %B175

B174:
    store i32 %calc16, i32* %def159
    br label %B176

B175:
    store i32 %calc18, i32* %def159
    br label %B176

B176:
    %ld281 = load i32, i32* %def159
    %cmp60 = icmp ne i32 %ld280, %ld281
    br i1 %cmp60, label %B177, label %B178

B177:
    store i32 %calc16, i32* %def160
    store i32 %ld281, i32* %def161
    store i32 %ld280, i32* %def162
    store i32 %calc18, i32* %def163
    store i32 %calc17, i32* %def164
    store i32 %calc16, i32* %def165
    store i32 %calc18, i32* %def166
    br label %B152

B178:
    store i32 %calc16, i32* %def160
    store i32 %ld281, i32* %def161
    store i32 %ld280, i32* %def162
    store i32 %calc18, i32* %def163
    store i32 %calc17, i32* %def164
    store i32 %calc16, i32* %def165
    store i32 %calc16, i32* %def166
    br label %B152

B152:
    %ld288 = load i32, i32* %def166
    %ld287 = load i32, i32* %def165
    %ld286 = load i32, i32* %def164
    %ld285 = load i32, i32* %def163
    %ld284 = load i32, i32* %def162
    %ld283 = load i32, i32* %def161
    %ld282 = load i32, i32* %def160
    %cmp13 = icmp slt i32 %ld288, %calc18
    %zot5 = zext i1 %cmp13 to i32
    %cmp14 = icmp ne i32 %zot5, 0
    br i1 %cmp14, label %B41, label %B42

B41:
    call void @putch(i32 79)
    call void @putch(i32 104)
    call void @putch(i32 33)
    call void @putch(i32 63)
    call void @putch(i32 10)
    br label %B213

B42:
    br label %B180

B180:
    br label %B181

B181:
    br label %B182

B182:
    br label %B183

B183:
    %cmp61 = icmp sgt i32 %calc16, %calc17
    %zot26 = zext i1 %cmp61 to i32
    %cmp62 = icmp ne i32 %zot26, 0
    br i1 %cmp62, label %B184, label %B185

B184:
    store i32 %calc16, i32* %def167
    br label %B186

B185:
    store i32 %calc17, i32* %def167
    br label %B186

B186:
    %ld289 = load i32, i32* %def167
    br label %B187

B187:
    br label %B188

B188:
    %cmp63 = icmp slt i32 %calc17, %calc18
    %zot27 = zext i1 %cmp63 to i32
    %cmp64 = icmp ne i32 %zot27, 0
    br i1 %cmp64, label %B189, label %B190

B189:
    store i32 %calc17, i32* %def168
    br label %B191

B190:
    store i32 %calc18, i32* %def168
    br label %B191

B191:
    %ld290 = load i32, i32* %def168
    %cmp65 = icmp eq i32 %ld289, %ld290
    br i1 %cmp65, label %B192, label %B193

B192:
    store i32 0, i32* %def171
    store i32 0, i32* %def172
    store i32 %calc17, i32* %def173
    store i32 0, i32* %def174
    store i32 0, i32* %def175
    store i32 0, i32* %def176
    store i32 0, i32* %def177
    br label %B179

B193:
    br label %B194

B194:
    br label %B195

B195:
    %cmp66 = icmp sgt i32 %calc16, %calc17
    %zot28 = zext i1 %cmp66 to i32
    %cmp67 = icmp ne i32 %zot28, 0
    br i1 %cmp67, label %B196, label %B197

B196:
    store i32 %calc16, i32* %def169
    br label %B198

B197:
    store i32 %calc17, i32* %def169
    br label %B198

B198:
    %ld291 = load i32, i32* %def169
    br label %B199

B199:
    br label %B200

B200:
    %cmp68 = icmp slt i32 %calc16, %calc18
    %zot29 = zext i1 %cmp68 to i32
    %cmp69 = icmp ne i32 %zot29, 0
    br i1 %cmp69, label %B201, label %B202

B201:
    store i32 %calc16, i32* %def170
    br label %B203

B202:
    store i32 %calc18, i32* %def170
    br label %B203

B203:
    %ld292 = load i32, i32* %def170
    %cmp70 = icmp ne i32 %ld291, %ld292
    br i1 %cmp70, label %B204, label %B205

B204:
    store i32 %calc17, i32* %def171
    store i32 %calc16, i32* %def172
    store i32 %calc18, i32* %def173
    store i32 %calc16, i32* %def174
    store i32 %ld292, i32* %def175
    store i32 %ld291, i32* %def176
    store i32 %calc18, i32* %def177
    br label %B179

B205:
    store i32 %calc17, i32* %def171
    store i32 %calc16, i32* %def172
    store i32 %calc16, i32* %def173
    store i32 %calc16, i32* %def174
    store i32 %ld292, i32* %def175
    store i32 %ld291, i32* %def176
    store i32 %calc18, i32* %def177
    br label %B179

B179:
    %ld299 = load i32, i32* %def177
    %ld298 = load i32, i32* %def176
    %ld297 = load i32, i32* %def175
    %ld296 = load i32, i32* %def174
    %ld295 = load i32, i32* %def173
    %ld294 = load i32, i32* %def172
    %ld293 = load i32, i32* %def171
    %calc12 = srem i32 %ld295, 65535
    br label %B207

B207:
    br label %B208

B208:
    %cmp71 = icmp sgt i32 %calc12, 20
    %zot30 = zext i1 %cmp71 to i32
    %cmp72 = icmp ne i32 %zot30, 0
    store i32 %ld282, i32* %def178
    store i32 %ld283, i32* %def179
    store i32 %ld284, i32* %def180
    store i32 %ld285, i32* %def181
    store i32 %ld286, i32* %def182
    store i32 %ld287, i32* %def183
    store i32 %calc17, i32* %def184
    store i32 %ld279, i32* %def185
    store i32 %calc15, i32* %def186
    store i32 %ld288, i32* %def187
    store i32 %calc18, i32* %def188
    store i32 %ld290, i32* %def189
    store i32 %ld293, i32* %def190
    store i32 %calc18, i32* %def191
    store i32 %calc17, i32* %def192
    store i32 %calc16, i32* %def193
    store i32 %ld289, i32* %def194
    store i32 %calc16, i32* %def195
    store i32 %calc17, i32* %def196
    store i32 %calc17, i32* %def197
    store i32 %calc18, i32* %def198
    store i32 0, i32* %def199
    store i32 %ld294, i32* %def200
    store i32 %calc16, i32* %def201
    store i32 -1, i32* %def202
    store i32 %calc17, i32* %def203
    store i32 %calc14, i32* %def204
    store i32 %ld252, i32* %def205
    store i32 %ld254, i32* %def206
    store i32 %call103, i32* %def207
    store i32 %calc14, i32* %def208
    store i32 %call104, i32* %def209
    store i32 %calc15, i32* %def210
    store i32 %calc5, i32* %def211
    store i32 %call107, i32* %def212
    store i32 %ld34, i32* %def213
    store i32 %ld255, i32* %def214
    store i32 %ld252, i32* %def215
    store i32 %call102, i32* %def216
    store i32 %ld32, i32* %def217
    store i32 88, i32* %def218
    store i32 %calc13, i32* %def219
    store i32 %ld33, i32* %def220
    store i32 %ld253, i32* %def221
    store i32 %ld253, i32* %def222
    store i32 %calc13, i32* %def223
    store i32 %calc18, i32* %def224
    store i32 %calc17, i32* %def225
    store i32 %ld257, i32* %def226
    store i32 %ld260, i32* %def227
    store i32 %ld261, i32* %def228
    store i32 %ld262, i32* %def229
    store i32 %ld263, i32* %def230
    store i32 %ld264, i32* %def231
    store i32 %ld265, i32* %def232
    store i32 %ld266, i32* %def233
    store i32 %calc16, i32* %def234
    store i32 %call108, i32* %def235
    store i32 %calc17, i32* %def236
    store i32 %call109, i32* %def237
    store i32 %calc16, i32* %def238
    store i32 %calc17, i32* %def239
    store i32 %calc16, i32* %def240
    store i32 %ld256, i32* %def241
    store i32 %calc18, i32* %def242
    store i32 %calc18, i32* %def243
    store i32 %calc17, i32* %def244
    store i32 %calc12, i32* %def245
    store i32 1, i32* %def246
    store i32 %ld295, i32* %def247
    store i32 %calc12, i32* %def248
    store i32 %ld296, i32* %def249
    store i32 %ld297, i32* %def250
    store i32 %calc17, i32* %def251
    store i32 %calc16, i32* %def252
    store i32 %ld278, i32* %def253
    store i32 %calc18, i32* %def254
    store i32 %calc18, i32* %def255
    store i32 %calc17, i32* %def256
    store i32 %calc16, i32* %def257
    store i32 %ld298, i32* %def258
    store i32 %ld299, i32* %def259
    br i1 %cmp72, label %B209, label %B210

B209:
    call void @putch(i32 89)
    call void @putch(i32 111)
    call void @putch(i32 117)
    call void @putch(i32 114)
    call void @putch(i32 32)
    call void @putch(i32 78)
    call void @putch(i32 117)
    call void @putch(i32 109)
    call void @putch(i32 32)
    call void @putch(i32 105)
    call void @putch(i32 115)
    call void @putch(i32 32)
    call void @putch(i32 116)
    call void @putch(i32 111)
    call void @putch(i32 111)
    call void @putch(i32 32)
    call void @putch(i32 66)
    call void @putch(i32 105)
    call void @putch(i32 103)
    call void @putch(i32 33)
    call void @putch(i32 33)
    call void @putch(i32 33)
    call void @putch(i32 10)
    store i32 %ld282, i32* %def260
    store i32 %ld283, i32* %def261
    store i32 %ld284, i32* %def262
    store i32 %ld285, i32* %def263
    store i32 %ld286, i32* %def264
    store i32 %ld287, i32* %def265
    store i32 %calc17, i32* %def266
    store i32 %ld279, i32* %def267
    store i32 %calc15, i32* %def268
    store i32 %ld288, i32* %def269
    store i32 %calc18, i32* %def270
    store i32 %ld290, i32* %def271
    store i32 %ld293, i32* %def272
    store i32 %calc18, i32* %def273
    store i32 %calc17, i32* %def274
    store i32 %calc16, i32* %def275
    store i32 %ld289, i32* %def276
    store i32 %calc16, i32* %def277
    store i32 %calc17, i32* %def278
    store i32 %calc17, i32* %def279
    store i32 %calc18, i32* %def280
    store i32 0, i32* %def281
    store i32 %ld294, i32* %def282
    store i32 %calc16, i32* %def283
    store i32 -1, i32* %def284
    store i32 %calc17, i32* %def285
    store i32 %calc14, i32* %def286
    store i32 %ld252, i32* %def287
    store i32 %ld254, i32* %def288
    store i32 %call103, i32* %def289
    store i32 %calc14, i32* %def290
    store i32 %call104, i32* %def291
    store i32 %calc15, i32* %def292
    store i32 %calc5, i32* %def293
    store i32 %call107, i32* %def294
    store i32 %ld34, i32* %def295
    store i32 %ld255, i32* %def296
    store i32 %ld252, i32* %def297
    store i32 %call102, i32* %def298
    store i32 %ld32, i32* %def299
    store i32 88, i32* %def300
    store i32 %calc13, i32* %def301
    store i32 %ld33, i32* %def302
    store i32 %ld253, i32* %def303
    store i32 %ld253, i32* %def304
    store i32 %calc13, i32* %def305
    store i32 %calc18, i32* %def306
    store i32 %calc17, i32* %def307
    store i32 %ld257, i32* %def308
    store i32 %ld260, i32* %def309
    store i32 %ld261, i32* %def310
    store i32 %ld262, i32* %def311
    store i32 %ld263, i32* %def312
    store i32 %ld264, i32* %def313
    store i32 %ld265, i32* %def314
    store i32 %ld266, i32* %def315
    store i32 %calc16, i32* %def316
    store i32 %call108, i32* %def317
    store i32 %calc17, i32* %def318
    store i32 %call109, i32* %def319
    store i32 %calc16, i32* %def320
    store i32 %calc17, i32* %def321
    store i32 %calc16, i32* %def322
    store i32 %ld256, i32* %def323
    store i32 %calc18, i32* %def324
    store i32 %calc18, i32* %def325
    store i32 %calc17, i32* %def326
    store i32 -1, i32* %def327
    store i32 %calc12, i32* %def328
    store i32 1, i32* %def329
    store i32 %ld295, i32* %def330
    store i32 %calc12, i32* %def331
    store i32 %ld296, i32* %def332
    store i32 %ld297, i32* %def333
    store i32 %calc17, i32* %def334
    store i32 %calc16, i32* %def335
    store i32 %ld278, i32* %def336
    store i32 %calc18, i32* %def337
    store i32 %calc18, i32* %def338
    store i32 %calc17, i32* %def339
    store i32 %calc16, i32* %def340
    store i32 %ld298, i32* %def341
    store i32 %ld299, i32* %def342
    br label %B206

B210:
    %ld381 = load i32, i32* %def259
    %ld380 = load i32, i32* %def258
    %ld379 = load i32, i32* %def257
    %ld378 = load i32, i32* %def256
    %ld377 = load i32, i32* %def255
    %ld376 = load i32, i32* %def254
    %ld375 = load i32, i32* %def253
    %ld374 = load i32, i32* %def252
    %ld373 = load i32, i32* %def251
    %ld372 = load i32, i32* %def250
    %ld371 = load i32, i32* %def249
    %ld370 = load i32, i32* %def248
    %ld369 = load i32, i32* %def247
    %ld368 = load i32, i32* %def246
    %ld367 = load i32, i32* %def245
    %ld366 = load i32, i32* %def244
    %ld365 = load i32, i32* %def243
    %ld364 = load i32, i32* %def242
    %ld363 = load i32, i32* %def241
    %ld362 = load i32, i32* %def240
    %ld361 = load i32, i32* %def239
    %ld360 = load i32, i32* %def238
    %ld359 = load i32, i32* %def237
    %ld358 = load i32, i32* %def236
    %ld357 = load i32, i32* %def235
    %ld356 = load i32, i32* %def234
    %ld355 = load i32, i32* %def233
    %ld354 = load i32, i32* %def232
    %ld353 = load i32, i32* %def231
    %ld352 = load i32, i32* %def230
    %ld351 = load i32, i32* %def229
    %ld350 = load i32, i32* %def228
    %ld349 = load i32, i32* %def227
    %ld348 = load i32, i32* %def226
    %ld347 = load i32, i32* %def225
    %ld346 = load i32, i32* %def224
    %ld345 = load i32, i32* %def223
    %ld344 = load i32, i32* %def222
    %ld343 = load i32, i32* %def221
    %ld342 = load i32, i32* %def220
    %ld341 = load i32, i32* %def219
    %ld340 = load i32, i32* %def218
    %ld339 = load i32, i32* %def217
    %ld338 = load i32, i32* %def216
    %ld337 = load i32, i32* %def215
    %ld336 = load i32, i32* %def214
    %ld335 = load i32, i32* %def213
    %ld334 = load i32, i32* %def212
    %ld333 = load i32, i32* %def211
    %ld332 = load i32, i32* %def210
    %ld331 = load i32, i32* %def209
    %ld330 = load i32, i32* %def208
    %ld329 = load i32, i32* %def207
    %ld328 = load i32, i32* %def206
    %ld327 = load i32, i32* %def205
    %ld326 = load i32, i32* %def204
    %ld325 = load i32, i32* %def203
    %ld324 = load i32, i32* %def202
    %ld323 = load i32, i32* %def201
    %ld322 = load i32, i32* %def200
    %ld321 = load i32, i32* %def199
    %ld320 = load i32, i32* %def198
    %ld319 = load i32, i32* %def197
    %ld318 = load i32, i32* %def196
    %ld317 = load i32, i32* %def195
    %ld316 = load i32, i32* %def194
    %ld315 = load i32, i32* %def193
    %ld314 = load i32, i32* %def192
    %ld313 = load i32, i32* %def191
    %ld312 = load i32, i32* %def190
    %ld311 = load i32, i32* %def189
    %ld310 = load i32, i32* %def188
    %ld309 = load i32, i32* %def187
    %ld308 = load i32, i32* %def186
    %ld307 = load i32, i32* %def185
    %ld306 = load i32, i32* %def184
    %ld305 = load i32, i32* %def183
    %ld304 = load i32, i32* %def182
    %ld303 = load i32, i32* %def181
    %ld302 = load i32, i32* %def180
    %ld301 = load i32, i32* %def179
    %ld300 = load i32, i32* %def178
    %cmp73 = icmp ne i32 %ld367, 0
    br i1 %cmp73, label %B211, label %B212

B211:
    %calc19 = mul i32 %ld368, %ld367
    %calc20 = add i32 -1, %ld367
    store i32 %ld300, i32* %def178
    store i32 %ld301, i32* %def179
    store i32 %ld302, i32* %def180
    store i32 %ld303, i32* %def181
    store i32 %ld304, i32* %def182
    store i32 %ld305, i32* %def183
    store i32 %ld306, i32* %def184
    store i32 %ld307, i32* %def185
    store i32 %ld308, i32* %def186
    store i32 %ld309, i32* %def187
    store i32 %ld310, i32* %def188
    store i32 %ld311, i32* %def189
    store i32 %ld312, i32* %def190
    store i32 %ld313, i32* %def191
    store i32 %ld314, i32* %def192
    store i32 %ld315, i32* %def193
    store i32 %ld316, i32* %def194
    store i32 %ld317, i32* %def195
    store i32 %ld318, i32* %def196
    store i32 %ld319, i32* %def197
    store i32 %ld320, i32* %def198
    store i32 %ld321, i32* %def199
    store i32 %ld322, i32* %def200
    store i32 %ld323, i32* %def201
    store i32 %ld324, i32* %def202
    store i32 %ld325, i32* %def203
    store i32 %ld326, i32* %def204
    store i32 %ld327, i32* %def205
    store i32 %ld328, i32* %def206
    store i32 %ld329, i32* %def207
    store i32 %ld330, i32* %def208
    store i32 %ld331, i32* %def209
    store i32 %ld332, i32* %def210
    store i32 %ld333, i32* %def211
    store i32 %ld334, i32* %def212
    store i32 %ld335, i32* %def213
    store i32 %ld336, i32* %def214
    store i32 %ld337, i32* %def215
    store i32 %ld338, i32* %def216
    store i32 %ld339, i32* %def217
    store i32 %ld340, i32* %def218
    store i32 %ld341, i32* %def219
    store i32 %ld342, i32* %def220
    store i32 %ld343, i32* %def221
    store i32 %ld344, i32* %def222
    store i32 %ld345, i32* %def223
    store i32 %ld346, i32* %def224
    store i32 %ld347, i32* %def225
    store i32 %ld348, i32* %def226
    store i32 %ld349, i32* %def227
    store i32 %ld350, i32* %def228
    store i32 %ld351, i32* %def229
    store i32 %ld352, i32* %def230
    store i32 %ld353, i32* %def231
    store i32 %ld354, i32* %def232
    store i32 %ld355, i32* %def233
    store i32 %ld356, i32* %def234
    store i32 %ld357, i32* %def235
    store i32 %ld358, i32* %def236
    store i32 %ld359, i32* %def237
    store i32 %ld360, i32* %def238
    store i32 %ld361, i32* %def239
    store i32 %ld362, i32* %def240
    store i32 %ld363, i32* %def241
    store i32 %ld364, i32* %def242
    store i32 %ld365, i32* %def243
    store i32 %ld366, i32* %def244
    store i32 %calc20, i32* %def245
    store i32 %calc19, i32* %def246
    store i32 %ld369, i32* %def247
    store i32 %ld370, i32* %def248
    store i32 %ld371, i32* %def249
    store i32 %ld372, i32* %def250
    store i32 %ld373, i32* %def251
    store i32 %ld374, i32* %def252
    store i32 %ld375, i32* %def253
    store i32 %ld376, i32* %def254
    store i32 %ld377, i32* %def255
    store i32 %ld378, i32* %def256
    store i32 %ld379, i32* %def257
    store i32 %ld380, i32* %def258
    store i32 %ld381, i32* %def259
    br label %B210

B212:
    store i32 %ld300, i32* %def260
    store i32 %ld301, i32* %def261
    store i32 %ld302, i32* %def262
    store i32 %ld303, i32* %def263
    store i32 %ld304, i32* %def264
    store i32 %ld305, i32* %def265
    store i32 %ld306, i32* %def266
    store i32 %ld307, i32* %def267
    store i32 %ld308, i32* %def268
    store i32 %ld309, i32* %def269
    store i32 %ld310, i32* %def270
    store i32 %ld311, i32* %def271
    store i32 %ld312, i32* %def272
    store i32 %ld313, i32* %def273
    store i32 %ld314, i32* %def274
    store i32 %ld315, i32* %def275
    store i32 %ld316, i32* %def276
    store i32 %ld317, i32* %def277
    store i32 %ld318, i32* %def278
    store i32 %ld319, i32* %def279
    store i32 %ld320, i32* %def280
    store i32 %ld321, i32* %def281
    store i32 %ld322, i32* %def282
    store i32 %ld323, i32* %def283
    store i32 %ld324, i32* %def284
    store i32 %ld325, i32* %def285
    store i32 %ld326, i32* %def286
    store i32 %ld327, i32* %def287
    store i32 %ld328, i32* %def288
    store i32 %ld329, i32* %def289
    store i32 %ld330, i32* %def290
    store i32 %ld331, i32* %def291
    store i32 %ld332, i32* %def292
    store i32 %ld333, i32* %def293
    store i32 %ld334, i32* %def294
    store i32 %ld335, i32* %def295
    store i32 %ld336, i32* %def296
    store i32 %ld337, i32* %def297
    store i32 %ld338, i32* %def298
    store i32 %ld339, i32* %def299
    store i32 %ld340, i32* %def300
    store i32 %ld341, i32* %def301
    store i32 %ld342, i32* %def302
    store i32 %ld343, i32* %def303
    store i32 %ld344, i32* %def304
    store i32 %ld345, i32* %def305
    store i32 %ld346, i32* %def306
    store i32 %ld347, i32* %def307
    store i32 %ld348, i32* %def308
    store i32 %ld349, i32* %def309
    store i32 %ld350, i32* %def310
    store i32 %ld351, i32* %def311
    store i32 %ld352, i32* %def312
    store i32 %ld353, i32* %def313
    store i32 %ld354, i32* %def314
    store i32 %ld355, i32* %def315
    store i32 %ld356, i32* %def316
    store i32 %ld357, i32* %def317
    store i32 %ld358, i32* %def318
    store i32 %ld359, i32* %def319
    store i32 %ld360, i32* %def320
    store i32 %ld361, i32* %def321
    store i32 %ld362, i32* %def322
    store i32 %ld363, i32* %def323
    store i32 %ld364, i32* %def324
    store i32 %ld365, i32* %def325
    store i32 %ld366, i32* %def326
    store i32 %ld368, i32* %def327
    store i32 %ld367, i32* %def328
    store i32 %ld368, i32* %def329
    store i32 %ld369, i32* %def330
    store i32 %ld370, i32* %def331
    store i32 %ld371, i32* %def332
    store i32 %ld372, i32* %def333
    store i32 %ld373, i32* %def334
    store i32 %ld374, i32* %def335
    store i32 %ld375, i32* %def336
    store i32 %ld376, i32* %def337
    store i32 %ld377, i32* %def338
    store i32 %ld378, i32* %def339
    store i32 %ld379, i32* %def340
    store i32 %ld380, i32* %def341
    store i32 %ld381, i32* %def342
    br label %B206

B206:
    %ld464 = load i32, i32* %def342
    %ld463 = load i32, i32* %def341
    %ld462 = load i32, i32* %def340
    %ld461 = load i32, i32* %def339
    %ld460 = load i32, i32* %def338
    %ld459 = load i32, i32* %def337
    %ld458 = load i32, i32* %def336
    %ld457 = load i32, i32* %def335
    %ld456 = load i32, i32* %def334
    %ld455 = load i32, i32* %def333
    %ld454 = load i32, i32* %def332
    %ld453 = load i32, i32* %def331
    %ld452 = load i32, i32* %def330
    %ld451 = load i32, i32* %def329
    %ld450 = load i32, i32* %def328
    %ld449 = load i32, i32* %def327
    %ld448 = load i32, i32* %def326
    %ld447 = load i32, i32* %def325
    %ld446 = load i32, i32* %def324
    %ld445 = load i32, i32* %def323
    %ld444 = load i32, i32* %def322
    %ld443 = load i32, i32* %def321
    %ld442 = load i32, i32* %def320
    %ld441 = load i32, i32* %def319
    %ld440 = load i32, i32* %def318
    %ld439 = load i32, i32* %def317
    %ld438 = load i32, i32* %def316
    %ld437 = load i32, i32* %def315
    %ld436 = load i32, i32* %def314
    %ld435 = load i32, i32* %def313
    %ld434 = load i32, i32* %def312
    %ld433 = load i32, i32* %def311
    %ld432 = load i32, i32* %def310
    %ld431 = load i32, i32* %def309
    %ld430 = load i32, i32* %def308
    %ld429 = load i32, i32* %def307
    %ld428 = load i32, i32* %def306
    %ld427 = load i32, i32* %def305
    %ld426 = load i32, i32* %def304
    %ld425 = load i32, i32* %def303
    %ld424 = load i32, i32* %def302
    %ld423 = load i32, i32* %def301
    %ld422 = load i32, i32* %def300
    %ld421 = load i32, i32* %def299
    %ld420 = load i32, i32* %def298
    %ld419 = load i32, i32* %def297
    %ld418 = load i32, i32* %def296
    %ld417 = load i32, i32* %def295
    %ld416 = load i32, i32* %def294
    %ld415 = load i32, i32* %def293
    %ld414 = load i32, i32* %def292
    %ld413 = load i32, i32* %def291
    %ld412 = load i32, i32* %def290
    %ld411 = load i32, i32* %def289
    %ld410 = load i32, i32* %def288
    %ld409 = load i32, i32* %def287
    %ld408 = load i32, i32* %def286
    %ld407 = load i32, i32* %def285
    %ld406 = load i32, i32* %def284
    %ld405 = load i32, i32* %def283
    %ld404 = load i32, i32* %def282
    %ld403 = load i32, i32* %def281
    %ld402 = load i32, i32* %def280
    %ld401 = load i32, i32* %def279
    %ld400 = load i32, i32* %def278
    %ld399 = load i32, i32* %def277
    %ld398 = load i32, i32* %def276
    %ld397 = load i32, i32* %def275
    %ld396 = load i32, i32* %def274
    %ld395 = load i32, i32* %def273
    %ld394 = load i32, i32* %def272
    %ld393 = load i32, i32* %def271
    %ld392 = load i32, i32* %def270
    %ld391 = load i32, i32* %def269
    %ld390 = load i32, i32* %def268
    %ld389 = load i32, i32* %def267
    %ld388 = load i32, i32* %def266
    %ld387 = load i32, i32* %def265
    %ld386 = load i32, i32* %def264
    %ld385 = load i32, i32* %def263
    %ld384 = load i32, i32* %def262
    %ld383 = load i32, i32* %def261
    %ld382 = load i32, i32* %def260
    call void @putint(i32 %ld449)
    call void @putch(i32 10)
    br label %B213

B213:
    ret i32 0
}


