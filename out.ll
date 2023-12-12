; ModuleID = 'llvm-link'
source_filename = "llvm-link"
target datalayout = "e-m:e-p270:32:32-p271:32:32-p272:64:64-i64:64-f80:128-n8:16:32:64-S128"
target triple = "x86_64-pc-linux-gnu"

@q = dso_local global i32 66
@r = dso_local global i32 155
@s = dso_local global i32 15
@.str = private unnamed_addr constant [3 x i8] c"%d\00", align 1
@.str.1 = private unnamed_addr constant [3 x i8] c"%c\00", align 1
@.str.2 = private unnamed_addr constant [4 x i8] c"%d:\00", align 1
@.str.3 = private unnamed_addr constant [4 x i8] c" %d\00", align 1
@.str.4 = private unnamed_addr constant [2 x i8] c"\0A\00", align 1
@.str.5 = private unnamed_addr constant [3 x i8] c"%s\00", align 1

define dso_local i32 @main() {
B31:
  %ld32 = load i32, i32* @q, align 4
  %def130 = alloca i32, align 4
  %def131 = alloca i32, align 4
  %def132 = alloca i32, align 4
  %def133 = alloca i32, align 4
  %def134 = alloca i32, align 4
  %def135 = alloca i32, align 4
  %def136 = alloca i32, align 4
  %def137 = alloca i32, align 4
  %def138 = alloca i32, align 4
  %def139 = alloca i32, align 4
  %def140 = alloca i32, align 4
  %def141 = alloca i32, align 4
  %def142 = alloca i32, align 4
  %def143 = alloca i32, align 4
  %def144 = alloca i32, align 4
  %def145 = alloca i32, align 4
  %def146 = alloca i32, align 4
  %def147 = alloca i32, align 4
  %def148 = alloca i32, align 4
  %def149 = alloca i32, align 4
  %def150 = alloca i32, align 4
  %def151 = alloca i32, align 4
  %def152 = alloca i32, align 4
  %def153 = alloca i32, align 4
  %def154 = alloca i32, align 4
  %def155 = alloca i32, align 4
  %def156 = alloca i32, align 4
  %def157 = alloca i32, align 4
  %def158 = alloca i32, align 4
  %def159 = alloca i32, align 4
  %def160 = alloca i32, align 4
  %def161 = alloca i32, align 4
  %def162 = alloca i32, align 4
  %def163 = alloca i32, align 4
  %def164 = alloca i32, align 4
  %def165 = alloca i32, align 4
  %def166 = alloca i32, align 4
  %def167 = alloca i32, align 4
  %def168 = alloca i32, align 4
  %def169 = alloca i32, align 4
  %def170 = alloca i32, align 4
  %def171 = alloca i32, align 4
  %def172 = alloca i32, align 4
  %def173 = alloca i32, align 4
  %def174 = alloca i32, align 4
  %def175 = alloca i32, align 4
  %def176 = alloca i32, align 4
  %def177 = alloca i32, align 4
  %def178 = alloca i32, align 4
  %def179 = alloca i32, align 4
  %def180 = alloca i32, align 4
  %def181 = alloca i32, align 4
  %def182 = alloca i32, align 4
  %def183 = alloca i32, align 4
  %def184 = alloca i32, align 4
  %def185 = alloca i32, align 4
  %def186 = alloca i32, align 4
  %def187 = alloca i32, align 4
  %def188 = alloca i32, align 4
  %def189 = alloca i32, align 4
  %def190 = alloca i32, align 4
  %def191 = alloca i32, align 4
  %def192 = alloca i32, align 4
  %def193 = alloca i32, align 4
  %def194 = alloca i32, align 4
  %def195 = alloca i32, align 4
  %def196 = alloca i32, align 4
  %def197 = alloca i32, align 4
  %def198 = alloca i32, align 4
  %def199 = alloca i32, align 4
  %def200 = alloca i32, align 4
  %def201 = alloca i32, align 4
  %def202 = alloca i32, align 4
  %def203 = alloca i32, align 4
  %def204 = alloca i32, align 4
  %def205 = alloca i32, align 4
  %def206 = alloca i32, align 4
  %def207 = alloca i32, align 4
  %def208 = alloca i32, align 4
  %def209 = alloca i32, align 4
  %def210 = alloca i32, align 4
  %def211 = alloca i32, align 4
  %def212 = alloca i32, align 4
  %def213 = alloca i32, align 4
  %def214 = alloca i32, align 4
  %def215 = alloca i32, align 4
  %def216 = alloca i32, align 4
  %def217 = alloca i32, align 4
  %def218 = alloca i32, align 4
  %def219 = alloca i32, align 4
  %def220 = alloca i32, align 4
  %def221 = alloca i32, align 4
  %def222 = alloca i32, align 4
  %def223 = alloca i32, align 4
  %def224 = alloca i32, align 4
  %def225 = alloca i32, align 4
  %def226 = alloca i32, align 4
  %def227 = alloca i32, align 4
  %def228 = alloca i32, align 4
  %def229 = alloca i32, align 4
  %def230 = alloca i32, align 4
  %def231 = alloca i32, align 4
  %def232 = alloca i32, align 4
  %def233 = alloca i32, align 4
  %def234 = alloca i32, align 4
  %def235 = alloca i32, align 4
  %def236 = alloca i32, align 4
  %def237 = alloca i32, align 4
  %def238 = alloca i32, align 4
  %def239 = alloca i32, align 4
  %def240 = alloca i32, align 4
  %def241 = alloca i32, align 4
  %def242 = alloca i32, align 4
  %def243 = alloca i32, align 4
  %def244 = alloca i32, align 4
  %def245 = alloca i32, align 4
  %def246 = alloca i32, align 4
  %def247 = alloca i32, align 4
  %def248 = alloca i32, align 4
  %def249 = alloca i32, align 4
  %def250 = alloca i32, align 4
  %def251 = alloca i32, align 4
  %def252 = alloca i32, align 4
  %def253 = alloca i32, align 4
  %def254 = alloca i32, align 4
  %def255 = alloca i32, align 4
  %def256 = alloca i32, align 4
  %def257 = alloca i32, align 4
  %def258 = alloca i32, align 4
  %def259 = alloca i32, align 4
  %def260 = alloca i32, align 4
  %def261 = alloca i32, align 4
  %def262 = alloca i32, align 4
  %def263 = alloca i32, align 4
  %def264 = alloca i32, align 4
  %def265 = alloca i32, align 4
  %def266 = alloca i32, align 4
  %def267 = alloca i32, align 4
  %def268 = alloca i32, align 4
  %def269 = alloca i32, align 4
  %def270 = alloca i32, align 4
  %def271 = alloca i32, align 4
  %def272 = alloca i32, align 4
  %def273 = alloca i32, align 4
  %def274 = alloca i32, align 4
  %def275 = alloca i32, align 4
  %def276 = alloca i32, align 4
  %def277 = alloca i32, align 4
  %def278 = alloca i32, align 4
  %def279 = alloca i32, align 4
  %def280 = alloca i32, align 4
  %def281 = alloca i32, align 4
  %def282 = alloca i32, align 4
  %def283 = alloca i32, align 4
  %def284 = alloca i32, align 4
  %def285 = alloca i32, align 4
  %def286 = alloca i32, align 4
  %def287 = alloca i32, align 4
  %def288 = alloca i32, align 4
  %def289 = alloca i32, align 4
  %def290 = alloca i32, align 4
  %def291 = alloca i32, align 4
  %def292 = alloca i32, align 4
  %def293 = alloca i32, align 4
  %def294 = alloca i32, align 4
  %def295 = alloca i32, align 4
  %def296 = alloca i32, align 4
  %def297 = alloca i32, align 4
  %def298 = alloca i32, align 4
  %def299 = alloca i32, align 4
  %def300 = alloca i32, align 4
  %def301 = alloca i32, align 4
  %def302 = alloca i32, align 4
  %def303 = alloca i32, align 4
  %def304 = alloca i32, align 4
  %def305 = alloca i32, align 4
  %def306 = alloca i32, align 4
  %def307 = alloca i32, align 4
  %def308 = alloca i32, align 4
  %def309 = alloca i32, align 4
  %def310 = alloca i32, align 4
  %def311 = alloca i32, align 4
  %def312 = alloca i32, align 4
  %def313 = alloca i32, align 4
  %def314 = alloca i32, align 4
  %def315 = alloca i32, align 4
  %def316 = alloca i32, align 4
  %def317 = alloca i32, align 4
  %def318 = alloca i32, align 4
  %def319 = alloca i32, align 4
  %def320 = alloca i32, align 4
  %def321 = alloca i32, align 4
  %def322 = alloca i32, align 4
  %def323 = alloca i32, align 4
  %def324 = alloca i32, align 4
  %def325 = alloca i32, align 4
  %def326 = alloca i32, align 4
  %def327 = alloca i32, align 4
  %def328 = alloca i32, align 4
  %def329 = alloca i32, align 4
  %def330 = alloca i32, align 4
  %def331 = alloca i32, align 4
  %def332 = alloca i32, align 4
  %def333 = alloca i32, align 4
  %def334 = alloca i32, align 4
  %def335 = alloca i32, align 4
  %def336 = alloca i32, align 4
  %def337 = alloca i32, align 4
  %def338 = alloca i32, align 4
  %def339 = alloca i32, align 4
  %def340 = alloca i32, align 4
  %def341 = alloca i32, align 4
  %def342 = alloca i32, align 4
  br label %B65

B65:                                              ; preds = %B31
  br label %B66

B66:                                              ; preds = %B65
  %cmp23 = icmp slt i32 88, %ld32
  %zot10 = zext i1 %cmp23 to i32
  %cmp24 = icmp ne i32 %zot10, 0
  br i1 %cmp24, label %B67, label %B68

B67:                                              ; preds = %B66
  store i32 88, i32* %def130, align 4
  br label %B64

B68:                                              ; preds = %B66
  store i32 %ld32, i32* %def130, align 4
  br label %B64

B64:                                              ; preds = %B68, %B67
  %ld252 = load i32, i32* %def130, align 4
  %ld33 = load i32, i32* @s, align 4
  br label %B70

B70:                                              ; preds = %B64
  %call102 = call i32 @getint()
  %calc13 = add i32 0, %call102
  br label %B69

B69:                                              ; preds = %B70
  br label %B72

B72:                                              ; preds = %B69
  br label %B73

B73:                                              ; preds = %B72
  %cmp25 = icmp sgt i32 %ld33, %calc13
  %zot11 = zext i1 %cmp25 to i32
  %cmp26 = icmp ne i32 %zot11, 0
  br i1 %cmp26, label %B74, label %B75

B74:                                              ; preds = %B73
  store i32 %ld33, i32* %def131, align 4
  br label %B71

B75:                                              ; preds = %B73
  store i32 %calc13, i32* %def131, align 4
  br label %B71

B71:                                              ; preds = %B75, %B74
  %ld253 = load i32, i32* %def131, align 4
  br label %B77

B77:                                              ; preds = %B71
  br label %B78

B78:                                              ; preds = %B77
  %cmp27 = icmp sgt i32 %ld252, %ld253
  %zot12 = zext i1 %cmp27 to i32
  %cmp28 = icmp ne i32 %zot12, 0
  br i1 %cmp28, label %B79, label %B80

B79:                                              ; preds = %B78
  store i32 %ld252, i32* %def132, align 4
  br label %B76

B80:                                              ; preds = %B78
  store i32 %ld253, i32* %def132, align 4
  br label %B76

B76:                                              ; preds = %B80, %B79
  %ld254 = load i32, i32* %def132, align 4
  %ld34 = load i32, i32* @r, align 4
  br label %B82

B82:                                              ; preds = %B76
  %call103 = call i32 @getint()
  %calc14 = add i32 0, %call103
  br label %B81

B81:                                              ; preds = %B82
  br label %B84

B84:                                              ; preds = %B81
  br label %B85

B85:                                              ; preds = %B84
  %cmp29 = icmp slt i32 %ld34, %calc14
  %zot13 = zext i1 %cmp29 to i32
  %cmp30 = icmp ne i32 %zot13, 0
  br i1 %cmp30, label %B86, label %B87

B86:                                              ; preds = %B85
  store i32 %ld34, i32* %def133, align 4
  br label %B83

B87:                                              ; preds = %B85
  store i32 %calc14, i32* %def133, align 4
  br label %B83

B83:                                              ; preds = %B87, %B86
  %ld255 = load i32, i32* %def133, align 4
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

B32:                                              ; preds = %B83
  %cmp9 = icmp eq i32 0, 0
  %zot3 = zext i1 %cmp9 to i32
  %cmp10 = icmp ne i32 %zot3, 0
  br i1 %cmp10, label %B33, label %B35

B33:                                              ; preds = %B32
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

B35:                                              ; preds = %B33, %B32
  br label %B89

B89:                                              ; preds = %B35
  %call104 = call i32 @getint()
  %calc15 = add i32 0, %call104
  br label %B88

B88:                                              ; preds = %B89
  %calc5 = add i32 10, %calc15
  br label %B91

B91:                                              ; preds = %B88
  call void @putint(i32 %calc5)
  call void @putch(i32 10)
  br label %B90

B90:                                              ; preds = %B91
  br label %B93

B93:                                              ; preds = %B90
  %call107 = call i32 @getint()
  %calc16 = add i32 0, %call107
  br label %B92

B92:                                              ; preds = %B93
  br label %B95

B95:                                              ; preds = %B92
  %call108 = call i32 @getint()
  %calc17 = add i32 0, %call108
  br label %B94

B94:                                              ; preds = %B95
  br label %B97

B97:                                              ; preds = %B94
  %call109 = call i32 @getint()
  %calc18 = add i32 0, %call109
  br label %B96

B96:                                              ; preds = %B97
  br label %B36

B36:                                              ; preds = %B96
  br label %B99

B99:                                              ; preds = %B36
  br label %B100

B100:                                             ; preds = %B99
  br label %B101

B101:                                             ; preds = %B100
  br label %B102

B102:                                             ; preds = %B101
  %cmp31 = icmp sgt i32 %calc16, %calc17
  %zot14 = zext i1 %cmp31 to i32
  %cmp32 = icmp ne i32 %zot14, 0
  br i1 %cmp32, label %B103, label %B104

B103:                                             ; preds = %B102
  store i32 %calc16, i32* %def134, align 4
  br label %B105

B104:                                             ; preds = %B102
  store i32 %calc17, i32* %def134, align 4
  br label %B105

B105:                                             ; preds = %B104, %B103
  %ld256 = load i32, i32* %def134, align 4
  br label %B106

B106:                                             ; preds = %B105
  br label %B107

B107:                                             ; preds = %B106
  %cmp33 = icmp slt i32 %calc17, %calc18
  %zot15 = zext i1 %cmp33 to i32
  %cmp34 = icmp ne i32 %zot15, 0
  br i1 %cmp34, label %B108, label %B109

B108:                                             ; preds = %B107
  store i32 %calc17, i32* %def135, align 4
  br label %B110

B109:                                             ; preds = %B107
  store i32 %calc18, i32* %def135, align 4
  br label %B110

B110:                                             ; preds = %B109, %B108
  %ld257 = load i32, i32* %def135, align 4
  %cmp35 = icmp eq i32 %ld256, %ld257
  br i1 %cmp35, label %B111, label %B112

B111:                                             ; preds = %B110
  store i32 0, i32* %def138, align 4
  store i32 0, i32* %def139, align 4
  store i32 %calc17, i32* %def140, align 4
  store i32 0, i32* %def141, align 4
  store i32 0, i32* %def142, align 4
  store i32 0, i32* %def143, align 4
  store i32 0, i32* %def144, align 4
  br label %B98

B112:                                             ; preds = %B110
  br label %B113

B113:                                             ; preds = %B112
  br label %B114

B114:                                             ; preds = %B113
  %cmp36 = icmp sgt i32 %calc16, %calc17
  %zot16 = zext i1 %cmp36 to i32
  %cmp37 = icmp ne i32 %zot16, 0
  br i1 %cmp37, label %B115, label %B116

B115:                                             ; preds = %B114
  store i32 %calc16, i32* %def136, align 4
  br label %B117

B116:                                             ; preds = %B114
  store i32 %calc17, i32* %def136, align 4
  br label %B117

B117:                                             ; preds = %B116, %B115
  %ld258 = load i32, i32* %def136, align 4
  br label %B118

B118:                                             ; preds = %B117
  br label %B119

B119:                                             ; preds = %B118
  %cmp38 = icmp slt i32 %calc16, %calc18
  %zot17 = zext i1 %cmp38 to i32
  %cmp39 = icmp ne i32 %zot17, 0
  br i1 %cmp39, label %B120, label %B121

B120:                                             ; preds = %B119
  store i32 %calc16, i32* %def137, align 4
  br label %B122

B121:                                             ; preds = %B119
  store i32 %calc18, i32* %def137, align 4
  br label %B122

B122:                                             ; preds = %B121, %B120
  %ld259 = load i32, i32* %def137, align 4
  %cmp40 = icmp ne i32 %ld258, %ld259
  br i1 %cmp40, label %B123, label %B124

B123:                                             ; preds = %B122
  store i32 %calc16, i32* %def138, align 4
  store i32 %ld259, i32* %def139, align 4
  store i32 %calc18, i32* %def140, align 4
  store i32 %calc17, i32* %def141, align 4
  store i32 %calc16, i32* %def142, align 4
  store i32 %ld258, i32* %def143, align 4
  store i32 %calc18, i32* %def144, align 4
  br label %B98

B124:                                             ; preds = %B122
  store i32 %calc16, i32* %def138, align 4
  store i32 %ld259, i32* %def139, align 4
  store i32 %calc16, i32* %def140, align 4
  store i32 %calc17, i32* %def141, align 4
  store i32 %calc16, i32* %def142, align 4
  store i32 %ld258, i32* %def143, align 4
  store i32 %calc18, i32* %def144, align 4
  br label %B98

B98:                                              ; preds = %B124, %B123, %B111
  %ld266 = load i32, i32* %def144, align 4
  %ld265 = load i32, i32* %def143, align 4
  %ld264 = load i32, i32* %def142, align 4
  %ld263 = load i32, i32* %def141, align 4
  %ld262 = load i32, i32* %def140, align 4
  %ld261 = load i32, i32* %def139, align 4
  %ld260 = load i32, i32* %def138, align 4
  %cmp11 = icmp sle i32 %ld262, %calc16
  %zot4 = zext i1 %cmp11 to i32
  %cmp12 = icmp ne i32 %zot4, 0
  br i1 %cmp12, label %B37, label %B40

B37:                                              ; preds = %B98
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

B126:                                             ; preds = %B37
  br label %B127

B127:                                             ; preds = %B126
  br label %B128

B128:                                             ; preds = %B127
  br label %B129

B129:                                             ; preds = %B128
  %cmp41 = icmp sgt i32 %calc16, %calc17
  %zot18 = zext i1 %cmp41 to i32
  %cmp42 = icmp ne i32 %zot18, 0
  br i1 %cmp42, label %B130, label %B131

B130:                                             ; preds = %B129
  store i32 %calc16, i32* %def145, align 4
  br label %B132

B131:                                             ; preds = %B129
  store i32 %calc17, i32* %def145, align 4
  br label %B132

B132:                                             ; preds = %B131, %B130
  %ld267 = load i32, i32* %def145, align 4
  br label %B133

B133:                                             ; preds = %B132
  br label %B134

B134:                                             ; preds = %B133
  %cmp43 = icmp slt i32 %calc17, %calc18
  %zot19 = zext i1 %cmp43 to i32
  %cmp44 = icmp ne i32 %zot19, 0
  br i1 %cmp44, label %B135, label %B136

B135:                                             ; preds = %B134
  store i32 %calc17, i32* %def146, align 4
  br label %B137

B136:                                             ; preds = %B134
  store i32 %calc18, i32* %def146, align 4
  br label %B137

B137:                                             ; preds = %B136, %B135
  %ld268 = load i32, i32* %def146, align 4
  %cmp45 = icmp eq i32 %ld267, %ld268
  br i1 %cmp45, label %B138, label %B139

B138:                                             ; preds = %B137
  store i32 0, i32* %def149, align 4
  store i32 %calc17, i32* %def150, align 4
  store i32 0, i32* %def151, align 4
  store i32 0, i32* %def152, align 4
  store i32 0, i32* %def153, align 4
  store i32 0, i32* %def154, align 4
  store i32 0, i32* %def155, align 4
  br label %B125

B139:                                             ; preds = %B137
  br label %B140

B140:                                             ; preds = %B139
  br label %B141

B141:                                             ; preds = %B140
  %cmp46 = icmp sgt i32 %calc16, %calc17
  %zot20 = zext i1 %cmp46 to i32
  %cmp47 = icmp ne i32 %zot20, 0
  br i1 %cmp47, label %B142, label %B143

B142:                                             ; preds = %B141
  store i32 %calc16, i32* %def147, align 4
  br label %B144

B143:                                             ; preds = %B141
  store i32 %calc17, i32* %def147, align 4
  br label %B144

B144:                                             ; preds = %B143, %B142
  %ld269 = load i32, i32* %def147, align 4
  br label %B145

B145:                                             ; preds = %B144
  br label %B146

B146:                                             ; preds = %B145
  %cmp48 = icmp slt i32 %calc16, %calc18
  %zot21 = zext i1 %cmp48 to i32
  %cmp49 = icmp ne i32 %zot21, 0
  br i1 %cmp49, label %B147, label %B148

B147:                                             ; preds = %B146
  store i32 %calc16, i32* %def148, align 4
  br label %B149

B148:                                             ; preds = %B146
  store i32 %calc18, i32* %def148, align 4
  br label %B149

B149:                                             ; preds = %B148, %B147
  %ld270 = load i32, i32* %def148, align 4
  %cmp50 = icmp ne i32 %ld269, %ld270
  br i1 %cmp50, label %B150, label %B151

B150:                                             ; preds = %B149
  store i32 %calc18, i32* %def149, align 4
  store i32 %calc18, i32* %def150, align 4
  store i32 %calc16, i32* %def151, align 4
  store i32 %ld270, i32* %def152, align 4
  store i32 %calc17, i32* %def153, align 4
  store i32 %calc16, i32* %def154, align 4
  store i32 %ld269, i32* %def155, align 4
  br label %B125

B151:                                             ; preds = %B149
  store i32 %calc18, i32* %def149, align 4
  store i32 %calc16, i32* %def150, align 4
  store i32 %calc16, i32* %def151, align 4
  store i32 %ld270, i32* %def152, align 4
  store i32 %calc17, i32* %def153, align 4
  store i32 %calc16, i32* %def154, align 4
  store i32 %ld269, i32* %def155, align 4
  br label %B125

B125:                                             ; preds = %B151, %B150, %B138
  %ld277 = load i32, i32* %def155, align 4
  %ld276 = load i32, i32* %def154, align 4
  %ld275 = load i32, i32* %def153, align 4
  %ld274 = load i32, i32* %def152, align 4
  %ld273 = load i32, i32* %def151, align 4
  %ld272 = load i32, i32* %def150, align 4
  %ld271 = load i32, i32* %def149, align 4
  %calc6 = sdiv i32 %ld272, 6
  %calc7 = mul i32 %calc6, %calc18
  %calc8 = srem i32 %calc7, 2
  %calc9 = add i32 %calc16, %calc8
  %calc10 = mul i32 0, -1
  %calc11 = sub i32 %calc9, %calc10
  call void @putint(i32 %calc11)
  call void @putch(i32 10)
  br label %B213

B40:                                              ; preds = %B98
  br label %B153

B153:                                             ; preds = %B40
  br label %B154

B154:                                             ; preds = %B153
  br label %B155

B155:                                             ; preds = %B154
  br label %B156

B156:                                             ; preds = %B155
  %cmp51 = icmp sgt i32 %calc16, %calc17
  %zot22 = zext i1 %cmp51 to i32
  %cmp52 = icmp ne i32 %zot22, 0
  br i1 %cmp52, label %B157, label %B158

B157:                                             ; preds = %B156
  store i32 %calc16, i32* %def156, align 4
  br label %B159

B158:                                             ; preds = %B156
  store i32 %calc17, i32* %def156, align 4
  br label %B159

B159:                                             ; preds = %B158, %B157
  %ld278 = load i32, i32* %def156, align 4
  br label %B160

B160:                                             ; preds = %B159
  br label %B161

B161:                                             ; preds = %B160
  %cmp53 = icmp slt i32 %calc17, %calc18
  %zot23 = zext i1 %cmp53 to i32
  %cmp54 = icmp ne i32 %zot23, 0
  br i1 %cmp54, label %B162, label %B163

B162:                                             ; preds = %B161
  store i32 %calc17, i32* %def157, align 4
  br label %B164

B163:                                             ; preds = %B161
  store i32 %calc18, i32* %def157, align 4
  br label %B164

B164:                                             ; preds = %B163, %B162
  %ld279 = load i32, i32* %def157, align 4
  %cmp55 = icmp eq i32 %ld278, %ld279
  br i1 %cmp55, label %B165, label %B166

B165:                                             ; preds = %B164
  store i32 0, i32* %def160, align 4
  store i32 0, i32* %def161, align 4
  store i32 0, i32* %def162, align 4
  store i32 0, i32* %def163, align 4
  store i32 0, i32* %def164, align 4
  store i32 0, i32* %def165, align 4
  store i32 %calc17, i32* %def166, align 4
  br label %B152

B166:                                             ; preds = %B164
  br label %B167

B167:                                             ; preds = %B166
  br label %B168

B168:                                             ; preds = %B167
  %cmp56 = icmp sgt i32 %calc16, %calc17
  %zot24 = zext i1 %cmp56 to i32
  %cmp57 = icmp ne i32 %zot24, 0
  br i1 %cmp57, label %B169, label %B170

B169:                                             ; preds = %B168
  store i32 %calc16, i32* %def158, align 4
  br label %B171

B170:                                             ; preds = %B168
  store i32 %calc17, i32* %def158, align 4
  br label %B171

B171:                                             ; preds = %B170, %B169
  %ld280 = load i32, i32* %def158, align 4
  br label %B172

B172:                                             ; preds = %B171
  br label %B173

B173:                                             ; preds = %B172
  %cmp58 = icmp slt i32 %calc16, %calc18
  %zot25 = zext i1 %cmp58 to i32
  %cmp59 = icmp ne i32 %zot25, 0
  br i1 %cmp59, label %B174, label %B175

B174:                                             ; preds = %B173
  store i32 %calc16, i32* %def159, align 4
  br label %B176

B175:                                             ; preds = %B173
  store i32 %calc18, i32* %def159, align 4
  br label %B176

B176:                                             ; preds = %B175, %B174
  %ld281 = load i32, i32* %def159, align 4
  %cmp60 = icmp ne i32 %ld280, %ld281
  br i1 %cmp60, label %B177, label %B178

B177:                                             ; preds = %B176
  store i32 %calc16, i32* %def160, align 4
  store i32 %ld281, i32* %def161, align 4
  store i32 %ld280, i32* %def162, align 4
  store i32 %calc18, i32* %def163, align 4
  store i32 %calc17, i32* %def164, align 4
  store i32 %calc16, i32* %def165, align 4
  store i32 %calc18, i32* %def166, align 4
  br label %B152

B178:                                             ; preds = %B176
  store i32 %calc16, i32* %def160, align 4
  store i32 %ld281, i32* %def161, align 4
  store i32 %ld280, i32* %def162, align 4
  store i32 %calc18, i32* %def163, align 4
  store i32 %calc17, i32* %def164, align 4
  store i32 %calc16, i32* %def165, align 4
  store i32 %calc16, i32* %def166, align 4
  br label %B152

B152:                                             ; preds = %B178, %B177, %B165
  %ld288 = load i32, i32* %def166, align 4
  %ld287 = load i32, i32* %def165, align 4
  %ld286 = load i32, i32* %def164, align 4
  %ld285 = load i32, i32* %def163, align 4
  %ld284 = load i32, i32* %def162, align 4
  %ld283 = load i32, i32* %def161, align 4
  %ld282 = load i32, i32* %def160, align 4
  %cmp13 = icmp slt i32 %ld288, %calc18
  %zot5 = zext i1 %cmp13 to i32
  %cmp14 = icmp ne i32 %zot5, 0
  br i1 %cmp14, label %B41, label %B42

B41:                                              ; preds = %B152
  call void @putch(i32 79)
  call void @putch(i32 104)
  call void @putch(i32 33)
  call void @putch(i32 63)
  call void @putch(i32 10)
  br label %B213

B42:                                              ; preds = %B152
  br label %B180

B180:                                             ; preds = %B42
  br label %B181

B181:                                             ; preds = %B180
  br label %B182

B182:                                             ; preds = %B181
  br label %B183

B183:                                             ; preds = %B182
  %cmp61 = icmp sgt i32 %calc16, %calc17
  %zot26 = zext i1 %cmp61 to i32
  %cmp62 = icmp ne i32 %zot26, 0
  br i1 %cmp62, label %B184, label %B185

B184:                                             ; preds = %B183
  store i32 %calc16, i32* %def167, align 4
  br label %B186

B185:                                             ; preds = %B183
  store i32 %calc17, i32* %def167, align 4
  br label %B186

B186:                                             ; preds = %B185, %B184
  %ld289 = load i32, i32* %def167, align 4
  br label %B187

B187:                                             ; preds = %B186
  br label %B188

B188:                                             ; preds = %B187
  %cmp63 = icmp slt i32 %calc17, %calc18
  %zot27 = zext i1 %cmp63 to i32
  %cmp64 = icmp ne i32 %zot27, 0
  br i1 %cmp64, label %B189, label %B190

B189:                                             ; preds = %B188
  store i32 %calc17, i32* %def168, align 4
  br label %B191

B190:                                             ; preds = %B188
  store i32 %calc18, i32* %def168, align 4
  br label %B191

B191:                                             ; preds = %B190, %B189
  %ld290 = load i32, i32* %def168, align 4
  %cmp65 = icmp eq i32 %ld289, %ld290
  br i1 %cmp65, label %B192, label %B193

B192:                                             ; preds = %B191
  store i32 0, i32* %def171, align 4
  store i32 0, i32* %def172, align 4
  store i32 %calc17, i32* %def173, align 4
  store i32 0, i32* %def174, align 4
  store i32 0, i32* %def175, align 4
  store i32 0, i32* %def176, align 4
  store i32 0, i32* %def177, align 4
  br label %B179

B193:                                             ; preds = %B191
  br label %B194

B194:                                             ; preds = %B193
  br label %B195

B195:                                             ; preds = %B194
  %cmp66 = icmp sgt i32 %calc16, %calc17
  %zot28 = zext i1 %cmp66 to i32
  %cmp67 = icmp ne i32 %zot28, 0
  br i1 %cmp67, label %B196, label %B197

B196:                                             ; preds = %B195
  store i32 %calc16, i32* %def169, align 4
  br label %B198

B197:                                             ; preds = %B195
  store i32 %calc17, i32* %def169, align 4
  br label %B198

B198:                                             ; preds = %B197, %B196
  %ld291 = load i32, i32* %def169, align 4
  br label %B199

B199:                                             ; preds = %B198
  br label %B200

B200:                                             ; preds = %B199
  %cmp68 = icmp slt i32 %calc16, %calc18
  %zot29 = zext i1 %cmp68 to i32
  %cmp69 = icmp ne i32 %zot29, 0
  br i1 %cmp69, label %B201, label %B202

B201:                                             ; preds = %B200
  store i32 %calc16, i32* %def170, align 4
  br label %B203

B202:                                             ; preds = %B200
  store i32 %calc18, i32* %def170, align 4
  br label %B203

B203:                                             ; preds = %B202, %B201
  %ld292 = load i32, i32* %def170, align 4
  %cmp70 = icmp ne i32 %ld291, %ld292
  br i1 %cmp70, label %B204, label %B205

B204:                                             ; preds = %B203
  store i32 %calc17, i32* %def171, align 4
  store i32 %calc16, i32* %def172, align 4
  store i32 %calc18, i32* %def173, align 4
  store i32 %calc16, i32* %def174, align 4
  store i32 %ld292, i32* %def175, align 4
  store i32 %ld291, i32* %def176, align 4
  store i32 %calc18, i32* %def177, align 4
  br label %B179

B205:                                             ; preds = %B203
  store i32 %calc17, i32* %def171, align 4
  store i32 %calc16, i32* %def172, align 4
  store i32 %calc16, i32* %def173, align 4
  store i32 %calc16, i32* %def174, align 4
  store i32 %ld292, i32* %def175, align 4
  store i32 %ld291, i32* %def176, align 4
  store i32 %calc18, i32* %def177, align 4
  br label %B179

B179:                                             ; preds = %B205, %B204, %B192
  %ld299 = load i32, i32* %def177, align 4
  %ld298 = load i32, i32* %def176, align 4
  %ld297 = load i32, i32* %def175, align 4
  %ld296 = load i32, i32* %def174, align 4
  %ld295 = load i32, i32* %def173, align 4
  %ld294 = load i32, i32* %def172, align 4
  %ld293 = load i32, i32* %def171, align 4
  %calc12 = srem i32 %ld295, 65535
  br label %B207

B207:                                             ; preds = %B179
  br label %B208

B208:                                             ; preds = %B207
  %cmp71 = icmp sgt i32 %calc12, 20
  %zot30 = zext i1 %cmp71 to i32
  %cmp72 = icmp ne i32 %zot30, 0
  store i32 %ld282, i32* %def178, align 4
  store i32 %ld283, i32* %def179, align 4
  store i32 %ld284, i32* %def180, align 4
  store i32 %ld285, i32* %def181, align 4
  store i32 %ld286, i32* %def182, align 4
  store i32 %ld287, i32* %def183, align 4
  store i32 %calc17, i32* %def184, align 4
  store i32 %ld279, i32* %def185, align 4
  store i32 %calc15, i32* %def186, align 4
  store i32 %ld288, i32* %def187, align 4
  store i32 %calc18, i32* %def188, align 4
  store i32 %ld290, i32* %def189, align 4
  store i32 %ld293, i32* %def190, align 4
  store i32 %calc18, i32* %def191, align 4
  store i32 %calc17, i32* %def192, align 4
  store i32 %calc16, i32* %def193, align 4
  store i32 %ld289, i32* %def194, align 4
  store i32 %calc16, i32* %def195, align 4
  store i32 %calc17, i32* %def196, align 4
  store i32 %calc17, i32* %def197, align 4
  store i32 %calc18, i32* %def198, align 4
  store i32 0, i32* %def199, align 4
  store i32 %ld294, i32* %def200, align 4
  store i32 %calc16, i32* %def201, align 4
  store i32 -1, i32* %def202, align 4
  store i32 %calc17, i32* %def203, align 4
  store i32 %calc14, i32* %def204, align 4
  store i32 %ld252, i32* %def205, align 4
  store i32 %ld254, i32* %def206, align 4
  store i32 %call103, i32* %def207, align 4
  store i32 %calc14, i32* %def208, align 4
  store i32 %call104, i32* %def209, align 4
  store i32 %calc15, i32* %def210, align 4
  store i32 %calc5, i32* %def211, align 4
  store i32 %call107, i32* %def212, align 4
  store i32 %ld34, i32* %def213, align 4
  store i32 %ld255, i32* %def214, align 4
  store i32 %ld252, i32* %def215, align 4
  store i32 %call102, i32* %def216, align 4
  store i32 %ld32, i32* %def217, align 4
  store i32 88, i32* %def218, align 4
  store i32 %calc13, i32* %def219, align 4
  store i32 %ld33, i32* %def220, align 4
  store i32 %ld253, i32* %def221, align 4
  store i32 %ld253, i32* %def222, align 4
  store i32 %calc13, i32* %def223, align 4
  store i32 %calc18, i32* %def224, align 4
  store i32 %calc17, i32* %def225, align 4
  store i32 %ld257, i32* %def226, align 4
  store i32 %ld260, i32* %def227, align 4
  store i32 %ld261, i32* %def228, align 4
  store i32 %ld262, i32* %def229, align 4
  store i32 %ld263, i32* %def230, align 4
  store i32 %ld264, i32* %def231, align 4
  store i32 %ld265, i32* %def232, align 4
  store i32 %ld266, i32* %def233, align 4
  store i32 %calc16, i32* %def234, align 4
  store i32 %call108, i32* %def235, align 4
  store i32 %calc17, i32* %def236, align 4
  store i32 %call109, i32* %def237, align 4
  store i32 %calc16, i32* %def238, align 4
  store i32 %calc17, i32* %def239, align 4
  store i32 %calc16, i32* %def240, align 4
  store i32 %ld256, i32* %def241, align 4
  store i32 %calc18, i32* %def242, align 4
  store i32 %calc18, i32* %def243, align 4
  store i32 %calc17, i32* %def244, align 4
  store i32 %calc12, i32* %def245, align 4
  store i32 1, i32* %def246, align 4
  store i32 %ld295, i32* %def247, align 4
  store i32 %calc12, i32* %def248, align 4
  store i32 %ld296, i32* %def249, align 4
  store i32 %ld297, i32* %def250, align 4
  store i32 %calc17, i32* %def251, align 4
  store i32 %calc16, i32* %def252, align 4
  store i32 %ld278, i32* %def253, align 4
  store i32 %calc18, i32* %def254, align 4
  store i32 %calc18, i32* %def255, align 4
  store i32 %calc17, i32* %def256, align 4
  store i32 %calc16, i32* %def257, align 4
  store i32 %ld298, i32* %def258, align 4
  store i32 %ld299, i32* %def259, align 4
  br i1 %cmp72, label %B209, label %B210

B209:                                             ; preds = %B208
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
  store i32 %ld282, i32* %def260, align 4
  store i32 %ld283, i32* %def261, align 4
  store i32 %ld284, i32* %def262, align 4
  store i32 %ld285, i32* %def263, align 4
  store i32 %ld286, i32* %def264, align 4
  store i32 %ld287, i32* %def265, align 4
  store i32 %calc17, i32* %def266, align 4
  store i32 %ld279, i32* %def267, align 4
  store i32 %calc15, i32* %def268, align 4
  store i32 %ld288, i32* %def269, align 4
  store i32 %calc18, i32* %def270, align 4
  store i32 %ld290, i32* %def271, align 4
  store i32 %ld293, i32* %def272, align 4
  store i32 %calc18, i32* %def273, align 4
  store i32 %calc17, i32* %def274, align 4
  store i32 %calc16, i32* %def275, align 4
  store i32 %ld289, i32* %def276, align 4
  store i32 %calc16, i32* %def277, align 4
  store i32 %calc17, i32* %def278, align 4
  store i32 %calc17, i32* %def279, align 4
  store i32 %calc18, i32* %def280, align 4
  store i32 0, i32* %def281, align 4
  store i32 %ld294, i32* %def282, align 4
  store i32 %calc16, i32* %def283, align 4
  store i32 -1, i32* %def284, align 4
  store i32 %calc17, i32* %def285, align 4
  store i32 %calc14, i32* %def286, align 4
  store i32 %ld252, i32* %def287, align 4
  store i32 %ld254, i32* %def288, align 4
  store i32 %call103, i32* %def289, align 4
  store i32 %calc14, i32* %def290, align 4
  store i32 %call104, i32* %def291, align 4
  store i32 %calc15, i32* %def292, align 4
  store i32 %calc5, i32* %def293, align 4
  store i32 %call107, i32* %def294, align 4
  store i32 %ld34, i32* %def295, align 4
  store i32 %ld255, i32* %def296, align 4
  store i32 %ld252, i32* %def297, align 4
  store i32 %call102, i32* %def298, align 4
  store i32 %ld32, i32* %def299, align 4
  store i32 88, i32* %def300, align 4
  store i32 %calc13, i32* %def301, align 4
  store i32 %ld33, i32* %def302, align 4
  store i32 %ld253, i32* %def303, align 4
  store i32 %ld253, i32* %def304, align 4
  store i32 %calc13, i32* %def305, align 4
  store i32 %calc18, i32* %def306, align 4
  store i32 %calc17, i32* %def307, align 4
  store i32 %ld257, i32* %def308, align 4
  store i32 %ld260, i32* %def309, align 4
  store i32 %ld261, i32* %def310, align 4
  store i32 %ld262, i32* %def311, align 4
  store i32 %ld263, i32* %def312, align 4
  store i32 %ld264, i32* %def313, align 4
  store i32 %ld265, i32* %def314, align 4
  store i32 %ld266, i32* %def315, align 4
  store i32 %calc16, i32* %def316, align 4
  store i32 %call108, i32* %def317, align 4
  store i32 %calc17, i32* %def318, align 4
  store i32 %call109, i32* %def319, align 4
  store i32 %calc16, i32* %def320, align 4
  store i32 %calc17, i32* %def321, align 4
  store i32 %calc16, i32* %def322, align 4
  store i32 %ld256, i32* %def323, align 4
  store i32 %calc18, i32* %def324, align 4
  store i32 %calc18, i32* %def325, align 4
  store i32 %calc17, i32* %def326, align 4
  store i32 -1, i32* %def327, align 4
  store i32 %calc12, i32* %def328, align 4
  store i32 1, i32* %def329, align 4
  store i32 %ld295, i32* %def330, align 4
  store i32 %calc12, i32* %def331, align 4
  store i32 %ld296, i32* %def332, align 4
  store i32 %ld297, i32* %def333, align 4
  store i32 %calc17, i32* %def334, align 4
  store i32 %calc16, i32* %def335, align 4
  store i32 %ld278, i32* %def336, align 4
  store i32 %calc18, i32* %def337, align 4
  store i32 %calc18, i32* %def338, align 4
  store i32 %calc17, i32* %def339, align 4
  store i32 %calc16, i32* %def340, align 4
  store i32 %ld298, i32* %def341, align 4
  store i32 %ld299, i32* %def342, align 4
  br label %B206

B210:                                             ; preds = %B211, %B208
  %ld381 = load i32, i32* %def259, align 4
  %ld380 = load i32, i32* %def258, align 4
  %ld379 = load i32, i32* %def257, align 4
  %ld378 = load i32, i32* %def256, align 4
  %ld377 = load i32, i32* %def255, align 4
  %ld376 = load i32, i32* %def254, align 4
  %ld375 = load i32, i32* %def253, align 4
  %ld374 = load i32, i32* %def252, align 4
  %ld373 = load i32, i32* %def251, align 4
  %ld372 = load i32, i32* %def250, align 4
  %ld371 = load i32, i32* %def249, align 4
  %ld370 = load i32, i32* %def248, align 4
  %ld369 = load i32, i32* %def247, align 4
  %ld368 = load i32, i32* %def246, align 4
  %ld367 = load i32, i32* %def245, align 4
  %ld366 = load i32, i32* %def244, align 4
  %ld365 = load i32, i32* %def243, align 4
  %ld364 = load i32, i32* %def242, align 4
  %ld363 = load i32, i32* %def241, align 4
  %ld362 = load i32, i32* %def240, align 4
  %ld361 = load i32, i32* %def239, align 4
  %ld360 = load i32, i32* %def238, align 4
  %ld359 = load i32, i32* %def237, align 4
  %ld358 = load i32, i32* %def236, align 4
  %ld357 = load i32, i32* %def235, align 4
  %ld356 = load i32, i32* %def234, align 4
  %ld355 = load i32, i32* %def233, align 4
  %ld354 = load i32, i32* %def232, align 4
  %ld353 = load i32, i32* %def231, align 4
  %ld352 = load i32, i32* %def230, align 4
  %ld351 = load i32, i32* %def229, align 4
  %ld350 = load i32, i32* %def228, align 4
  %ld349 = load i32, i32* %def227, align 4
  %ld348 = load i32, i32* %def226, align 4
  %ld347 = load i32, i32* %def225, align 4
  %ld346 = load i32, i32* %def224, align 4
  %ld345 = load i32, i32* %def223, align 4
  %ld344 = load i32, i32* %def222, align 4
  %ld343 = load i32, i32* %def221, align 4
  %ld342 = load i32, i32* %def220, align 4
  %ld341 = load i32, i32* %def219, align 4
  %ld340 = load i32, i32* %def218, align 4
  %ld339 = load i32, i32* %def217, align 4
  %ld338 = load i32, i32* %def216, align 4
  %ld337 = load i32, i32* %def215, align 4
  %ld336 = load i32, i32* %def214, align 4
  %ld335 = load i32, i32* %def213, align 4
  %ld334 = load i32, i32* %def212, align 4
  %ld333 = load i32, i32* %def211, align 4
  %ld332 = load i32, i32* %def210, align 4
  %ld331 = load i32, i32* %def209, align 4
  %ld330 = load i32, i32* %def208, align 4
  %ld329 = load i32, i32* %def207, align 4
  %ld328 = load i32, i32* %def206, align 4
  %ld327 = load i32, i32* %def205, align 4
  %ld326 = load i32, i32* %def204, align 4
  %ld325 = load i32, i32* %def203, align 4
  %ld324 = load i32, i32* %def202, align 4
  %ld323 = load i32, i32* %def201, align 4
  %ld322 = load i32, i32* %def200, align 4
  %ld321 = load i32, i32* %def199, align 4
  %ld320 = load i32, i32* %def198, align 4
  %ld319 = load i32, i32* %def197, align 4
  %ld318 = load i32, i32* %def196, align 4
  %ld317 = load i32, i32* %def195, align 4
  %ld316 = load i32, i32* %def194, align 4
  %ld315 = load i32, i32* %def193, align 4
  %ld314 = load i32, i32* %def192, align 4
  %ld313 = load i32, i32* %def191, align 4
  %ld312 = load i32, i32* %def190, align 4
  %ld311 = load i32, i32* %def189, align 4
  %ld310 = load i32, i32* %def188, align 4
  %ld309 = load i32, i32* %def187, align 4
  %ld308 = load i32, i32* %def186, align 4
  %ld307 = load i32, i32* %def185, align 4
  %ld306 = load i32, i32* %def184, align 4
  %ld305 = load i32, i32* %def183, align 4
  %ld304 = load i32, i32* %def182, align 4
  %ld303 = load i32, i32* %def181, align 4
  %ld302 = load i32, i32* %def180, align 4
  %ld301 = load i32, i32* %def179, align 4
  %ld300 = load i32, i32* %def178, align 4
  %cmp73 = icmp ne i32 %ld367, 0
  br i1 %cmp73, label %B211, label %B212

B211:                                             ; preds = %B210
  %calc19 = mul i32 %ld368, %ld367
  %calc20 = add i32 -1, %ld367
  store i32 %ld300, i32* %def178, align 4
  store i32 %ld301, i32* %def179, align 4
  store i32 %ld302, i32* %def180, align 4
  store i32 %ld303, i32* %def181, align 4
  store i32 %ld304, i32* %def182, align 4
  store i32 %ld305, i32* %def183, align 4
  store i32 %ld306, i32* %def184, align 4
  store i32 %ld307, i32* %def185, align 4
  store i32 %ld308, i32* %def186, align 4
  store i32 %ld309, i32* %def187, align 4
  store i32 %ld310, i32* %def188, align 4
  store i32 %ld311, i32* %def189, align 4
  store i32 %ld312, i32* %def190, align 4
  store i32 %ld313, i32* %def191, align 4
  store i32 %ld314, i32* %def192, align 4
  store i32 %ld315, i32* %def193, align 4
  store i32 %ld316, i32* %def194, align 4
  store i32 %ld317, i32* %def195, align 4
  store i32 %ld318, i32* %def196, align 4
  store i32 %ld319, i32* %def197, align 4
  store i32 %ld320, i32* %def198, align 4
  store i32 %ld321, i32* %def199, align 4
  store i32 %ld322, i32* %def200, align 4
  store i32 %ld323, i32* %def201, align 4
  store i32 %ld324, i32* %def202, align 4
  store i32 %ld325, i32* %def203, align 4
  store i32 %ld326, i32* %def204, align 4
  store i32 %ld327, i32* %def205, align 4
  store i32 %ld328, i32* %def206, align 4
  store i32 %ld329, i32* %def207, align 4
  store i32 %ld330, i32* %def208, align 4
  store i32 %ld331, i32* %def209, align 4
  store i32 %ld332, i32* %def210, align 4
  store i32 %ld333, i32* %def211, align 4
  store i32 %ld334, i32* %def212, align 4
  store i32 %ld335, i32* %def213, align 4
  store i32 %ld336, i32* %def214, align 4
  store i32 %ld337, i32* %def215, align 4
  store i32 %ld338, i32* %def216, align 4
  store i32 %ld339, i32* %def217, align 4
  store i32 %ld340, i32* %def218, align 4
  store i32 %ld341, i32* %def219, align 4
  store i32 %ld342, i32* %def220, align 4
  store i32 %ld343, i32* %def221, align 4
  store i32 %ld344, i32* %def222, align 4
  store i32 %ld345, i32* %def223, align 4
  store i32 %ld346, i32* %def224, align 4
  store i32 %ld347, i32* %def225, align 4
  store i32 %ld348, i32* %def226, align 4
  store i32 %ld349, i32* %def227, align 4
  store i32 %ld350, i32* %def228, align 4
  store i32 %ld351, i32* %def229, align 4
  store i32 %ld352, i32* %def230, align 4
  store i32 %ld353, i32* %def231, align 4
  store i32 %ld354, i32* %def232, align 4
  store i32 %ld355, i32* %def233, align 4
  store i32 %ld356, i32* %def234, align 4
  store i32 %ld357, i32* %def235, align 4
  store i32 %ld358, i32* %def236, align 4
  store i32 %ld359, i32* %def237, align 4
  store i32 %ld360, i32* %def238, align 4
  store i32 %ld361, i32* %def239, align 4
  store i32 %ld362, i32* %def240, align 4
  store i32 %ld363, i32* %def241, align 4
  store i32 %ld364, i32* %def242, align 4
  store i32 %ld365, i32* %def243, align 4
  store i32 %ld366, i32* %def244, align 4
  store i32 %calc20, i32* %def245, align 4
  store i32 %calc19, i32* %def246, align 4
  store i32 %ld369, i32* %def247, align 4
  store i32 %ld370, i32* %def248, align 4
  store i32 %ld371, i32* %def249, align 4
  store i32 %ld372, i32* %def250, align 4
  store i32 %ld373, i32* %def251, align 4
  store i32 %ld374, i32* %def252, align 4
  store i32 %ld375, i32* %def253, align 4
  store i32 %ld376, i32* %def254, align 4
  store i32 %ld377, i32* %def255, align 4
  store i32 %ld378, i32* %def256, align 4
  store i32 %ld379, i32* %def257, align 4
  store i32 %ld380, i32* %def258, align 4
  store i32 %ld381, i32* %def259, align 4
  br label %B210

B212:                                             ; preds = %B210
  store i32 %ld300, i32* %def260, align 4
  store i32 %ld301, i32* %def261, align 4
  store i32 %ld302, i32* %def262, align 4
  store i32 %ld303, i32* %def263, align 4
  store i32 %ld304, i32* %def264, align 4
  store i32 %ld305, i32* %def265, align 4
  store i32 %ld306, i32* %def266, align 4
  store i32 %ld307, i32* %def267, align 4
  store i32 %ld308, i32* %def268, align 4
  store i32 %ld309, i32* %def269, align 4
  store i32 %ld310, i32* %def270, align 4
  store i32 %ld311, i32* %def271, align 4
  store i32 %ld312, i32* %def272, align 4
  store i32 %ld313, i32* %def273, align 4
  store i32 %ld314, i32* %def274, align 4
  store i32 %ld315, i32* %def275, align 4
  store i32 %ld316, i32* %def276, align 4
  store i32 %ld317, i32* %def277, align 4
  store i32 %ld318, i32* %def278, align 4
  store i32 %ld319, i32* %def279, align 4
  store i32 %ld320, i32* %def280, align 4
  store i32 %ld321, i32* %def281, align 4
  store i32 %ld322, i32* %def282, align 4
  store i32 %ld323, i32* %def283, align 4
  store i32 %ld324, i32* %def284, align 4
  store i32 %ld325, i32* %def285, align 4
  store i32 %ld326, i32* %def286, align 4
  store i32 %ld327, i32* %def287, align 4
  store i32 %ld328, i32* %def288, align 4
  store i32 %ld329, i32* %def289, align 4
  store i32 %ld330, i32* %def290, align 4
  store i32 %ld331, i32* %def291, align 4
  store i32 %ld332, i32* %def292, align 4
  store i32 %ld333, i32* %def293, align 4
  store i32 %ld334, i32* %def294, align 4
  store i32 %ld335, i32* %def295, align 4
  store i32 %ld336, i32* %def296, align 4
  store i32 %ld337, i32* %def297, align 4
  store i32 %ld338, i32* %def298, align 4
  store i32 %ld339, i32* %def299, align 4
  store i32 %ld340, i32* %def300, align 4
  store i32 %ld341, i32* %def301, align 4
  store i32 %ld342, i32* %def302, align 4
  store i32 %ld343, i32* %def303, align 4
  store i32 %ld344, i32* %def304, align 4
  store i32 %ld345, i32* %def305, align 4
  store i32 %ld346, i32* %def306, align 4
  store i32 %ld347, i32* %def307, align 4
  store i32 %ld348, i32* %def308, align 4
  store i32 %ld349, i32* %def309, align 4
  store i32 %ld350, i32* %def310, align 4
  store i32 %ld351, i32* %def311, align 4
  store i32 %ld352, i32* %def312, align 4
  store i32 %ld353, i32* %def313, align 4
  store i32 %ld354, i32* %def314, align 4
  store i32 %ld355, i32* %def315, align 4
  store i32 %ld356, i32* %def316, align 4
  store i32 %ld357, i32* %def317, align 4
  store i32 %ld358, i32* %def318, align 4
  store i32 %ld359, i32* %def319, align 4
  store i32 %ld360, i32* %def320, align 4
  store i32 %ld361, i32* %def321, align 4
  store i32 %ld362, i32* %def322, align 4
  store i32 %ld363, i32* %def323, align 4
  store i32 %ld364, i32* %def324, align 4
  store i32 %ld365, i32* %def325, align 4
  store i32 %ld366, i32* %def326, align 4
  store i32 %ld368, i32* %def327, align 4
  store i32 %ld367, i32* %def328, align 4
  store i32 %ld368, i32* %def329, align 4
  store i32 %ld369, i32* %def330, align 4
  store i32 %ld370, i32* %def331, align 4
  store i32 %ld371, i32* %def332, align 4
  store i32 %ld372, i32* %def333, align 4
  store i32 %ld373, i32* %def334, align 4
  store i32 %ld374, i32* %def335, align 4
  store i32 %ld375, i32* %def336, align 4
  store i32 %ld376, i32* %def337, align 4
  store i32 %ld377, i32* %def338, align 4
  store i32 %ld378, i32* %def339, align 4
  store i32 %ld379, i32* %def340, align 4
  store i32 %ld380, i32* %def341, align 4
  store i32 %ld381, i32* %def342, align 4
  br label %B206

B206:                                             ; preds = %B212, %B209
  %ld464 = load i32, i32* %def342, align 4
  %ld463 = load i32, i32* %def341, align 4
  %ld462 = load i32, i32* %def340, align 4
  %ld461 = load i32, i32* %def339, align 4
  %ld460 = load i32, i32* %def338, align 4
  %ld459 = load i32, i32* %def337, align 4
  %ld458 = load i32, i32* %def336, align 4
  %ld457 = load i32, i32* %def335, align 4
  %ld456 = load i32, i32* %def334, align 4
  %ld455 = load i32, i32* %def333, align 4
  %ld454 = load i32, i32* %def332, align 4
  %ld453 = load i32, i32* %def331, align 4
  %ld452 = load i32, i32* %def330, align 4
  %ld451 = load i32, i32* %def329, align 4
  %ld450 = load i32, i32* %def328, align 4
  %ld449 = load i32, i32* %def327, align 4
  %ld448 = load i32, i32* %def326, align 4
  %ld447 = load i32, i32* %def325, align 4
  %ld446 = load i32, i32* %def324, align 4
  %ld445 = load i32, i32* %def323, align 4
  %ld444 = load i32, i32* %def322, align 4
  %ld443 = load i32, i32* %def321, align 4
  %ld442 = load i32, i32* %def320, align 4
  %ld441 = load i32, i32* %def319, align 4
  %ld440 = load i32, i32* %def318, align 4
  %ld439 = load i32, i32* %def317, align 4
  %ld438 = load i32, i32* %def316, align 4
  %ld437 = load i32, i32* %def315, align 4
  %ld436 = load i32, i32* %def314, align 4
  %ld435 = load i32, i32* %def313, align 4
  %ld434 = load i32, i32* %def312, align 4
  %ld433 = load i32, i32* %def311, align 4
  %ld432 = load i32, i32* %def310, align 4
  %ld431 = load i32, i32* %def309, align 4
  %ld430 = load i32, i32* %def308, align 4
  %ld429 = load i32, i32* %def307, align 4
  %ld428 = load i32, i32* %def306, align 4
  %ld427 = load i32, i32* %def305, align 4
  %ld426 = load i32, i32* %def304, align 4
  %ld425 = load i32, i32* %def303, align 4
  %ld424 = load i32, i32* %def302, align 4
  %ld423 = load i32, i32* %def301, align 4
  %ld422 = load i32, i32* %def300, align 4
  %ld421 = load i32, i32* %def299, align 4
  %ld420 = load i32, i32* %def298, align 4
  %ld419 = load i32, i32* %def297, align 4
  %ld418 = load i32, i32* %def296, align 4
  %ld417 = load i32, i32* %def295, align 4
  %ld416 = load i32, i32* %def294, align 4
  %ld415 = load i32, i32* %def293, align 4
  %ld414 = load i32, i32* %def292, align 4
  %ld413 = load i32, i32* %def291, align 4
  %ld412 = load i32, i32* %def290, align 4
  %ld411 = load i32, i32* %def289, align 4
  %ld410 = load i32, i32* %def288, align 4
  %ld409 = load i32, i32* %def287, align 4
  %ld408 = load i32, i32* %def286, align 4
  %ld407 = load i32, i32* %def285, align 4
  %ld406 = load i32, i32* %def284, align 4
  %ld405 = load i32, i32* %def283, align 4
  %ld404 = load i32, i32* %def282, align 4
  %ld403 = load i32, i32* %def281, align 4
  %ld402 = load i32, i32* %def280, align 4
  %ld401 = load i32, i32* %def279, align 4
  %ld400 = load i32, i32* %def278, align 4
  %ld399 = load i32, i32* %def277, align 4
  %ld398 = load i32, i32* %def276, align 4
  %ld397 = load i32, i32* %def275, align 4
  %ld396 = load i32, i32* %def274, align 4
  %ld395 = load i32, i32* %def273, align 4
  %ld394 = load i32, i32* %def272, align 4
  %ld393 = load i32, i32* %def271, align 4
  %ld392 = load i32, i32* %def270, align 4
  %ld391 = load i32, i32* %def269, align 4
  %ld390 = load i32, i32* %def268, align 4
  %ld389 = load i32, i32* %def267, align 4
  %ld388 = load i32, i32* %def266, align 4
  %ld387 = load i32, i32* %def265, align 4
  %ld386 = load i32, i32* %def264, align 4
  %ld385 = load i32, i32* %def263, align 4
  %ld384 = load i32, i32* %def262, align 4
  %ld383 = load i32, i32* %def261, align 4
  %ld382 = load i32, i32* %def260, align 4
  call void @putint(i32 %ld449)
  call void @putch(i32 10)
  br label %B213

B213:                                             ; preds = %B206, %B41, %B125
  ret i32 0
}

; Function Attrs: noinline nounwind optnone uwtable
define dso_local i32 @getint() #0 {
  %1 = alloca i32, align 4
  %2 = call i32 (i8*, ...) @__isoc99_scanf(i8* noundef getelementptr inbounds ([3 x i8], [3 x i8]* @.str, i64 0, i64 0), i32* noundef %1)
  %3 = load i32, i32* %1, align 4
  ret i32 %3
}

declare i32 @__isoc99_scanf(i8* noundef, ...) #1

; Function Attrs: noinline nounwind optnone uwtable
define dso_local i32 @getch() #0 {
  %1 = alloca i8, align 1
  %2 = call i32 (i8*, ...) @__isoc99_scanf(i8* noundef getelementptr inbounds ([3 x i8], [3 x i8]* @.str.1, i64 0, i64 0), i8* noundef %1)
  %3 = load i8, i8* %1, align 1
  %4 = sext i8 %3 to i32
  ret i32 %4
}

; Function Attrs: noinline nounwind optnone uwtable
define dso_local i32 @getarray(i32* noundef %0) #0 {
  %2 = alloca i32*, align 8
  %3 = alloca i32, align 4
  %4 = alloca i32, align 4
  store i32* %0, i32** %2, align 8
  %5 = call i32 (i8*, ...) @__isoc99_scanf(i8* noundef getelementptr inbounds ([3 x i8], [3 x i8]* @.str, i64 0, i64 0), i32* noundef %3)
  store i32 0, i32* %4, align 4
  br label %6

6:                                                ; preds = %16, %1
  %7 = load i32, i32* %4, align 4
  %8 = load i32, i32* %3, align 4
  %9 = icmp slt i32 %7, %8
  br i1 %9, label %10, label %19

10:                                               ; preds = %6
  %11 = load i32*, i32** %2, align 8
  %12 = load i32, i32* %4, align 4
  %13 = sext i32 %12 to i64
  %14 = getelementptr inbounds i32, i32* %11, i64 %13
  %15 = call i32 (i8*, ...) @__isoc99_scanf(i8* noundef getelementptr inbounds ([3 x i8], [3 x i8]* @.str, i64 0, i64 0), i32* noundef %14)
  br label %16

16:                                               ; preds = %10
  %17 = load i32, i32* %4, align 4
  %18 = add nsw i32 %17, 1
  store i32 %18, i32* %4, align 4
  br label %6, !llvm.loop !6

19:                                               ; preds = %6
  %20 = load i32, i32* %3, align 4
  ret i32 %20
}

; Function Attrs: noinline nounwind optnone uwtable
define dso_local void @putint(i32 noundef %0) #0 {
  %2 = alloca i32, align 4
  store i32 %0, i32* %2, align 4
  %3 = load i32, i32* %2, align 4
  %4 = call i32 (i8*, ...) @printf(i8* noundef getelementptr inbounds ([3 x i8], [3 x i8]* @.str, i64 0, i64 0), i32 noundef %3)
  ret void
}

declare i32 @printf(i8* noundef, ...) #1

; Function Attrs: noinline nounwind optnone uwtable
define dso_local void @putch(i32 noundef %0) #0 {
  %2 = alloca i32, align 4
  store i32 %0, i32* %2, align 4
  %3 = load i32, i32* %2, align 4
  %4 = call i32 (i8*, ...) @printf(i8* noundef getelementptr inbounds ([3 x i8], [3 x i8]* @.str.1, i64 0, i64 0), i32 noundef %3)
  ret void
}

; Function Attrs: noinline nounwind optnone uwtable
define dso_local void @putarray(i32 noundef %0, i32* noundef %1) #0 {
  %3 = alloca i32, align 4
  %4 = alloca i32*, align 8
  %5 = alloca i32, align 4
  store i32 %0, i32* %3, align 4
  store i32* %1, i32** %4, align 8
  %6 = load i32, i32* %3, align 4
  %7 = call i32 (i8*, ...) @printf(i8* noundef getelementptr inbounds ([4 x i8], [4 x i8]* @.str.2, i64 0, i64 0), i32 noundef %6)
  store i32 0, i32* %5, align 4
  br label %8

8:                                                ; preds = %19, %2
  %9 = load i32, i32* %5, align 4
  %10 = load i32, i32* %3, align 4
  %11 = icmp slt i32 %9, %10
  br i1 %11, label %12, label %22

12:                                               ; preds = %8
  %13 = load i32*, i32** %4, align 8
  %14 = load i32, i32* %5, align 4
  %15 = sext i32 %14 to i64
  %16 = getelementptr inbounds i32, i32* %13, i64 %15
  %17 = load i32, i32* %16, align 4
  %18 = call i32 (i8*, ...) @printf(i8* noundef getelementptr inbounds ([4 x i8], [4 x i8]* @.str.3, i64 0, i64 0), i32 noundef %17)
  br label %19

19:                                               ; preds = %12
  %20 = load i32, i32* %5, align 4
  %21 = add nsw i32 %20, 1
  store i32 %21, i32* %5, align 4
  br label %8, !llvm.loop !8

22:                                               ; preds = %8
  %23 = call i32 (i8*, ...) @printf(i8* noundef getelementptr inbounds ([2 x i8], [2 x i8]* @.str.4, i64 0, i64 0))
  ret void
}

; Function Attrs: noinline nounwind optnone uwtable
define dso_local void @putstr(i8* noundef %0) #0 {
  %2 = alloca i8*, align 8
  store i8* %0, i8** %2, align 8
  %3 = load i8*, i8** %2, align 8
  %4 = call i32 (i8*, ...) @printf(i8* noundef getelementptr inbounds ([3 x i8], [3 x i8]* @.str.5, i64 0, i64 0), i8* noundef %3)
  ret void
}

attributes #0 = { noinline nounwind optnone uwtable "frame-pointer"="all" "min-legal-vector-width"="0" "no-trapping-math"="true" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+cx8,+fxsr,+mmx,+sse,+sse2,+x87" "tune-cpu"="generic" }
attributes #1 = { "frame-pointer"="all" "no-trapping-math"="true" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+cx8,+fxsr,+mmx,+sse,+sse2,+x87" "tune-cpu"="generic" }

!llvm.ident = !{!0}
!llvm.module.flags = !{!1, !2, !3, !4, !5}

!0 = !{!"Ubuntu clang version 14.0.0-1ubuntu1.1"}
!1 = !{i32 1, !"wchar_size", i32 4}
!2 = !{i32 7, !"PIC Level", i32 2}
!3 = !{i32 7, !"PIE Level", i32 2}
!4 = !{i32 7, !"uwtable", i32 1}
!5 = !{i32 7, !"frame-pointer", i32 2}
!6 = distinct !{!6, !7}
!7 = !{!"llvm.loop.mustprogress"}
!8 = distinct !{!8, !7}
