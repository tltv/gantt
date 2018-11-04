var $wnd = $wnd || window.parent;
var __gwtModuleFunction = $wnd.org_tltv_gantt_GanttLib;
var $sendStats = __gwtModuleFunction.__sendStats;
$sendStats('moduleStartup', 'moduleEvalStart');
var $gwt_version = "8.1.7";
var $strongName = '457C399320FA4BB63218F63892AB2217';
var $gwt = {};
var $doc = $wnd.document;
var $moduleName, $moduleBase;
function __gwtStartLoadingFragment(frag) {
var fragFile = 'deferredjs/' + $strongName + '/' + frag + '.cache.js';
return __gwtModuleFunction.__startLoadingFragment(fragFile);
}
function __gwtInstallCode(code) {return __gwtModuleFunction.__installRunAsyncCode(code);}
function __gwt_isKnownPropertyValue(propName, propValue) {
return __gwtModuleFunction.__gwt_isKnownPropertyValue(propName, propValue);
}
function __gwt_getMetaProperty(name) {
return __gwtModuleFunction.__gwt_getMetaProperty(name);
}
var $stats = $wnd.__gwtStatsEvent ? function(a) {
return $wnd.__gwtStatsEvent && $wnd.__gwtStatsEvent(a);
} : null;
var $sessionId = $wnd.__gwtStatsSessionId ? $wnd.__gwtStatsSessionId : null;
var $intern_0 = {3:1, 4:1}, $intern_1 = {3:1, 10:1, 9:1}, $intern_2 = {12:1, 7:1, 3:1, 6:1, 5:1}, $intern_3 = {11:1, 7:1, 3:1, 6:1, 5:1}, $intern_4 = {7:1, 38:1, 3:1, 6:1, 5:1}, $intern_5 = {7:1, 39:1, 3:1, 6:1, 5:1}, $intern_6 = {19:1, 3:1, 6:1, 5:1}, $intern_7 = {7:1, 59:1, 3:1, 6:1, 5:1}, $intern_8 = {58:1, 3:1, 10:1, 9:1}, $intern_9 = {3:1}, $intern_10 = 86400000, $intern_11 = 65535, $intern_12 = 4194303, $intern_13 = 1048575, $intern_14 = 524288, $intern_15 = 4194304, $intern_16 = 17592186044416, $intern_17 = -17592186044416, $intern_18 = 262144, $intern_19 = 65536, $intern_20 = 16777216, $intern_21 = 33554432, $intern_22 = 67108864, $intern_23 = {32:1, 25:1, 31:1, 30:1, 33:1, 22:1, 20:1}, $intern_24 = {32:1, 25:1, 31:1, 30:1, 33:1, 79:1, 22:1, 20:1}, $intern_25 = {17:1}, $intern_26 = {74:1}, $intern_27 = {16:1}, $intern_28 = {3:1, 29:1}, $intern_29 = 3600000, $intern_30 = {15:1, 306:1}, $intern_31 = {15:1, 307:1}, $intern_32 = {15:1, 308:1}, $intern_33 = {309:1, 15:1}, $intern_34 = {310:1, 15:1}, $intern_35 = {311:1, 15:1}, $intern_36 = {303:1, 15:1}, $intern_37 = {304:1, 15:1}, $intern_38 = {15:1, 305:1};
var _, prototypesByTypeId_0, initFnList_0, permutationId = -1;
function setGwtProperty(propertyName, propertyValue){
  typeof window === 'object' && typeof window['$gwt'] === 'object' && (window['$gwt'][propertyName] = propertyValue);
}

function gwtOnLoad_0(errFn, modName, modBase, softPermutationId){
  ensureModuleInit();
  var initFnList = initFnList_0;
  $moduleName = modName;
  $moduleBase = modBase;
  permutationId = softPermutationId;
  function initializeModules(){
    for (var i_0 = 0; i_0 < initFnList.length; i_0++) {
      initFnList[i_0]();
    }
  }

  if (errFn) {
    try {
      $entry(initializeModules)();
    }
     catch (e) {
      errFn(modName, e);
    }
  }
   else {
    $entry(initializeModules)();
  }
}

function ensureModuleInit(){
  initFnList_0 == null && (initFnList_0 = []);
}

function addInitFunctions(){
  ensureModuleInit();
  var initFnList = initFnList_0;
  for (var i_0 = 0; i_0 < arguments.length; i_0++) {
    initFnList.push(arguments[i_0]);
  }
}

function typeMarkerFn(){
}

function toString_13(object){
  var number;
  if (Array.isArray(object) && object.typeMarker === typeMarkerFn) {
    return $getName(getClass__Ljava_lang_Class___devirtual$(object)) + '@' + (number = hashCode__I__devirtual$(object) >>> 0 , number.toString(16));
  }
  return object.toString();
}

function portableObjCreate(obj){
  function F(){
  }

  ;
  F.prototype = obj || {};
  return new F;
}

function makeLambdaFunction(samMethod, ctor, ctorArguments){
  var lambda = function(){
    return samMethod.apply(lambda, arguments);
  }
  ;
  ctor.apply(lambda, ctorArguments);
  return lambda;
}

function emptyMethod(){
}

function defineClass(typeId, superTypeIdOrPrototype, castableTypeMap){
  var prototypesByTypeId = prototypesByTypeId_0, superPrototype;
  var prototype_0 = prototypesByTypeId[typeId];
  var clazz = prototype_0 instanceof Array?prototype_0[0]:null;
  if (prototype_0 && !clazz) {
    _ = prototype_0;
  }
   else {
    _ = (superPrototype = superTypeIdOrPrototype && superTypeIdOrPrototype.prototype , !superPrototype && (superPrototype = prototypesByTypeId_0[superTypeIdOrPrototype]) , portableObjCreate(superPrototype));
    _.castableTypeMap = castableTypeMap;
    !superTypeIdOrPrototype && (_.typeMarker = typeMarkerFn);
    prototypesByTypeId[typeId] = _;
  }
  for (var i_0 = 3; i_0 < arguments.length; ++i_0) {
    arguments[i_0].prototype = _;
  }
  clazz && (_.___clazz = clazz);
}

function bootstrap(){
  prototypesByTypeId_0 = {};
  !Array.isArray && (Array.isArray = function(vArg){
    return Object.prototype.toString.call(vArg) === '[object Array]';
  }
  );
  function now_0(){
    return (new Date).getTime();
  }

  !Date.now && (Date.now = now_0);
}

bootstrap();
function Object_0(){
}

function equals_Ljava_lang_Object__Z__devirtual$(this$static, other){
  return instanceOfString(this$static)?$equals_0(this$static, other):instanceOfDouble(this$static)?(checkCriticalNotNull(this$static) , this$static === other):instanceOfBoolean(this$static)?(checkCriticalNotNull(this$static) , this$static === other):hasJavaObjectVirtualDispatch(this$static)?this$static.equals_0(other):isJavaArray(this$static)?this$static === other:$equals(this$static, other);
}

function getClass__Ljava_lang_Class___devirtual$(this$static){
  return instanceOfString(this$static)?Ljava_lang_String_2_classLit:instanceOfDouble(this$static)?Ljava_lang_Double_2_classLit:instanceOfBoolean(this$static)?Ljava_lang_Boolean_2_classLit:hasJavaObjectVirtualDispatch(this$static)?this$static.___clazz:isJavaArray(this$static)?this$static.___clazz:this$static.___clazz || Array.isArray(this$static) && getClassLiteralForArray(Lcom_google_gwt_core_client_JavaScriptObject_2_classLit, 1) || Lcom_google_gwt_core_client_JavaScriptObject_2_classLit;
}

function hashCode__I__devirtual$(this$static){
  return instanceOfString(this$static)?getHashCode_1(this$static):instanceOfDouble(this$static)?$hashCode_0(this$static):instanceOfBoolean(this$static)?(checkCriticalNotNull(this$static) , this$static)?1231:1237:hasJavaObjectVirtualDispatch(this$static)?this$static.hashCode_0():isJavaArray(this$static)?getHashCode_0(this$static):$hashCode(this$static);
}

defineClass(1, null, {}, Object_0);
_.equals_0 = function equals(other){
  return this === other;
}
;
_.getClass_0 = function getClass_0(){
  return this.___clazz;
}
;
_.hashCode_0 = function hashCode_0(){
  return getHashCode_0(this);
}
;
_.toString_0 = function toString_0(){
  var number;
  return $getName(getClass__Ljava_lang_Class___devirtual$(this)) + '@' + (number = hashCode__I__devirtual$(this) >>> 0 , number.toString(16));
}
;
_.equals = function(other){
  return this.equals_0(other);
}
;
_.hashCode = function(){
  return this.hashCode_0();
}
;
_.toString = function(){
  return this.toString_0();
}
;
function canCast(src_0, dstId){
  if (instanceOfString(src_0)) {
    return !!stringCastMap[dstId];
  }
   else if (src_0.castableTypeMap) {
    return !!src_0.castableTypeMap[dstId];
  }
   else if (instanceOfDouble(src_0)) {
    return !!doubleCastMap[dstId];
  }
   else if (instanceOfBoolean(src_0)) {
    return !!booleanCastMap[dstId];
  }
  return false;
}

function castTo(src_0, dstId){
  checkCriticalType(src_0 == null || canCast(src_0, dstId));
  return src_0;
}

function castToArray(src_0){
  var elementTypeCategory;
  checkCriticalType(src_0 == null || Array.isArray(src_0) && (elementTypeCategory = getElementTypeCategory(src_0) , !(elementTypeCategory >= 14 && elementTypeCategory <= 16)));
  return src_0;
}

function castToFunction(src_0){
  checkCriticalType(src_0 == null || isFunction(src_0));
  return src_0;
}

function castToJso(src_0){
  checkCriticalType(src_0 == null || isJsObjectOrFunction(src_0) && !(src_0.typeMarker === typeMarkerFn));
  return src_0;
}

function castToString(src_0){
  checkCriticalType(src_0 == null || instanceOfString(src_0));
  return src_0;
}

function hasJavaObjectVirtualDispatch(src_0){
  return !Array.isArray(src_0) && src_0.typeMarker === typeMarkerFn;
}

function instanceOf(src_0, dstId){
  return src_0 != null && canCast(src_0, dstId);
}

function instanceOfBoolean(src_0){
  return typeof src_0 === 'boolean';
}

function instanceOfDouble(src_0){
  return typeof src_0 === 'number';
}

function instanceOfJso(src_0){
  return src_0 != null && isJsObjectOrFunction(src_0) && !(src_0.typeMarker === typeMarkerFn);
}

function instanceOfString(src_0){
  return typeof src_0 === 'string';
}

function isFunction(src_0){
  return typeof src_0 === 'function';
}

function isJsObjectOrFunction(src_0){
  return typeof src_0 === 'object' || typeof src_0 === 'function';
}

function maskUndefined(src_0){
  return src_0 == null?null:src_0;
}

function round_int(x_0){
  return Math.max(Math.min(x_0, 2147483647), -2147483648) | 0;
}

function throwClassCastExceptionUnlessNull(o){
  checkCriticalType(o == null);
  return o;
}

var booleanCastMap, doubleCastMap, stringCastMap;
function $ensureNamesAreInitialized(this$static){
  if (this$static.typeName != null) {
    return;
  }
  initializeNames(this$static);
}

function $getName(this$static){
  $ensureNamesAreInitialized(this$static);
  return this$static.typeName;
}

function Class(){
  ++nextSequentialId;
  this.typeName = null;
  this.simpleName = null;
  this.packageName = null;
  this.compoundName = null;
  this.canonicalName = null;
  this.typeId = null;
  this.arrayLiterals = null;
}

function createClassObject(packageName, compoundClassName){
  var clazz;
  clazz = new Class;
  clazz.packageName = packageName;
  clazz.compoundName = compoundClassName;
  return clazz;
}

function createForClass(packageName, compoundClassName, typeId, superclass){
  var clazz;
  clazz = createClassObject(packageName, compoundClassName);
  maybeSetClassLiteral(typeId, clazz);
  clazz.superclass = superclass;
  return clazz;
}

function createForEnum(packageName, compoundClassName, typeId, superclass, enumConstantsFunc){
  var clazz;
  clazz = createClassObject(packageName, compoundClassName);
  maybeSetClassLiteral(typeId, clazz);
  clazz.modifiers = enumConstantsFunc?8:0;
  clazz.superclass = superclass;
  return clazz;
}

function createForInterface(packageName, compoundClassName){
  var clazz;
  clazz = createClassObject(packageName, compoundClassName);
  clazz.modifiers = 2;
  return clazz;
}

function createForPrimitive(className, primitiveTypeId){
  var clazz;
  clazz = createClassObject('', className);
  clazz.typeId = primitiveTypeId;
  clazz.modifiers = 1;
  return clazz;
}

function getClassLiteralForArray_0(leafClass, dimensions){
  var arrayLiterals = leafClass.arrayLiterals = leafClass.arrayLiterals || [];
  return arrayLiterals[dimensions] || (arrayLiterals[dimensions] = leafClass.createClassLiteralForArray(dimensions));
}

function getPrototypeForClass(clazz){
  if (clazz.isPrimitive()) {
    return null;
  }
  var typeId = clazz.typeId;
  return prototypesByTypeId_0[typeId];
}

function initializeNames(clazz){
  if (clazz.isArray_1()) {
    var componentType = clazz.componentType;
    componentType.isPrimitive()?(clazz.typeName = '[' + componentType.typeId):!componentType.isArray_1()?(clazz.typeName = '[L' + componentType.getName() + ';'):(clazz.typeName = '[' + componentType.getName());
    clazz.canonicalName = componentType.getCanonicalName() + '[]';
    clazz.simpleName = componentType.getSimpleName() + '[]';
    return;
  }
  var packageName = clazz.packageName;
  var compoundName = clazz.compoundName;
  compoundName = compoundName.split('/');
  clazz.typeName = join_0('.', [packageName, join_0('$', compoundName)]);
  clazz.canonicalName = join_0('.', [packageName, join_0('.', compoundName)]);
  clazz.simpleName = compoundName[compoundName.length - 1];
}

function join_0(separator, strings){
  var i_0 = 0;
  while (!strings[i_0] || strings[i_0] == '') {
    i_0++;
  }
  var result = strings[i_0++];
  for (; i_0 < strings.length; i_0++) {
    if (!strings[i_0] || strings[i_0] == '') {
      continue;
    }
    result += separator + strings[i_0];
  }
  return result;
}

function maybeSetClassLiteral(typeId, clazz){
  var proto;
  if (!typeId) {
    return;
  }
  clazz.typeId = typeId;
  var prototype_0 = getPrototypeForClass(clazz);
  if (!prototype_0) {
    prototypesByTypeId_0[typeId] = [clazz];
    return;
  }
  prototype_0.___clazz = clazz;
}

defineClass(61, 1, {61:1}, Class);
_.createClassLiteralForArray = function createClassLiteralForArray(dimensions){
  var clazz;
  clazz = new Class;
  clazz.modifiers = 4;
  clazz.superclass = Ljava_lang_Object_2_classLit;
  dimensions > 1?(clazz.componentType = getClassLiteralForArray_0(this, dimensions - 1)):(clazz.componentType = this);
  return clazz;
}
;
_.getCanonicalName = function getCanonicalName(){
  $ensureNamesAreInitialized(this);
  return this.canonicalName;
}
;
_.getName = function getName(){
  return $getName(this);
}
;
_.getSimpleName = function getSimpleName(){
  return $ensureNamesAreInitialized(this) , this.simpleName;
}
;
_.isArray_1 = function isArray_1(){
  return (this.modifiers & 4) != 0;
}
;
_.isPrimitive = function isPrimitive_0(){
  return (this.modifiers & 1) != 0;
}
;
_.toString_0 = function toString_18(){
  return ((this.modifiers & 2) != 0?'interface ':(this.modifiers & 1) != 0?'':'class ') + ($ensureNamesAreInitialized(this) , this.typeName);
}
;
_.modifiers = 0;
var nextSequentialId = 1;
var Ljava_lang_Object_2_classLit = createForClass('java.lang', 'Object', 1, null);
var Ljava_lang_Class_2_classLit = createForClass('java.lang', 'Class', 61, Ljava_lang_Object_2_classLit);
defineClass(346, 1, {});
var instance_0;
var Lcom_google_gwt_animation_client_AnimationScheduler_2_classLit = createForClass('com.google.gwt.animation.client', 'AnimationScheduler', 346, Ljava_lang_Object_2_classLit);
defineClass(97, 1, {97:1});
var Lcom_google_gwt_animation_client_AnimationScheduler$AnimationHandle_2_classLit = createForClass('com.google.gwt.animation.client', 'AnimationScheduler/AnimationHandle', 97, Ljava_lang_Object_2_classLit);
function AnimationSchedulerImplStandard(){
}

function requestImpl(cb, element){
  var callback = $entry(function(){
    var time = now_1();
    cb.execute(time);
  }
  );
  var handle = $wnd.requestAnimationFrame(callback, element);
  return {id:handle};
}

defineClass(292, 346, {}, AnimationSchedulerImplStandard);
_.requestAnimationFrame_0 = function requestAnimationFrame_0(callback, element){
  requestImpl(callback, element);
  return new AnimationSchedulerImplStandard$1;
}
;
var Lcom_google_gwt_animation_client_AnimationSchedulerImplStandard_2_classLit = createForClass('com.google.gwt.animation.client', 'AnimationSchedulerImplStandard', 292, Lcom_google_gwt_animation_client_AnimationScheduler_2_classLit);
function AnimationSchedulerImplStandard$1(){
}

defineClass(293, 97, {97:1}, AnimationSchedulerImplStandard$1);
var Lcom_google_gwt_animation_client_AnimationSchedulerImplStandard$1_2_classLit = createForClass('com.google.gwt.animation.client', 'AnimationSchedulerImplStandard/1', 293, Lcom_google_gwt_animation_client_AnimationScheduler$AnimationHandle_2_classLit);
function $updateAnimations(this$static){
  var curAnimations, duration, requestId, requestId$index, requestId$max;
  curAnimations = initUnidimensionalArray(Lcom_google_gwt_animation_client_AnimationSchedulerImplTimer$AnimationHandleImpl_2_classLit, {356:1, 3:1, 4:1}, 98, this$static.animationRequests.array.length, 0, 1);
  curAnimations = castTo($toArray(this$static.animationRequests, curAnimations), 356);
  duration = new Duration;
  for (requestId$index = 0 , requestId$max = curAnimations.length; requestId$index < requestId$max; ++requestId$index) {
    requestId = curAnimations[requestId$index];
    $remove_5(this$static.animationRequests, requestId);
    $execute(requestId.callback);
  }
  this$static.animationRequests.array.length > 0 && $schedule(this$static.timer, $wnd.Math.max(5, 16 - (now_1() - duration.start_0)));
}

function AnimationSchedulerImplTimer(){
  this.animationRequests = new ArrayList;
  this.timer = new AnimationSchedulerImplTimer$1(this);
}

defineClass(294, 346, {}, AnimationSchedulerImplTimer);
_.requestAnimationFrame_0 = function requestAnimationFrame_1(callback, element){
  var requestId;
  requestId = new AnimationSchedulerImplTimer$AnimationHandleImpl(callback);
  $add_2(this.animationRequests, requestId);
  this.animationRequests.array.length == 1 && $schedule(this.timer, 16);
  return requestId;
}
;
var Lcom_google_gwt_animation_client_AnimationSchedulerImplTimer_2_classLit = createForClass('com.google.gwt.animation.client', 'AnimationSchedulerImplTimer', 294, Lcom_google_gwt_animation_client_AnimationScheduler_2_classLit);
function $cancel(this$static){
  if (!this$static.timerId) {
    return;
  }
  ++this$static.cancelCounter;
  this$static.isRepeating?clearInterval_0(this$static.timerId.value_0):clearTimeout_0(this$static.timerId.value_0);
  this$static.timerId = null;
}

function $schedule(this$static, delayMillis){
  if (delayMillis < 0) {
    throw toJs(new IllegalArgumentException('must be non-negative'));
  }
  !!this$static.timerId && $cancel(this$static);
  this$static.isRepeating = false;
  this$static.timerId = valueOf_0(setTimeout_0(createCallback(this$static, this$static.cancelCounter), delayMillis));
}

function Timer(){
}

function clearInterval_0(timerId){
  $wnd.clearInterval(timerId);
}

function clearTimeout_0(timerId){
  $wnd.clearTimeout(timerId);
}

function createCallback(timer, cancelCounter){
  return $entry(function(){
    timer.fire_0(cancelCounter);
  }
  );
}

function setTimeout_0(func, time){
  return $wnd.setTimeout(func, time);
}

defineClass(48, 1, {});
_.fire_0 = function fire(scheduleCancelCounter){
  if (scheduleCancelCounter != this.cancelCounter) {
    return;
  }
  this.isRepeating || (this.timerId = null);
  this.run();
}
;
_.cancelCounter = 0;
_.isRepeating = false;
_.timerId = null;
var Lcom_google_gwt_user_client_Timer_2_classLit = createForClass('com.google.gwt.user.client', 'Timer', 48, Ljava_lang_Object_2_classLit);
function AnimationSchedulerImplTimer$1(this$0){
  this.this$01 = this$0;
  Timer.call(this);
}

defineClass(295, 48, {}, AnimationSchedulerImplTimer$1);
_.run = function run(){
  $updateAnimations(this.this$01);
}
;
var Lcom_google_gwt_animation_client_AnimationSchedulerImplTimer$1_2_classLit = createForClass('com.google.gwt.animation.client', 'AnimationSchedulerImplTimer/1', 295, Lcom_google_gwt_user_client_Timer_2_classLit);
function AnimationSchedulerImplTimer$AnimationHandleImpl(callback){
  this.callback = callback;
}

defineClass(98, 97, {97:1, 98:1}, AnimationSchedulerImplTimer$AnimationHandleImpl);
var Lcom_google_gwt_animation_client_AnimationSchedulerImplTimer$AnimationHandleImpl_2_classLit = createForClass('com.google.gwt.animation.client', 'AnimationSchedulerImplTimer/AnimationHandleImpl', 98, Lcom_google_gwt_animation_client_AnimationScheduler$AnimationHandle_2_classLit);
function Duration(){
  this.start_0 = now_1();
}

defineClass(102, 1, {}, Duration);
_.start_0 = 0;
var Lcom_google_gwt_core_client_Duration_2_classLit = createForClass('com.google.gwt.core.client', 'Duration', 102, Ljava_lang_Object_2_classLit);
function $addSuppressed(this$static, exception){
  checkCriticalNotNull_0(exception);
  checkCriticalArgument(exception != this$static);
  if (this$static.disableSuppression) {
    return;
  }
  this$static.suppressedExceptions == null?(this$static.suppressedExceptions = stampJavaTypeInfo(getClassLiteralForArray(Ljava_lang_Throwable_2_classLit, 1), $intern_0, 9, 0, [exception])):(this$static.suppressedExceptions[this$static.suppressedExceptions.length] = exception);
}

function $fillInStackTrace(this$static){
  this$static.writetableStackTrace && this$static.backingJsObject !== '__noinit__' && this$static.initializeBackingError();
  return this$static;
}

function $setBackingJsObject(this$static, backingJsObject){
  this$static.backingJsObject = backingJsObject;
  backingJsObject != null && setPropertySafe(backingJsObject, '__java$exception', this$static);
}

function $toString(this$static, message){
  var className;
  className = $getName(this$static.___clazz);
  return message == null?className:className + ': ' + message;
}

function fixIE(e){
  if (!('stack' in e)) {
    try {
      throw e;
    }
     catch (ignored) {
    }
  }
  return e;
}

defineClass(9, 1, {3:1, 9:1});
_.createError = function createError(msg){
  return new Error(msg);
}
;
_.getMessage = function getMessage(){
  return this.detailMessage;
}
;
_.initializeBackingError = function initializeBackingError(){
  var className, errorMessage, message;
  message = this.detailMessage == null?null:this.detailMessage.replace(new RegExp('\n', 'g'), ' ');
  errorMessage = (className = $getName(this.___clazz) , message == null?className:className + ': ' + message);
  $setBackingJsObject(this, fixIE(this.createError(errorMessage)));
  captureStackTrace(this);
}
;
_.toString_0 = function toString_1(){
  return $toString(this, this.getMessage());
}
;
_.backingJsObject = '__noinit__';
_.disableSuppression = false;
_.writetableStackTrace = true;
var Ljava_lang_Throwable_2_classLit = createForClass('java.lang', 'Throwable', 9, Ljava_lang_Object_2_classLit);
defineClass(10, 9, $intern_1);
var Ljava_lang_Exception_2_classLit = createForClass('java.lang', 'Exception', 10, Ljava_lang_Throwable_2_classLit);
function RuntimeException(){
  $fillInStackTrace(this);
  this.initializeBackingError();
}

function RuntimeException_0(message){
  this.detailMessage = message;
  $fillInStackTrace(this);
  this.initializeBackingError();
}

function RuntimeException_1(message, cause){
  this.detailMessage = message;
  $fillInStackTrace(this);
  this.initializeBackingError();
}

defineClass(18, 10, $intern_1, RuntimeException_0);
var Ljava_lang_RuntimeException_2_classLit = createForClass('java.lang', 'RuntimeException', 18, Ljava_lang_Exception_2_classLit);
defineClass(82, 18, $intern_1);
var Ljava_lang_JsException_2_classLit = createForClass('java.lang', 'JsException', 82, Ljava_lang_RuntimeException_2_classLit);
defineClass(127, 82, $intern_1);
var Lcom_google_gwt_core_client_impl_JavaScriptExceptionBase_2_classLit = createForClass('com.google.gwt.core.client.impl', 'JavaScriptExceptionBase', 127, Ljava_lang_JsException_2_classLit);
function $clinit_JavaScriptException(){
  $clinit_JavaScriptException = emptyMethod;
  NOT_SET = new Object_0;
}

function $ensureInit(this$static){
  var exception;
  if (this$static.message_0 == null) {
    exception = maskUndefined(this$static.e) === maskUndefined(NOT_SET)?null:this$static.e;
    this$static.name_0 = exception == null?'null':instanceOfJso(exception)?getExceptionName0(castToJso(exception)):instanceOfString(exception)?'String':$getName(getClass__Ljava_lang_Class___devirtual$(exception));
    this$static.description = this$static.description + ': ' + (instanceOfJso(exception)?getExceptionDescription0(castToJso(exception)):exception + '');
    this$static.message_0 = '(' + this$static.name_0 + ') ' + this$static.description;
  }
}

function JavaScriptException(e){
  $clinit_JavaScriptException();
  $fillInStackTrace(this);
  this.backingJsObject = e;
  e != null && setPropertySafe(e, '__java$exception', this);
  this.detailMessage = e == null?'null':toString_13(e);
  this.description = '';
  this.e = e;
  this.description = '';
}

function getExceptionDescription0(e){
  return e == null?null:e.message;
}

function getExceptionName0(e){
  return e == null?null:e.name;
}

defineClass(62, 127, {62:1, 3:1, 10:1, 9:1}, JavaScriptException);
_.getMessage = function getMessage_0(){
  $ensureInit(this);
  return this.message_0;
}
;
_.getThrown = function getThrown(){
  return maskUndefined(this.e) === maskUndefined(NOT_SET)?null:this.e;
}
;
var NOT_SET;
var Lcom_google_gwt_core_client_JavaScriptException_2_classLit = createForClass('com.google.gwt.core.client', 'JavaScriptException', 62, Lcom_google_gwt_core_client_impl_JavaScriptExceptionBase_2_classLit);
function $equals(this$static, other){
  return !!this$static && !!this$static.equals?this$static.equals(other):maskUndefined(this$static) === maskUndefined(other);
}

function $hashCode(this$static){
  return !!this$static && !!this$static.hashCode?this$static.hashCode():getHashCode_0(this$static);
}

var Lcom_google_gwt_core_client_JavaScriptObject_2_classLit = createForClass('com.google.gwt.core.client', 'JavaScriptObject$', 0, Ljava_lang_Object_2_classLit);
function $push(this$static, value_0){
  this$static[this$static.length] = value_0;
}

function now_1(){
  if (Date.now) {
    return Date.now();
  }
  return (new Date).getTime();
}

function escapeChar(c, escapeTable){
  var lookedUp = escapeTable_0[c.charCodeAt(0)];
  return lookedUp == null?c:lookedUp;
}

function escapeValue(toEscape){
  var escapeTable = (!escapeTable_0 && (escapeTable_0 = initEscapeTable()) , escapeTable_0);
  var s = toEscape.replace(/[\x00-\x1f\xad\u0600-\u0603\u06dd\u070f\u17b4\u17b5\u200b-\u200f\u2028-\u202e\u2060-\u2064\u206a-\u206f\ufeff\ufff9-\ufffb"\\]/g, function(x_0){
    return escapeChar(x_0, escapeTable);
  }
  );
  return '"' + s + '"';
}

function initEscapeTable(){
  var out = ['\\u0000', '\\u0001', '\\u0002', '\\u0003', '\\u0004', '\\u0005', '\\u0006', '\\u0007', '\\b', '\\t', '\\n', '\\u000B', '\\f', '\\r', '\\u000E', '\\u000F', '\\u0010', '\\u0011', '\\u0012', '\\u0013', '\\u0014', '\\u0015', '\\u0016', '\\u0017', '\\u0018', '\\u0019', '\\u001A', '\\u001B', '\\u001C', '\\u001D', '\\u001E', '\\u001F'];
  out[34] = '\\"';
  out[92] = '\\\\';
  out[173] = '\\u00ad';
  out[1536] = '\\u0600';
  out[1537] = '\\u0601';
  out[1538] = '\\u0602';
  out[1539] = '\\u0603';
  out[1757] = '\\u06dd';
  out[1807] = '\\u070f';
  out[6068] = '\\u17b4';
  out[6069] = '\\u17b5';
  out[8203] = '\\u200b';
  out[8204] = '\\u200c';
  out[8205] = '\\u200d';
  out[8206] = '\\u200e';
  out[8207] = '\\u200f';
  out[8232] = '\\u2028';
  out[8233] = '\\u2029';
  out[8234] = '\\u202a';
  out[8235] = '\\u202b';
  out[8236] = '\\u202c';
  out[8237] = '\\u202d';
  out[8238] = '\\u202e';
  out[8288] = '\\u2060';
  out[8289] = '\\u2061';
  out[8290] = '\\u2062';
  out[8291] = '\\u2063';
  out[8292] = '\\u2064';
  out[8298] = '\\u206a';
  out[8299] = '\\u206b';
  out[8300] = '\\u206c';
  out[8301] = '\\u206d';
  out[8302] = '\\u206e';
  out[8303] = '\\u206f';
  out[65279] = '\\ufeff';
  out[65529] = '\\ufff9';
  out[65530] = '\\ufffa';
  out[65531] = '\\ufffb';
  return out;
}

function safeEval(json){
  try {
    return JSON.parse(json);
  }
   catch (e) {
    return throwIllegalArgumentException('Error parsing JSON: ' + e, json);
  }
}

function throwIllegalArgumentException(message, data_0){
  throw toJs(new IllegalArgumentException(message + '\n' + data_0));
}

var escapeTable_0;
defineClass(314, 1, {});
var Lcom_google_gwt_core_client_Scheduler_2_classLit = createForClass('com.google.gwt.core.client', 'Scheduler', 314, Ljava_lang_Object_2_classLit);
function $clinit_Impl(){
  $clinit_Impl = emptyMethod;
  !!($clinit_StackTraceCreator() , collector);
}

function apply_0(jsFunction, thisObj, args){
  return jsFunction.apply(thisObj, args);
  var __0;
}

function enter(){
  var now_0;
  if (entryDepth != 0) {
    now_0 = now_1();
    if (now_0 - watchdogEntryDepthLastScheduled > 2000) {
      watchdogEntryDepthLastScheduled = now_0;
      watchdogEntryDepthTimerId = $wnd.setTimeout(watchdogEntryDepthRun, 10);
    }
  }
  if (entryDepth++ == 0) {
    $flushEntryCommands(($clinit_SchedulerImpl() , INSTANCE));
    return true;
  }
  return false;
}

function entry_0(jsFunction){
  $clinit_Impl();
  return function(){
    return entry0(jsFunction, this, arguments);
    var __0;
  }
  ;
}

function entry0(jsFunction, thisObj, args){
  var initialEntry;
  initialEntry = enter();
  try {
    return apply_0(jsFunction, thisObj, args);
  }
   finally {
    exit(initialEntry);
  }
}

function exit(initialEntry){
  initialEntry && $flushFinallyCommands(($clinit_SchedulerImpl() , INSTANCE));
  --entryDepth;
  if (initialEntry) {
    if (watchdogEntryDepthTimerId != -1) {
      watchdogEntryDepthCancel(watchdogEntryDepthTimerId);
      watchdogEntryDepthTimerId = -1;
    }
  }
}

function reportToBrowser(e){
  $clinit_Impl();
  $wnd.setTimeout(function(){
    throw e;
  }
  , 0);
}

function watchdogEntryDepthCancel(timerId){
  $wnd.clearTimeout(timerId);
}

function watchdogEntryDepthRun(){
  entryDepth != 0 && (entryDepth = 0);
  watchdogEntryDepthTimerId = -1;
}

var entryDepth = 0, watchdogEntryDepthLastScheduled = 0, watchdogEntryDepthTimerId = -1;
function $clinit_SchedulerImpl(){
  $clinit_SchedulerImpl = emptyMethod;
  INSTANCE = new SchedulerImpl;
}

function $flushEntryCommands(this$static){
  var oldQueue, rescheduled;
  if (this$static.entryCommands) {
    rescheduled = null;
    do {
      oldQueue = this$static.entryCommands;
      this$static.entryCommands = null;
      rescheduled = runScheduledTasks(oldQueue, rescheduled);
    }
     while (this$static.entryCommands);
    this$static.entryCommands = rescheduled;
  }
}

function $flushFinallyCommands(this$static){
  var oldQueue, rescheduled;
  if (this$static.finallyCommands) {
    rescheduled = null;
    do {
      oldQueue = this$static.finallyCommands;
      this$static.finallyCommands = null;
      rescheduled = runScheduledTasks(oldQueue, rescheduled);
    }
     while (this$static.finallyCommands);
    this$static.finallyCommands = rescheduled;
  }
}

function $flushPostEventPumpCommands(this$static){
  var oldDeferred;
  if (this$static.deferredCommands) {
    oldDeferred = this$static.deferredCommands;
    this$static.deferredCommands = null;
    !this$static.incrementalCommands && (this$static.incrementalCommands = []);
    runScheduledTasks(oldDeferred, this$static.incrementalCommands);
  }
  !!this$static.incrementalCommands && (this$static.incrementalCommands = $runRepeatingTasks(this$static.incrementalCommands));
}

function $isWorkQueued(this$static){
  return !!this$static.deferredCommands || !!this$static.incrementalCommands;
}

function $maybeSchedulePostEventPumpCommands(this$static){
  if (!this$static.shouldBeRunning) {
    this$static.shouldBeRunning = true;
    !this$static.flusher && (this$static.flusher = new SchedulerImpl$Flusher(this$static));
    scheduleFixedDelayImpl(this$static.flusher, 1);
    !this$static.rescue && (this$static.rescue = new SchedulerImpl$Rescuer(this$static));
    scheduleFixedDelayImpl(this$static.rescue, 50);
  }
}

function $runRepeatingTasks(tasks){
  var canceledSomeTasks, duration, executedSomeTask, i_0, length_0, newTasks, t;
  length_0 = tasks.length;
  if (length_0 == 0) {
    return null;
  }
  canceledSomeTasks = false;
  duration = new Duration;
  while (now_1() - duration.start_0 < 16) {
    executedSomeTask = false;
    for (i_0 = 0; i_0 < length_0; i_0++) {
      t = tasks[i_0];
      if (!t) {
        continue;
      }
      executedSomeTask = true;
      if (!t[0].execute_0()) {
        tasks[i_0] = null;
        canceledSomeTasks = true;
      }
    }
    if (!executedSomeTask) {
      break;
    }
  }
  if (canceledSomeTasks) {
    newTasks = [];
    for (i_0 = 0; i_0 < length_0; i_0++) {
      !!tasks[i_0] && (newTasks[newTasks.length] = tasks[i_0] , undefined);
    }
    return newTasks.length == 0?null:newTasks;
  }
   else {
    return tasks;
  }
}

function $scheduleDeferred(this$static, cmd){
  this$static.deferredCommands = push_0(this$static.deferredCommands, [cmd, false]);
  $maybeSchedulePostEventPumpCommands(this$static);
}

function SchedulerImpl(){
}

function execute(cmd){
  return cmd.execute_0();
}

function push_0(queue, task){
  !queue && (queue = []);
  queue[queue.length] = task;
  return queue;
}

function runScheduledTasks(tasks, rescheduled){
  var e, i_0, j, t;
  for (i_0 = 0 , j = tasks.length; i_0 < j; i_0++) {
    t = tasks[i_0];
    try {
      t[1]?t[0].execute_0() && (rescheduled = push_0(rescheduled, t)):t[0].execute_1();
    }
     catch ($e0) {
      $e0 = toJava($e0);
      if (instanceOf($e0, 9)) {
        e = $e0;
        $clinit_Impl();
        reportToBrowser(instanceOf(e, 62)?castTo(e, 62).getThrown():e);
      }
       else 
        throw toJs($e0);
    }
  }
  return rescheduled;
}

function scheduleFixedDelayImpl(cmd, delayMs){
  $clinit_SchedulerImpl();
  function callback(){
    var ret = $entry(execute)(cmd);
    ret && $wnd.setTimeout(callback, delayMs);
  }

  $wnd.setTimeout(callback, delayMs);
}

defineClass(133, 314, {}, SchedulerImpl);
_.flushRunning = false;
_.shouldBeRunning = false;
var INSTANCE;
var Lcom_google_gwt_core_client_impl_SchedulerImpl_2_classLit = createForClass('com.google.gwt.core.client.impl', 'SchedulerImpl', 133, Lcom_google_gwt_core_client_Scheduler_2_classLit);
function SchedulerImpl$Flusher(this$0){
  this.this$01 = this$0;
}

defineClass(134, 1, {}, SchedulerImpl$Flusher);
_.execute_0 = function execute_0(){
  this.this$01.flushRunning = true;
  $flushPostEventPumpCommands(this.this$01);
  this.this$01.flushRunning = false;
  return this.this$01.shouldBeRunning = $isWorkQueued(this.this$01);
}
;
var Lcom_google_gwt_core_client_impl_SchedulerImpl$Flusher_2_classLit = createForClass('com.google.gwt.core.client.impl', 'SchedulerImpl/Flusher', 134, Ljava_lang_Object_2_classLit);
function SchedulerImpl$Rescuer(this$0){
  this.this$01 = this$0;
}

defineClass(135, 1, {}, SchedulerImpl$Rescuer);
_.execute_0 = function execute_1(){
  this.this$01.flushRunning && scheduleFixedDelayImpl(this.this$01.flusher, 1);
  return this.this$01.shouldBeRunning;
}
;
var Lcom_google_gwt_core_client_impl_SchedulerImpl$Rescuer_2_classLit = createForClass('com.google.gwt.core.client.impl', 'SchedulerImpl/Rescuer', 135, Ljava_lang_Object_2_classLit);
function $clinit_StackTraceCreator(){
  $clinit_StackTraceCreator = emptyMethod;
  var c, enforceLegacy;
  enforceLegacy = !supportsErrorStack();
  c = new StackTraceCreator$CollectorModernNoSourceMap;
  collector = enforceLegacy?new StackTraceCreator$CollectorLegacy:c;
}

function captureStackTrace(error){
  $clinit_StackTraceCreator();
  collector.collect(error);
}

function extractFunctionName(fnName){
  var fnRE = /function(?:\s+([\w$]+))?\s*\(/;
  var match_0 = fnRE.exec(fnName);
  return match_0 && match_0[1] || 'anonymous';
}

function supportsErrorStack(){
  if (Error.stackTraceLimit > 0) {
    $wnd.Error.stackTraceLimit = Error.stackTraceLimit = 64;
    return true;
  }
  return 'stack' in new Error;
}

var collector;
defineClass(326, 1, {});
var Lcom_google_gwt_core_client_impl_StackTraceCreator$Collector_2_classLit = createForClass('com.google.gwt.core.client.impl', 'StackTraceCreator/Collector', 326, Ljava_lang_Object_2_classLit);
function StackTraceCreator$CollectorLegacy(){
}

defineClass(128, 326, {}, StackTraceCreator$CollectorLegacy);
_.collect = function collect(error){
  var seen = {}, name_1;
  var fnStack = [];
  error['fnStack'] = fnStack;
  var callee = arguments.callee.caller;
  while (callee) {
    var name_0 = ($clinit_StackTraceCreator() , callee.name || (callee.name = extractFunctionName(callee.toString())));
    fnStack.push(name_0);
    var keyName = ':' + name_0;
    var withThisName = seen[keyName];
    if (withThisName) {
      var i_0, j;
      for (i_0 = 0 , j = withThisName.length; i_0 < j; i_0++) {
        if (withThisName[i_0] === callee) {
          return;
        }
      }
    }
    (withThisName || (seen[keyName] = [])).push(callee);
    callee = callee.caller;
  }
}
;
var Lcom_google_gwt_core_client_impl_StackTraceCreator$CollectorLegacy_2_classLit = createForClass('com.google.gwt.core.client.impl', 'StackTraceCreator/CollectorLegacy', 128, Lcom_google_gwt_core_client_impl_StackTraceCreator$Collector_2_classLit);
defineClass(327, 326, {});
_.collect = function collect_0(error){
}
;
var Lcom_google_gwt_core_client_impl_StackTraceCreator$CollectorModern_2_classLit = createForClass('com.google.gwt.core.client.impl', 'StackTraceCreator/CollectorModern', 327, Lcom_google_gwt_core_client_impl_StackTraceCreator$Collector_2_classLit);
function StackTraceCreator$CollectorModernNoSourceMap(){
}

defineClass(129, 327, {}, StackTraceCreator$CollectorModernNoSourceMap);
var Lcom_google_gwt_core_client_impl_StackTraceCreator$CollectorModernNoSourceMap_2_classLit = createForClass('com.google.gwt.core.client.impl', 'StackTraceCreator/CollectorModernNoSourceMap', 129, Lcom_google_gwt_core_client_impl_StackTraceCreator$CollectorModern_2_classLit);
function $appendChild(this$static, newChild){
  return this$static.appendChild(newChild);
}

function $getChild(this$static, index_0){
  return this$static.childNodes[index_0];
}

function $insertAfter(this$static, newChild, refChild){
  var next;
  next = !refChild?null:refChild.nextSibling;
  return !next?this$static.appendChild(newChild):this$static.insertBefore(newChild, next);
}

function $insertBefore(this$static, newChild, refChild){
  return this$static.insertBefore(newChild, refChild);
}

function $insertFirst(this$static, child){
  return $insertBefore(this$static, child, this$static.firstChild);
}

function $removeAllChildren(this$static){
  while (this$static.lastChild) {
    this$static.removeChild(this$static.lastChild);
  }
}

function $removeChild(this$static, oldChild){
  return this$static.removeChild(oldChild);
}

function $removeFromParent(this$static){
  var parent_0;
  parent_0 = $getParentElement(this$static);
  !!parent_0 && parent_0.removeChild(this$static);
}

function $addClassName(this$static, className){
  var idx, oldClassName;
  className = trimClassName(className);
  oldClassName = this$static.className || '';
  idx = indexOfName(oldClassName, className);
  if (idx == -1) {
    oldClassName.length > 0?(this$static.className = oldClassName + ' ' + className || '' , undefined):(this$static.className = className || '' , undefined);
    return true;
  }
  return false;
}

function $getPropertyInt(this$static, name_0){
  return parseInt(this$static[name_0]) | 0;
}

function $hasClassName(this$static, className){
  var idx;
  className = trimClassName(className);
  idx = indexOfName(this$static.className || '', className);
  return idx != -1;
}

function $removeClassName(this$static, className){
  var begin, end, idx, newClassName, oldStyle;
  className = trimClassName(className);
  oldStyle = this$static.className || '';
  idx = indexOfName(oldStyle, className);
  if (idx != -1) {
    begin = $trim(oldStyle.substr(0, idx));
    end = $trim($substring(oldStyle, idx + className.length));
    begin.length == 0?(newClassName = end):end.length == 0?(newClassName = begin):(newClassName = begin + ' ' + end);
    this$static.className = newClassName || '';
    return true;
  }
  return false;
}

function $setAttribute(this$static, name_0, value_0){
  this$static.setAttribute(name_0, value_0);
}

function $setClassName(this$static, className){
  this$static.className = className || '';
}

function $setScrollTop(this$static, scrollTop){
  this$static.scrollTop = scrollTop;
}

function indexOfName(nameList, name_0){
  var idx, last, lastPos;
  idx = nameList.indexOf(name_0);
  while (idx != -1) {
    if (idx == 0 || (checkCriticalStringElementIndex(idx - 1, nameList.length) , nameList.charCodeAt(idx - 1) == 32)) {
      last = idx + name_0.length;
      lastPos = nameList.length;
      if (last == lastPos || last < lastPos && (checkCriticalStringElementIndex(last, nameList.length) , nameList.charCodeAt(last) == 32)) {
        break;
      }
    }
    idx = nameList.indexOf(name_0, idx + 1);
  }
  return idx;
}

function trimClassName(className){
  className = $trim(className);
  return className;
}

function $createElement(doc, tag){
  return doc.createElement(tag);
}

function $getFirstChildElement(elem){
  var child = elem.firstChild;
  while (child && child.nodeType != 1)
    child = child.nextSibling;
  return child;
}

function $getNextSiblingElement(elem){
  var sib = elem.nextSibling;
  while (sib && sib.nodeType != 1)
    sib = sib.nextSibling;
  return sib;
}

function $getParentElement(node){
  var parent_0 = node.parentNode;
  (!parent_0 || parent_0.nodeType != 1) && (parent_0 = null);
  return parent_0;
}

function $getScrollLeft(doc){
  var scrollingElement;
  return $getScrollLeft_0((scrollingElement = $getDocumentScrollingElement(doc) , scrollingElement?scrollingElement:doc.documentElement));
}

function $getScrollTop(doc){
  var scrollingElement;
  return ((scrollingElement = $getDocumentScrollingElement(doc) , scrollingElement?scrollingElement:doc.documentElement).scrollTop || 0) | 0;
}

function $eventGetButton(evt){
  var button = evt.button;
  if (button == 1) {
    return 4;
  }
   else if (button == 2) {
    return 2;
  }
  return 1;
}

function $getDocumentScrollingElement(doc){
  if (doc.scrollingElement) {
    return doc.scrollingElement;
  }
  return doc.body;
}

function $isOrHasChild(parent_0, child){
  return parent_0.contains(child);
}

function $setInnerText(elem, text_0){
  elem.textContent = text_0 || '';
}

function $getAbsoluteLeft(elem){
  var left, rect;
  rect = elem.getBoundingClientRect && elem.getBoundingClientRect();
  left = rect?rect.left + $getScrollLeft(elem.ownerDocument):getAbsoluteLeftUsingOffsets(elem);
  return left | 0;
}

function $getAbsoluteTop(elem){
  var rect, top_0, top_1;
  rect = elem.getBoundingClientRect && elem.getBoundingClientRect();
  top_0 = rect?rect.top + $getScrollTop(elem.ownerDocument):getAbsoluteTopUsingOffsets(elem);
  return top_0 | 0;
}

function $getScrollLeft_0(elem){
  if (!$equalsIgnoreCase('body', elem.tagName) && $isRTL(elem)) {
    return ((elem.scrollLeft || 0) | 0) - (((elem.scrollWidth || 0) | 0) - (elem.clientWidth | 0));
  }
  return (elem.scrollLeft || 0) | 0;
}

function $isRTL(elem){
  return elem.ownerDocument.defaultView.getComputedStyle(elem, '').direction == 'rtl';
}

function $setScrollLeft(elem, left){
  !$equalsIgnoreCase('body', elem.tagName) && $isRTL(elem) && (left += ((elem.scrollWidth || 0) | 0) - (elem.clientWidth | 0));
  elem.scrollLeft = left;
}

function getAbsoluteLeftUsingOffsets(elem){
  if (elem.offsetLeft == null) {
    return 0;
  }
  var left = 0;
  var doc = elem.ownerDocument;
  var curr = elem.parentNode;
  if (curr) {
    while (curr.offsetParent) {
      left -= curr.scrollLeft;
      doc.defaultView.getComputedStyle(curr, '').getPropertyValue('direction') == 'rtl' && (left += curr.scrollWidth - curr.clientWidth);
      curr = curr.parentNode;
    }
  }
  while (elem) {
    left += elem.offsetLeft;
    if (doc.defaultView.getComputedStyle(elem, '')['position'] == 'fixed') {
      left += doc.body.scrollLeft;
      return left;
    }
    var parent_0 = elem.offsetParent;
    parent_0 && $wnd.devicePixelRatio && (left += parseInt(doc.defaultView.getComputedStyle(parent_0, '').getPropertyValue('border-left-width')));
    if (parent_0 && parent_0.tagName == 'BODY' && elem.style.position == 'absolute') {
      break;
    }
    elem = parent_0;
  }
  return left;
}

function getAbsoluteTopUsingOffsets(elem){
  if (elem.offsetTop == null) {
    return 0;
  }
  var top_0 = 0;
  var doc = elem.ownerDocument;
  var curr = elem.parentNode;
  if (curr) {
    while (curr.offsetParent) {
      top_0 -= curr.scrollTop;
      curr = curr.parentNode;
    }
  }
  while (elem) {
    top_0 += elem.offsetTop;
    if (doc.defaultView.getComputedStyle(elem, '')['position'] == 'fixed') {
      top_0 += doc.body.scrollTop;
      return top_0;
    }
    var parent_0 = elem.offsetParent;
    parent_0 && $wnd.devicePixelRatio && (top_0 += parseInt(doc.defaultView.getComputedStyle(parent_0, '').getPropertyValue('border-top-width')));
    if (parent_0 && parent_0.tagName == 'BODY' && elem.style.position == 'absolute') {
      break;
    }
    elem = parent_0;
  }
  return top_0;
}

function $getScrollLeft_1(this$static){
  var scrollingElement;
  return $getScrollLeft_0((scrollingElement = $getDocumentScrollingElement(this$static) , scrollingElement?scrollingElement:this$static.documentElement));
}

function $getScrollTop_0(this$static){
  var scrollingElement;
  return ((scrollingElement = $getDocumentScrollingElement(this$static) , scrollingElement?scrollingElement:this$static.documentElement).scrollTop || 0) | 0;
}

function $name(this$static){
  return this$static.name_0 != null?this$static.name_0:'' + this$static.ordinal;
}

function $toString_0(this$static){
  return this$static.name_0 != null?this$static.name_0:'' + this$static.ordinal;
}

function Enum(name_0, ordinal){
  this.name_0 = name_0;
  this.ordinal = ordinal;
}

function createValueOfMap(enumConstants){
  var result, value_0, value$index, value$max;
  result = {};
  for (value$index = 0 , value$max = enumConstants.length; value$index < value$max; ++value$index) {
    value_0 = enumConstants[value$index];
    result[':' + (value_0.name_0 != null?value_0.name_0:'' + value_0.ordinal)] = value_0;
  }
  return result;
}

function valueOf(map_0, name_0){
  var result;
  checkCriticalNotNull(name_0);
  result = map_0[':' + name_0];
  checkCriticalArgument_0(!!result, stampJavaTypeInfo(getClassLiteralForArray(Ljava_lang_Object_2_classLit, 1), $intern_0, 1, 5, [name_0]));
  return result;
}

defineClass(5, 1, {3:1, 6:1, 5:1});
_.equals_0 = function equals_0(other){
  return this === other;
}
;
_.hashCode_0 = function hashCode_1(){
  return getHashCode_0(this);
}
;
_.toString_0 = function toString_2(){
  return $toString_0(this);
}
;
_.ordinal = 0;
var Ljava_lang_Enum_2_classLit = createForClass('java.lang', 'Enum', 5, Ljava_lang_Object_2_classLit);
function $clinit_Style$Cursor(){
  $clinit_Style$Cursor = emptyMethod;
  DEFAULT = new Style$Cursor$1;
  AUTO = new Style$Cursor$2;
  CROSSHAIR = new Style$Cursor$3;
  POINTER = new Style$Cursor$4;
  MOVE = new Style$Cursor$5;
  E_RESIZE = new Style$Cursor$6;
  NE_RESIZE = new Style$Cursor$7;
  NW_RESIZE = new Style$Cursor$8;
  N_RESIZE = new Style$Cursor$9;
  SE_RESIZE = new Style$Cursor$10;
  SW_RESIZE = new Style$Cursor$11;
  S_RESIZE = new Style$Cursor$12;
  W_RESIZE = new Style$Cursor$13;
  TEXT = new Style$Cursor$14;
  WAIT = new Style$Cursor$15;
  HELP = new Style$Cursor$16;
  COL_RESIZE = new Style$Cursor$17;
  ROW_RESIZE = new Style$Cursor$18;
}

function Style$Cursor(enum$name, enum$ordinal){
  Enum.call(this, enum$name, enum$ordinal);
}

function values_0(){
  $clinit_Style$Cursor();
  return stampJavaTypeInfo(getClassLiteralForArray(Lcom_google_gwt_dom_client_Style$Cursor_2_classLit, 1), $intern_0, 12, 0, [DEFAULT, AUTO, CROSSHAIR, POINTER, MOVE, E_RESIZE, NE_RESIZE, NW_RESIZE, N_RESIZE, SE_RESIZE, SW_RESIZE, S_RESIZE, W_RESIZE, TEXT, WAIT, HELP, COL_RESIZE, ROW_RESIZE]);
}

defineClass(12, 5, $intern_2);
var AUTO, COL_RESIZE, CROSSHAIR, DEFAULT, E_RESIZE, HELP, MOVE, NE_RESIZE, NW_RESIZE, N_RESIZE, POINTER, ROW_RESIZE, SE_RESIZE, SW_RESIZE, S_RESIZE, TEXT, WAIT, W_RESIZE;
var Lcom_google_gwt_dom_client_Style$Cursor_2_classLit = createForEnum('com.google.gwt.dom.client', 'Style/Cursor', 12, Ljava_lang_Enum_2_classLit, values_0);
function Style$Cursor$1(){
  Style$Cursor.call(this, 'DEFAULT', 0);
}

defineClass(198, 12, $intern_2, Style$Cursor$1);
var Lcom_google_gwt_dom_client_Style$Cursor$1_2_classLit = createForEnum('com.google.gwt.dom.client', 'Style/Cursor/1', 198, Lcom_google_gwt_dom_client_Style$Cursor_2_classLit, null);
function Style$Cursor$10(){
  Style$Cursor.call(this, 'SE_RESIZE', 9);
}

defineClass(207, 12, $intern_2, Style$Cursor$10);
var Lcom_google_gwt_dom_client_Style$Cursor$10_2_classLit = createForEnum('com.google.gwt.dom.client', 'Style/Cursor/10', 207, Lcom_google_gwt_dom_client_Style$Cursor_2_classLit, null);
function Style$Cursor$11(){
  Style$Cursor.call(this, 'SW_RESIZE', 10);
}

defineClass(208, 12, $intern_2, Style$Cursor$11);
var Lcom_google_gwt_dom_client_Style$Cursor$11_2_classLit = createForEnum('com.google.gwt.dom.client', 'Style/Cursor/11', 208, Lcom_google_gwt_dom_client_Style$Cursor_2_classLit, null);
function Style$Cursor$12(){
  Style$Cursor.call(this, 'S_RESIZE', 11);
}

defineClass(209, 12, $intern_2, Style$Cursor$12);
var Lcom_google_gwt_dom_client_Style$Cursor$12_2_classLit = createForEnum('com.google.gwt.dom.client', 'Style/Cursor/12', 209, Lcom_google_gwt_dom_client_Style$Cursor_2_classLit, null);
function Style$Cursor$13(){
  Style$Cursor.call(this, 'W_RESIZE', 12);
}

defineClass(210, 12, $intern_2, Style$Cursor$13);
var Lcom_google_gwt_dom_client_Style$Cursor$13_2_classLit = createForEnum('com.google.gwt.dom.client', 'Style/Cursor/13', 210, Lcom_google_gwt_dom_client_Style$Cursor_2_classLit, null);
function Style$Cursor$14(){
  Style$Cursor.call(this, 'TEXT', 13);
}

defineClass(211, 12, $intern_2, Style$Cursor$14);
var Lcom_google_gwt_dom_client_Style$Cursor$14_2_classLit = createForEnum('com.google.gwt.dom.client', 'Style/Cursor/14', 211, Lcom_google_gwt_dom_client_Style$Cursor_2_classLit, null);
function Style$Cursor$15(){
  Style$Cursor.call(this, 'WAIT', 14);
}

defineClass(212, 12, $intern_2, Style$Cursor$15);
var Lcom_google_gwt_dom_client_Style$Cursor$15_2_classLit = createForEnum('com.google.gwt.dom.client', 'Style/Cursor/15', 212, Lcom_google_gwt_dom_client_Style$Cursor_2_classLit, null);
function Style$Cursor$16(){
  Style$Cursor.call(this, 'HELP', 15);
}

defineClass(213, 12, $intern_2, Style$Cursor$16);
var Lcom_google_gwt_dom_client_Style$Cursor$16_2_classLit = createForEnum('com.google.gwt.dom.client', 'Style/Cursor/16', 213, Lcom_google_gwt_dom_client_Style$Cursor_2_classLit, null);
function Style$Cursor$17(){
  Style$Cursor.call(this, 'COL_RESIZE', 16);
}

defineClass(214, 12, $intern_2, Style$Cursor$17);
var Lcom_google_gwt_dom_client_Style$Cursor$17_2_classLit = createForEnum('com.google.gwt.dom.client', 'Style/Cursor/17', 214, Lcom_google_gwt_dom_client_Style$Cursor_2_classLit, null);
function Style$Cursor$18(){
  Style$Cursor.call(this, 'ROW_RESIZE', 17);
}

defineClass(215, 12, $intern_2, Style$Cursor$18);
var Lcom_google_gwt_dom_client_Style$Cursor$18_2_classLit = createForEnum('com.google.gwt.dom.client', 'Style/Cursor/18', 215, Lcom_google_gwt_dom_client_Style$Cursor_2_classLit, null);
function Style$Cursor$2(){
  Style$Cursor.call(this, 'AUTO', 1);
}

defineClass(199, 12, $intern_2, Style$Cursor$2);
var Lcom_google_gwt_dom_client_Style$Cursor$2_2_classLit = createForEnum('com.google.gwt.dom.client', 'Style/Cursor/2', 199, Lcom_google_gwt_dom_client_Style$Cursor_2_classLit, null);
function Style$Cursor$3(){
  Style$Cursor.call(this, 'CROSSHAIR', 2);
}

defineClass(200, 12, $intern_2, Style$Cursor$3);
var Lcom_google_gwt_dom_client_Style$Cursor$3_2_classLit = createForEnum('com.google.gwt.dom.client', 'Style/Cursor/3', 200, Lcom_google_gwt_dom_client_Style$Cursor_2_classLit, null);
function Style$Cursor$4(){
  Style$Cursor.call(this, 'POINTER', 3);
}

defineClass(201, 12, $intern_2, Style$Cursor$4);
var Lcom_google_gwt_dom_client_Style$Cursor$4_2_classLit = createForEnum('com.google.gwt.dom.client', 'Style/Cursor/4', 201, Lcom_google_gwt_dom_client_Style$Cursor_2_classLit, null);
function Style$Cursor$5(){
  Style$Cursor.call(this, 'MOVE', 4);
}

defineClass(202, 12, $intern_2, Style$Cursor$5);
var Lcom_google_gwt_dom_client_Style$Cursor$5_2_classLit = createForEnum('com.google.gwt.dom.client', 'Style/Cursor/5', 202, Lcom_google_gwt_dom_client_Style$Cursor_2_classLit, null);
function Style$Cursor$6(){
  Style$Cursor.call(this, 'E_RESIZE', 5);
}

defineClass(203, 12, $intern_2, Style$Cursor$6);
var Lcom_google_gwt_dom_client_Style$Cursor$6_2_classLit = createForEnum('com.google.gwt.dom.client', 'Style/Cursor/6', 203, Lcom_google_gwt_dom_client_Style$Cursor_2_classLit, null);
function Style$Cursor$7(){
  Style$Cursor.call(this, 'NE_RESIZE', 6);
}

defineClass(204, 12, $intern_2, Style$Cursor$7);
var Lcom_google_gwt_dom_client_Style$Cursor$7_2_classLit = createForEnum('com.google.gwt.dom.client', 'Style/Cursor/7', 204, Lcom_google_gwt_dom_client_Style$Cursor_2_classLit, null);
function Style$Cursor$8(){
  Style$Cursor.call(this, 'NW_RESIZE', 7);
}

defineClass(205, 12, $intern_2, Style$Cursor$8);
var Lcom_google_gwt_dom_client_Style$Cursor$8_2_classLit = createForEnum('com.google.gwt.dom.client', 'Style/Cursor/8', 205, Lcom_google_gwt_dom_client_Style$Cursor_2_classLit, null);
function Style$Cursor$9(){
  Style$Cursor.call(this, 'N_RESIZE', 8);
}

defineClass(206, 12, $intern_2, Style$Cursor$9);
var Lcom_google_gwt_dom_client_Style$Cursor$9_2_classLit = createForEnum('com.google.gwt.dom.client', 'Style/Cursor/9', 206, Lcom_google_gwt_dom_client_Style$Cursor_2_classLit, null);
function $clinit_Style$Display(){
  $clinit_Style$Display = emptyMethod;
  NONE = new Style$Display$1;
  BLOCK = new Style$Display$2;
  INLINE = new Style$Display$3;
  INLINE_BLOCK = new Style$Display$4;
  INLINE_TABLE = new Style$Display$5;
  LIST_ITEM = new Style$Display$6;
  RUN_IN = new Style$Display$7;
  TABLE = new Style$Display$8;
  TABLE_CAPTION = new Style$Display$9;
  TABLE_COLUMN_GROUP = new Style$Display$10;
  TABLE_HEADER_GROUP = new Style$Display$11;
  TABLE_FOOTER_GROUP = new Style$Display$12;
  TABLE_ROW_GROUP = new Style$Display$13;
  TABLE_CELL = new Style$Display$14;
  TABLE_COLUMN = new Style$Display$15;
  TABLE_ROW = new Style$Display$16;
  INITIAL = new Style$Display$17;
  FLEX = new Style$Display$18;
  INLINE_FLEX = new Style$Display$19;
}

function Style$Display(enum$name, enum$ordinal){
  Enum.call(this, enum$name, enum$ordinal);
}

function values_1(){
  $clinit_Style$Display();
  return stampJavaTypeInfo(getClassLiteralForArray(Lcom_google_gwt_dom_client_Style$Display_2_classLit, 1), $intern_0, 11, 0, [NONE, BLOCK, INLINE, INLINE_BLOCK, INLINE_TABLE, LIST_ITEM, RUN_IN, TABLE, TABLE_CAPTION, TABLE_COLUMN_GROUP, TABLE_HEADER_GROUP, TABLE_FOOTER_GROUP, TABLE_ROW_GROUP, TABLE_CELL, TABLE_COLUMN, TABLE_ROW, INITIAL, FLEX, INLINE_FLEX]);
}

defineClass(11, 5, $intern_3);
var BLOCK, FLEX, INITIAL, INLINE, INLINE_BLOCK, INLINE_FLEX, INLINE_TABLE, LIST_ITEM, NONE, RUN_IN, TABLE, TABLE_CAPTION, TABLE_CELL, TABLE_COLUMN, TABLE_COLUMN_GROUP, TABLE_FOOTER_GROUP, TABLE_HEADER_GROUP, TABLE_ROW, TABLE_ROW_GROUP;
var Lcom_google_gwt_dom_client_Style$Display_2_classLit = createForEnum('com.google.gwt.dom.client', 'Style/Display', 11, Ljava_lang_Enum_2_classLit, values_1);
function Style$Display$1(){
  Style$Display.call(this, 'NONE', 0);
}

defineClass(216, 11, $intern_3, Style$Display$1);
var Lcom_google_gwt_dom_client_Style$Display$1_2_classLit = createForEnum('com.google.gwt.dom.client', 'Style/Display/1', 216, Lcom_google_gwt_dom_client_Style$Display_2_classLit, null);
function Style$Display$10(){
  Style$Display.call(this, 'TABLE_COLUMN_GROUP', 9);
}

defineClass(225, 11, $intern_3, Style$Display$10);
var Lcom_google_gwt_dom_client_Style$Display$10_2_classLit = createForEnum('com.google.gwt.dom.client', 'Style/Display/10', 225, Lcom_google_gwt_dom_client_Style$Display_2_classLit, null);
function Style$Display$11(){
  Style$Display.call(this, 'TABLE_HEADER_GROUP', 10);
}

defineClass(226, 11, $intern_3, Style$Display$11);
var Lcom_google_gwt_dom_client_Style$Display$11_2_classLit = createForEnum('com.google.gwt.dom.client', 'Style/Display/11', 226, Lcom_google_gwt_dom_client_Style$Display_2_classLit, null);
function Style$Display$12(){
  Style$Display.call(this, 'TABLE_FOOTER_GROUP', 11);
}

defineClass(227, 11, $intern_3, Style$Display$12);
var Lcom_google_gwt_dom_client_Style$Display$12_2_classLit = createForEnum('com.google.gwt.dom.client', 'Style/Display/12', 227, Lcom_google_gwt_dom_client_Style$Display_2_classLit, null);
function Style$Display$13(){
  Style$Display.call(this, 'TABLE_ROW_GROUP', 12);
}

defineClass(228, 11, $intern_3, Style$Display$13);
var Lcom_google_gwt_dom_client_Style$Display$13_2_classLit = createForEnum('com.google.gwt.dom.client', 'Style/Display/13', 228, Lcom_google_gwt_dom_client_Style$Display_2_classLit, null);
function Style$Display$14(){
  Style$Display.call(this, 'TABLE_CELL', 13);
}

defineClass(229, 11, $intern_3, Style$Display$14);
var Lcom_google_gwt_dom_client_Style$Display$14_2_classLit = createForEnum('com.google.gwt.dom.client', 'Style/Display/14', 229, Lcom_google_gwt_dom_client_Style$Display_2_classLit, null);
function Style$Display$15(){
  Style$Display.call(this, 'TABLE_COLUMN', 14);
}

defineClass(230, 11, $intern_3, Style$Display$15);
var Lcom_google_gwt_dom_client_Style$Display$15_2_classLit = createForEnum('com.google.gwt.dom.client', 'Style/Display/15', 230, Lcom_google_gwt_dom_client_Style$Display_2_classLit, null);
function Style$Display$16(){
  Style$Display.call(this, 'TABLE_ROW', 15);
}

defineClass(231, 11, $intern_3, Style$Display$16);
var Lcom_google_gwt_dom_client_Style$Display$16_2_classLit = createForEnum('com.google.gwt.dom.client', 'Style/Display/16', 231, Lcom_google_gwt_dom_client_Style$Display_2_classLit, null);
function Style$Display$17(){
  Style$Display.call(this, 'INITIAL', 16);
}

defineClass(232, 11, $intern_3, Style$Display$17);
var Lcom_google_gwt_dom_client_Style$Display$17_2_classLit = createForEnum('com.google.gwt.dom.client', 'Style/Display/17', 232, Lcom_google_gwt_dom_client_Style$Display_2_classLit, null);
function Style$Display$18(){
  Style$Display.call(this, 'FLEX', 17);
}

defineClass(233, 11, $intern_3, Style$Display$18);
var Lcom_google_gwt_dom_client_Style$Display$18_2_classLit = createForEnum('com.google.gwt.dom.client', 'Style/Display/18', 233, Lcom_google_gwt_dom_client_Style$Display_2_classLit, null);
function Style$Display$19(){
  Style$Display.call(this, 'INLINE_FLEX', 18);
}

defineClass(234, 11, $intern_3, Style$Display$19);
var Lcom_google_gwt_dom_client_Style$Display$19_2_classLit = createForEnum('com.google.gwt.dom.client', 'Style/Display/19', 234, Lcom_google_gwt_dom_client_Style$Display_2_classLit, null);
function Style$Display$2(){
  Style$Display.call(this, 'BLOCK', 1);
}

defineClass(217, 11, $intern_3, Style$Display$2);
var Lcom_google_gwt_dom_client_Style$Display$2_2_classLit = createForEnum('com.google.gwt.dom.client', 'Style/Display/2', 217, Lcom_google_gwt_dom_client_Style$Display_2_classLit, null);
function Style$Display$3(){
  Style$Display.call(this, 'INLINE', 2);
}

defineClass(218, 11, $intern_3, Style$Display$3);
var Lcom_google_gwt_dom_client_Style$Display$3_2_classLit = createForEnum('com.google.gwt.dom.client', 'Style/Display/3', 218, Lcom_google_gwt_dom_client_Style$Display_2_classLit, null);
function Style$Display$4(){
  Style$Display.call(this, 'INLINE_BLOCK', 3);
}

defineClass(219, 11, $intern_3, Style$Display$4);
var Lcom_google_gwt_dom_client_Style$Display$4_2_classLit = createForEnum('com.google.gwt.dom.client', 'Style/Display/4', 219, Lcom_google_gwt_dom_client_Style$Display_2_classLit, null);
function Style$Display$5(){
  Style$Display.call(this, 'INLINE_TABLE', 4);
}

defineClass(220, 11, $intern_3, Style$Display$5);
var Lcom_google_gwt_dom_client_Style$Display$5_2_classLit = createForEnum('com.google.gwt.dom.client', 'Style/Display/5', 220, Lcom_google_gwt_dom_client_Style$Display_2_classLit, null);
function Style$Display$6(){
  Style$Display.call(this, 'LIST_ITEM', 5);
}

defineClass(221, 11, $intern_3, Style$Display$6);
var Lcom_google_gwt_dom_client_Style$Display$6_2_classLit = createForEnum('com.google.gwt.dom.client', 'Style/Display/6', 221, Lcom_google_gwt_dom_client_Style$Display_2_classLit, null);
function Style$Display$7(){
  Style$Display.call(this, 'RUN_IN', 6);
}

defineClass(222, 11, $intern_3, Style$Display$7);
var Lcom_google_gwt_dom_client_Style$Display$7_2_classLit = createForEnum('com.google.gwt.dom.client', 'Style/Display/7', 222, Lcom_google_gwt_dom_client_Style$Display_2_classLit, null);
function Style$Display$8(){
  Style$Display.call(this, 'TABLE', 7);
}

defineClass(223, 11, $intern_3, Style$Display$8);
var Lcom_google_gwt_dom_client_Style$Display$8_2_classLit = createForEnum('com.google.gwt.dom.client', 'Style/Display/8', 223, Lcom_google_gwt_dom_client_Style$Display_2_classLit, null);
function Style$Display$9(){
  Style$Display.call(this, 'TABLE_CAPTION', 8);
}

defineClass(224, 11, $intern_3, Style$Display$9);
var Lcom_google_gwt_dom_client_Style$Display$9_2_classLit = createForEnum('com.google.gwt.dom.client', 'Style/Display/9', 224, Lcom_google_gwt_dom_client_Style$Display_2_classLit, null);
function $clinit_Style$Overflow(){
  $clinit_Style$Overflow = emptyMethod;
  VISIBLE = new Style$Overflow$1;
  HIDDEN = new Style$Overflow$2;
  SCROLL = new Style$Overflow$3;
  AUTO_0 = new Style$Overflow$4;
}

function Style$Overflow(enum$name, enum$ordinal){
  Enum.call(this, enum$name, enum$ordinal);
}

function values_2(){
  $clinit_Style$Overflow();
  return stampJavaTypeInfo(getClassLiteralForArray(Lcom_google_gwt_dom_client_Style$Overflow_2_classLit, 1), $intern_0, 38, 0, [VISIBLE, HIDDEN, SCROLL, AUTO_0]);
}

defineClass(38, 5, $intern_4);
var AUTO_0, HIDDEN, SCROLL, VISIBLE;
var Lcom_google_gwt_dom_client_Style$Overflow_2_classLit = createForEnum('com.google.gwt.dom.client', 'Style/Overflow', 38, Ljava_lang_Enum_2_classLit, values_2);
function Style$Overflow$1(){
  Style$Overflow.call(this, 'VISIBLE', 0);
}

defineClass(235, 38, $intern_4, Style$Overflow$1);
var Lcom_google_gwt_dom_client_Style$Overflow$1_2_classLit = createForEnum('com.google.gwt.dom.client', 'Style/Overflow/1', 235, Lcom_google_gwt_dom_client_Style$Overflow_2_classLit, null);
function Style$Overflow$2(){
  Style$Overflow.call(this, 'HIDDEN', 1);
}

defineClass(236, 38, $intern_4, Style$Overflow$2);
var Lcom_google_gwt_dom_client_Style$Overflow$2_2_classLit = createForEnum('com.google.gwt.dom.client', 'Style/Overflow/2', 236, Lcom_google_gwt_dom_client_Style$Overflow_2_classLit, null);
function Style$Overflow$3(){
  Style$Overflow.call(this, 'SCROLL', 2);
}

defineClass(237, 38, $intern_4, Style$Overflow$3);
var Lcom_google_gwt_dom_client_Style$Overflow$3_2_classLit = createForEnum('com.google.gwt.dom.client', 'Style/Overflow/3', 237, Lcom_google_gwt_dom_client_Style$Overflow_2_classLit, null);
function Style$Overflow$4(){
  Style$Overflow.call(this, 'AUTO', 3);
}

defineClass(238, 38, $intern_4, Style$Overflow$4);
var Lcom_google_gwt_dom_client_Style$Overflow$4_2_classLit = createForEnum('com.google.gwt.dom.client', 'Style/Overflow/4', 238, Lcom_google_gwt_dom_client_Style$Overflow_2_classLit, null);
function $clinit_Style$Position(){
  $clinit_Style$Position = emptyMethod;
  STATIC = new Style$Position$1;
  RELATIVE = new Style$Position$2;
  ABSOLUTE = new Style$Position$3;
  FIXED = new Style$Position$4;
}

function Style$Position(enum$name, enum$ordinal){
  Enum.call(this, enum$name, enum$ordinal);
}

function values_3(){
  $clinit_Style$Position();
  return stampJavaTypeInfo(getClassLiteralForArray(Lcom_google_gwt_dom_client_Style$Position_2_classLit, 1), $intern_0, 39, 0, [STATIC, RELATIVE, ABSOLUTE, FIXED]);
}

defineClass(39, 5, $intern_5);
var ABSOLUTE, FIXED, RELATIVE, STATIC;
var Lcom_google_gwt_dom_client_Style$Position_2_classLit = createForEnum('com.google.gwt.dom.client', 'Style/Position', 39, Ljava_lang_Enum_2_classLit, values_3);
function Style$Position$1(){
  Style$Position.call(this, 'STATIC', 0);
}

defineClass(239, 39, $intern_5, Style$Position$1);
var Lcom_google_gwt_dom_client_Style$Position$1_2_classLit = createForEnum('com.google.gwt.dom.client', 'Style/Position/1', 239, Lcom_google_gwt_dom_client_Style$Position_2_classLit, null);
function Style$Position$2(){
  Style$Position.call(this, 'RELATIVE', 1);
}

defineClass(240, 39, $intern_5, Style$Position$2);
var Lcom_google_gwt_dom_client_Style$Position$2_2_classLit = createForEnum('com.google.gwt.dom.client', 'Style/Position/2', 240, Lcom_google_gwt_dom_client_Style$Position_2_classLit, null);
function Style$Position$3(){
  Style$Position.call(this, 'ABSOLUTE', 2);
}

defineClass(241, 39, $intern_5, Style$Position$3);
var Lcom_google_gwt_dom_client_Style$Position$3_2_classLit = createForEnum('com.google.gwt.dom.client', 'Style/Position/3', 241, Lcom_google_gwt_dom_client_Style$Position_2_classLit, null);
function Style$Position$4(){
  Style$Position.call(this, 'FIXED', 3);
}

defineClass(242, 39, $intern_5, Style$Position$4);
var Lcom_google_gwt_dom_client_Style$Position$4_2_classLit = createForEnum('com.google.gwt.dom.client', 'Style/Position/4', 242, Lcom_google_gwt_dom_client_Style$Position_2_classLit, null);
function $clinit_Style$Unit(){
  $clinit_Style$Unit = emptyMethod;
  PX = new Style$Unit$1;
  PCT = new Style$Unit$2;
  EM = new Style$Unit$3;
  EX = new Style$Unit$4;
  PT = new Style$Unit$5;
  PC = new Style$Unit$6;
  IN = new Style$Unit$7;
  CM = new Style$Unit$8;
  MM = new Style$Unit$9;
}

function Style$Unit(enum$name, enum$ordinal){
  Enum.call(this, enum$name, enum$ordinal);
}

function values_4(){
  $clinit_Style$Unit();
  return stampJavaTypeInfo(getClassLiteralForArray(Lcom_google_gwt_dom_client_Style$Unit_2_classLit, 1), $intern_0, 19, 0, [PX, PCT, EM, EX, PT, PC, IN, CM, MM]);
}

defineClass(19, 5, $intern_6);
var CM, EM, EX, IN, MM, PC, PCT, PT, PX;
var Lcom_google_gwt_dom_client_Style$Unit_2_classLit = createForEnum('com.google.gwt.dom.client', 'Style/Unit', 19, Ljava_lang_Enum_2_classLit, values_4);
function Style$Unit$1(){
  Style$Unit.call(this, 'PX', 0);
}

defineClass(189, 19, $intern_6, Style$Unit$1);
var Lcom_google_gwt_dom_client_Style$Unit$1_2_classLit = createForEnum('com.google.gwt.dom.client', 'Style/Unit/1', 189, Lcom_google_gwt_dom_client_Style$Unit_2_classLit, null);
function Style$Unit$2(){
  Style$Unit.call(this, 'PCT', 1);
}

defineClass(190, 19, $intern_6, Style$Unit$2);
var Lcom_google_gwt_dom_client_Style$Unit$2_2_classLit = createForEnum('com.google.gwt.dom.client', 'Style/Unit/2', 190, Lcom_google_gwt_dom_client_Style$Unit_2_classLit, null);
function Style$Unit$3(){
  Style$Unit.call(this, 'EM', 2);
}

defineClass(191, 19, $intern_6, Style$Unit$3);
var Lcom_google_gwt_dom_client_Style$Unit$3_2_classLit = createForEnum('com.google.gwt.dom.client', 'Style/Unit/3', 191, Lcom_google_gwt_dom_client_Style$Unit_2_classLit, null);
function Style$Unit$4(){
  Style$Unit.call(this, 'EX', 3);
}

defineClass(192, 19, $intern_6, Style$Unit$4);
var Lcom_google_gwt_dom_client_Style$Unit$4_2_classLit = createForEnum('com.google.gwt.dom.client', 'Style/Unit/4', 192, Lcom_google_gwt_dom_client_Style$Unit_2_classLit, null);
function Style$Unit$5(){
  Style$Unit.call(this, 'PT', 4);
}

defineClass(193, 19, $intern_6, Style$Unit$5);
var Lcom_google_gwt_dom_client_Style$Unit$5_2_classLit = createForEnum('com.google.gwt.dom.client', 'Style/Unit/5', 193, Lcom_google_gwt_dom_client_Style$Unit_2_classLit, null);
function Style$Unit$6(){
  Style$Unit.call(this, 'PC', 5);
}

defineClass(194, 19, $intern_6, Style$Unit$6);
var Lcom_google_gwt_dom_client_Style$Unit$6_2_classLit = createForEnum('com.google.gwt.dom.client', 'Style/Unit/6', 194, Lcom_google_gwt_dom_client_Style$Unit_2_classLit, null);
function Style$Unit$7(){
  Style$Unit.call(this, 'IN', 6);
}

defineClass(195, 19, $intern_6, Style$Unit$7);
var Lcom_google_gwt_dom_client_Style$Unit$7_2_classLit = createForEnum('com.google.gwt.dom.client', 'Style/Unit/7', 195, Lcom_google_gwt_dom_client_Style$Unit_2_classLit, null);
function Style$Unit$8(){
  Style$Unit.call(this, 'CM', 7);
}

defineClass(196, 19, $intern_6, Style$Unit$8);
var Lcom_google_gwt_dom_client_Style$Unit$8_2_classLit = createForEnum('com.google.gwt.dom.client', 'Style/Unit/8', 196, Lcom_google_gwt_dom_client_Style$Unit_2_classLit, null);
function Style$Unit$9(){
  Style$Unit.call(this, 'MM', 8);
}

defineClass(197, 19, $intern_6, Style$Unit$9);
var Lcom_google_gwt_dom_client_Style$Unit$9_2_classLit = createForEnum('com.google.gwt.dom.client', 'Style/Unit/9', 197, Lcom_google_gwt_dom_client_Style$Unit_2_classLit, null);
function $clinit_Style$Visibility(){
  $clinit_Style$Visibility = emptyMethod;
  VISIBLE_0 = new Style$Visibility$1;
  HIDDEN_0 = new Style$Visibility$2;
}

function Style$Visibility(enum$name, enum$ordinal){
  Enum.call(this, enum$name, enum$ordinal);
}

function values_5(){
  $clinit_Style$Visibility();
  return stampJavaTypeInfo(getClassLiteralForArray(Lcom_google_gwt_dom_client_Style$Visibility_2_classLit, 1), $intern_0, 59, 0, [VISIBLE_0, HIDDEN_0]);
}

defineClass(59, 5, $intern_7);
var HIDDEN_0, VISIBLE_0;
var Lcom_google_gwt_dom_client_Style$Visibility_2_classLit = createForEnum('com.google.gwt.dom.client', 'Style/Visibility', 59, Ljava_lang_Enum_2_classLit, values_5);
function Style$Visibility$1(){
  Style$Visibility.call(this, 'VISIBLE', 0);
}

defineClass(243, 59, $intern_7, Style$Visibility$1);
var Lcom_google_gwt_dom_client_Style$Visibility$1_2_classLit = createForEnum('com.google.gwt.dom.client', 'Style/Visibility/1', 243, Lcom_google_gwt_dom_client_Style$Visibility_2_classLit, null);
function Style$Visibility$2(){
  Style$Visibility.call(this, 'HIDDEN', 1);
}

defineClass(244, 59, $intern_7, Style$Visibility$2);
var Lcom_google_gwt_dom_client_Style$Visibility$2_2_classLit = createForEnum('com.google.gwt.dom.client', 'Style/Visibility/2', 244, Lcom_google_gwt_dom_client_Style$Visibility_2_classLit, null);
function $clinit_StyleInjector(){
  $clinit_StyleInjector = emptyMethod;
  toInject = [];
  toInjectAtEnd = [];
  toInjectAtStart = [];
}

function flush(which){
  $clinit_StyleInjector();
  var css, maybeReturn, toReturn;
  toReturn = null;
  if (toInjectAtStart.length != 0) {
    css = toInjectAtStart.join('');
    maybeReturn = $injectStyleSheetAtStart(($clinit_StyleInjector$StyleInjectorImpl() , IMPL), css);
    toInjectAtStart == which && (toReturn = maybeReturn);
    toInjectAtStart.length = 0;
  }
  if (toInject.length != 0) {
    css = toInject.join('');
    maybeReturn = $injectStyleSheet(($clinit_StyleInjector$StyleInjectorImpl() , IMPL), css);
    toInject == which && (toReturn = maybeReturn);
    toInject.length = 0;
  }
  if (toInjectAtEnd.length != 0) {
    css = toInjectAtEnd.join('');
    maybeReturn = $injectStyleSheet(($clinit_StyleInjector$StyleInjectorImpl() , IMPL), css);
    toInjectAtEnd == which && (toReturn = maybeReturn);
    toInjectAtEnd.length = 0;
  }
  return toReturn;
}

var toInject, toInjectAtEnd, toInjectAtStart;
function $clinit_StyleInjector$StyleInjectorImpl(){
  $clinit_StyleInjector$StyleInjectorImpl = emptyMethod;
  IMPL = new StyleInjector$StyleInjectorImpl;
}

function $createElement_0(contents){
  var style;
  style = $doc.createElement('style');
  style['language'] = 'text/css';
  style.textContent = contents || '';
  return style;
}

function $getHead(this$static){
  var elt;
  if (!this$static.head_0) {
    elt = $doc.getElementsByTagName('head')[0];
    this$static.head_0 = elt;
  }
  return this$static.head_0;
}

function $injectStyleSheet(this$static, contents){
  var style;
  style = $createElement_0(contents);
  $appendChild($getHead(this$static), style);
  return style;
}

function $injectStyleSheetAtStart(this$static, contents){
  var style;
  style = $createElement_0(contents);
  $insertBefore($getHead(this$static), style, this$static.head_0.firstChild);
  return style;
}

function StyleInjector$StyleInjectorImpl(){
}

defineClass(291, 1, {}, StyleInjector$StyleInjectorImpl);
var IMPL;
var Lcom_google_gwt_dom_client_StyleInjector$StyleInjectorImpl_2_classLit = createForClass('com.google.gwt.dom.client', 'StyleInjector/StyleInjectorImpl', 291, Ljava_lang_Object_2_classLit);
defineClass(336, 1, {});
_.toString_0 = function toString_3(){
  return 'An event type';
}
;
var Lcom_google_web_bindery_event_shared_Event_2_classLit = createForClass('com.google.web.bindery.event.shared', 'Event', 336, Ljava_lang_Object_2_classLit);
function $overrideSource(this$static, source){
  this$static.source = source;
}

defineClass(337, 336, {});
_.dead = false;
var Lcom_google_gwt_event_shared_GwtEvent_2_classLit = createForClass('com.google.gwt.event.shared', 'GwtEvent', 337, Lcom_google_web_bindery_event_shared_Event_2_classLit);
function $setNativeEvent(this$static, nativeEvent){
  this$static.nativeEvent = nativeEvent;
}

function $setRelativeElement(this$static, relativeElem){
  this$static.relativeElem = relativeElem;
}

function fireNativeEvent(nativeEvent, handlerSource, relativeElem){
  var currentNative, currentRelativeElem, type_0, type$iterator, types;
  if (registered_0) {
    types = castTo($unsafeGet(registered_0, nativeEvent.type), 29);
    if (types) {
      for (type$iterator = types.iterator(); type$iterator.hasNext_0();) {
        type_0 = castTo(type$iterator.next_1(), 24);
        currentNative = type_0.flyweight.nativeEvent;
        currentRelativeElem = type_0.flyweight.relativeElem;
        $setNativeEvent(type_0.flyweight, nativeEvent);
        $setRelativeElement(type_0.flyweight, relativeElem);
        $fireEvent_0(handlerSource, type_0.flyweight);
        $setNativeEvent(type_0.flyweight, currentNative);
        $setRelativeElement(type_0.flyweight, currentRelativeElem);
      }
    }
  }
}

defineClass(340, 337, {});
_.getAssociatedType = function getAssociatedType(){
  return this.getAssociatedType_0();
}
;
var registered_0;
var Lcom_google_gwt_event_dom_client_DomEvent_2_classLit = createForClass('com.google.gwt.event.dom.client', 'DomEvent', 340, Lcom_google_gwt_event_shared_GwtEvent_2_classLit);
function $clinit_ContextMenuEvent(){
  $clinit_ContextMenuEvent = emptyMethod;
  TYPE = new DomEvent$Type('contextmenu', new ContextMenuEvent);
}

function ContextMenuEvent(){
}

defineClass(272, 340, {}, ContextMenuEvent);
_.dispatch = function dispatch(handler){
  castTo(handler, 353).this$01.defaultContextMenuEnabled || !!this.nativeEvent && (this.nativeEvent.preventDefault() , undefined);
}
;
_.getAssociatedType_0 = function getAssociatedType_0(){
  return TYPE;
}
;
var TYPE;
var Lcom_google_gwt_event_dom_client_ContextMenuEvent_2_classLit = createForClass('com.google.gwt.event.dom.client', 'ContextMenuEvent', 272, Lcom_google_gwt_event_dom_client_DomEvent_2_classLit);
defineClass(181, 1, {});
_.hashCode_0 = function hashCode_2(){
  return this.index_0;
}
;
_.toString_0 = function toString_4(){
  return 'Event type';
}
;
_.index_0 = 0;
var nextHashCode = 0;
var Lcom_google_web_bindery_event_shared_Event$Type_2_classLit = createForClass('com.google.web.bindery.event.shared', 'Event/Type', 181, Ljava_lang_Object_2_classLit);
function GwtEvent$Type(){
  this.index_0 = ++nextHashCode;
}

defineClass(89, 181, {}, GwtEvent$Type);
var Lcom_google_gwt_event_shared_GwtEvent$Type_2_classLit = createForClass('com.google.gwt.event.shared', 'GwtEvent/Type', 89, Lcom_google_web_bindery_event_shared_Event$Type_2_classLit);
function DomEvent$Type(eventName, flyweight){
  var types;
  GwtEvent$Type.call(this);
  this.flyweight = flyweight;
  !registered_0 && (registered_0 = new PrivateMap);
  types = castTo($unsafeGet(registered_0, eventName), 29);
  if (!types) {
    types = new ArrayList;
    $unsafePut(registered_0, eventName, types);
  }
  types.add_0(this);
  this.name_0 = eventName;
}

defineClass(24, 89, {24:1}, DomEvent$Type);
var Lcom_google_gwt_event_dom_client_DomEvent$Type_2_classLit = createForClass('com.google.gwt.event.dom.client', 'DomEvent/Type', 24, Lcom_google_gwt_event_shared_GwtEvent$Type_2_classLit);
defineClass(341, 340, {});
var Lcom_google_gwt_event_dom_client_HumanInputEvent_2_classLit = createForClass('com.google.gwt.event.dom.client', 'HumanInputEvent', 341, Lcom_google_gwt_event_dom_client_DomEvent_2_classLit);
defineClass(342, 341, {});
var Lcom_google_gwt_event_dom_client_MouseEvent_2_classLit = createForClass('com.google.gwt.event.dom.client', 'MouseEvent', 342, Lcom_google_gwt_event_dom_client_HumanInputEvent_2_classLit);
function $clinit_DoubleClickEvent(){
  $clinit_DoubleClickEvent = emptyMethod;
  TYPE_0 = new DomEvent$Type('dblclick', new DoubleClickEvent);
}

function $dispatch(this$static, handler){
  $onDoubleClick(handler, this$static);
}

function DoubleClickEvent(){
}

defineClass(281, 342, {}, DoubleClickEvent);
_.dispatch = function dispatch_0(handler){
  $dispatch(this, castTo(handler, 351));
}
;
_.getAssociatedType_0 = function getAssociatedType_1(){
  return TYPE_0;
}
;
var TYPE_0;
var Lcom_google_gwt_event_dom_client_DoubleClickEvent_2_classLit = createForClass('com.google.gwt.event.dom.client', 'DoubleClickEvent', 281, Lcom_google_gwt_event_dom_client_MouseEvent_2_classLit);
function $clinit_MouseDownEvent(){
  $clinit_MouseDownEvent = emptyMethod;
  TYPE_1 = new DomEvent$Type('mousedown', new MouseDownEvent);
}

function MouseDownEvent(){
}

defineClass(282, 342, {}, MouseDownEvent);
_.dispatch = function dispatch_1(handler){
  castTo(handler, 303).onMouseDown(this);
}
;
_.getAssociatedType_0 = function getAssociatedType_2(){
  return TYPE_1;
}
;
var TYPE_1;
var Lcom_google_gwt_event_dom_client_MouseDownEvent_2_classLit = createForClass('com.google.gwt.event.dom.client', 'MouseDownEvent', 282, Lcom_google_gwt_event_dom_client_MouseEvent_2_classLit);
function $clinit_MouseMoveEvent(){
  $clinit_MouseMoveEvent = emptyMethod;
  TYPE_2 = new DomEvent$Type('mousemove', new MouseMoveEvent);
}

function MouseMoveEvent(){
}

defineClass(284, 342, {}, MouseMoveEvent);
_.dispatch = function dispatch_2(handler){
  castTo(handler, 304).onMouseMove(this);
}
;
_.getAssociatedType_0 = function getAssociatedType_3(){
  return TYPE_2;
}
;
var TYPE_2;
var Lcom_google_gwt_event_dom_client_MouseMoveEvent_2_classLit = createForClass('com.google.gwt.event.dom.client', 'MouseMoveEvent', 284, Lcom_google_gwt_event_dom_client_MouseEvent_2_classLit);
function $clinit_MouseUpEvent(){
  $clinit_MouseUpEvent = emptyMethod;
  TYPE_3 = new DomEvent$Type('mouseup', new MouseUpEvent);
}

function $dispatch_0(this$static, handler){
  $onMouseUp(handler, this$static);
}

function MouseUpEvent(){
}

defineClass(283, 342, {}, MouseUpEvent);
_.dispatch = function dispatch_3(handler){
  $dispatch_0(this, castTo(handler, 352));
}
;
_.getAssociatedType_0 = function getAssociatedType_4(){
  return TYPE_3;
}
;
var TYPE_3;
var Lcom_google_gwt_event_dom_client_MouseUpEvent_2_classLit = createForClass('com.google.gwt.event.dom.client', 'MouseUpEvent', 283, Lcom_google_gwt_event_dom_client_MouseEvent_2_classLit);
function $unsafeGet(this$static, key){
  return this$static.map_0[key];
}

function $unsafePut(this$static, key, value_0){
  this$static.map_0[key] = value_0;
}

function PrivateMap(){
  this.map_0 = {};
}

defineClass(288, 1, {}, PrivateMap);
var Lcom_google_gwt_event_dom_client_PrivateMap_2_classLit = createForClass('com.google.gwt.event.dom.client', 'PrivateMap', 288, Ljava_lang_Object_2_classLit);
defineClass(344, 341, {});
var Lcom_google_gwt_event_dom_client_TouchEvent_2_classLit = createForClass('com.google.gwt.event.dom.client', 'TouchEvent', 344, Lcom_google_gwt_event_dom_client_HumanInputEvent_2_classLit);
function $clinit_TouchCancelEvent(){
  $clinit_TouchCancelEvent = emptyMethod;
  TYPE_4 = new DomEvent$Type('touchcancel', new TouchCancelEvent);
}

function TouchCancelEvent(){
}

defineClass(280, 344, {}, TouchCancelEvent);
_.dispatch = function dispatch_4(handler){
  castTo(handler, 311).onTouchCancel(this);
}
;
_.getAssociatedType_0 = function getAssociatedType_5(){
  return TYPE_4;
}
;
var TYPE_4;
var Lcom_google_gwt_event_dom_client_TouchCancelEvent_2_classLit = createForClass('com.google.gwt.event.dom.client', 'TouchCancelEvent', 280, Lcom_google_gwt_event_dom_client_TouchEvent_2_classLit);
function $clinit_TouchEndEvent(){
  $clinit_TouchEndEvent = emptyMethod;
  TYPE_5 = new DomEvent$Type('touchend', new TouchEndEvent);
}

function $dispatch_1(this$static, handler){
  handler.this$01.containerScrollStartPosY = -1;
  handler.this$01.containerScrollStartPosX = -1;
  $onTouchOrMouseUp(handler.this$01, this$static.nativeEvent);
  !!this$static.nativeEvent && (this$static.nativeEvent.preventDefault() , undefined);
}

function TouchEndEvent(){
}

defineClass(278, 344, {}, TouchEndEvent);
_.dispatch = function dispatch_5(handler){
  $dispatch_1(this, castTo(handler, 354));
}
;
_.getAssociatedType_0 = function getAssociatedType_6(){
  return TYPE_5;
}
;
var TYPE_5;
var Lcom_google_gwt_event_dom_client_TouchEndEvent_2_classLit = createForClass('com.google.gwt.event.dom.client', 'TouchEndEvent', 278, Lcom_google_gwt_event_dom_client_TouchEvent_2_classLit);
function $clinit_TouchMoveEvent(){
  $clinit_TouchMoveEvent = emptyMethod;
  TYPE_6 = new DomEvent$Type('touchmove', new TouchMoveEvent);
}

function TouchMoveEvent(){
}

defineClass(279, 344, {}, TouchMoveEvent);
_.dispatch = function dispatch_6(handler){
  castTo(handler, 310).onTouchMove(this);
}
;
_.getAssociatedType_0 = function getAssociatedType_7(){
  return TYPE_6;
}
;
var TYPE_6;
var Lcom_google_gwt_event_dom_client_TouchMoveEvent_2_classLit = createForClass('com.google.gwt.event.dom.client', 'TouchMoveEvent', 279, Lcom_google_gwt_event_dom_client_TouchEvent_2_classLit);
function $clinit_TouchStartEvent(){
  $clinit_TouchStartEvent = emptyMethod;
  TYPE_7 = new DomEvent$Type('touchstart', new TouchStartEvent);
}

function TouchStartEvent(){
}

defineClass(277, 344, {}, TouchStartEvent);
_.dispatch = function dispatch_7(handler){
  castTo(handler, 309).onTouchStart(this);
}
;
_.getAssociatedType_0 = function getAssociatedType_8(){
  return TYPE_7;
}
;
var TYPE_7;
var Lcom_google_gwt_event_dom_client_TouchStartEvent_2_classLit = createForClass('com.google.gwt.event.dom.client', 'TouchStartEvent', 277, Lcom_google_gwt_event_dom_client_TouchEvent_2_classLit);
function CloseEvent_0(){
}

function fire_0(source){
  var event_0;
  if (TYPE_8) {
    event_0 = new CloseEvent_0;
    $fireEvent(source, event_0);
  }
}

defineClass(290, 337, {}, CloseEvent_0);
_.dispatch = function dispatch_8(handler){
  castTo(handler, 355);
  detachWidgets();
}
;
_.getAssociatedType = function getAssociatedType_9(){
  return TYPE_8;
}
;
var TYPE_8;
var Lcom_google_gwt_event_logical_shared_CloseEvent_2_classLit = createForClass('com.google.gwt.event.logical.shared', 'CloseEvent', 290, Lcom_google_gwt_event_shared_GwtEvent_2_classLit);
function $addHandler(this$static, type_0, handler){
  return new LegacyHandlerWrapper($doAdd(this$static.eventBus, type_0, handler));
}

function $fireEvent(this$static, event_0){
  var e, oldSource;
  !event_0.dead || (event_0.dead = false , event_0.source = null);
  oldSource = event_0.source;
  $overrideSource(event_0, this$static.source);
  try {
    $doFire(this$static.eventBus, event_0);
  }
   catch ($e0) {
    $e0 = toJava($e0);
    if (instanceOf($e0, 58)) {
      e = $e0;
      throw toJs(new UmbrellaException_0(e.causes));
    }
     else 
      throw toJs($e0);
  }
   finally {
    oldSource == null?(event_0.dead = true , event_0.source = null):(event_0.source = oldSource);
  }
}

function HandlerManager(source){
  this.eventBus = new HandlerManager$Bus;
  this.source = source;
}

defineClass(78, 1, {25:1}, HandlerManager);
var Lcom_google_gwt_event_shared_HandlerManager_2_classLit = createForClass('com.google.gwt.event.shared', 'HandlerManager', 78, Ljava_lang_Object_2_classLit);
defineClass(338, 1, {});
var Lcom_google_web_bindery_event_shared_EventBus_2_classLit = createForClass('com.google.web.bindery.event.shared', 'EventBus', 338, Ljava_lang_Object_2_classLit);
function $defer(this$static, command){
  !this$static.deferredDeltas && (this$static.deferredDeltas = new ArrayList);
  $add_2(this$static.deferredDeltas, command);
}

function $doAdd(this$static, type_0, handler){
  var l;
  if (!type_0) {
    throw toJs(new NullPointerException_0('Cannot add a handler with a null type'));
  }
  if (!handler) {
    throw toJs(new NullPointerException_0('Cannot add a null handler'));
  }
  this$static.firingDepth > 0?$defer(this$static, new SimpleEventBus$2(this$static, type_0, handler)):(l = $ensureHandlerList(this$static, type_0, null) , l.add_0(handler));
  return new SimpleEventBus$1(this$static, type_0, handler);
}

function $doAddNow(this$static, type_0, source, handler){
  var l;
  l = $ensureHandlerList(this$static, type_0, source);
  l.add_0(handler);
}

function $doFire(this$static, event_0){
  var causes, directHandlers, e, handler, handlers, it;
  if (!event_0) {
    throw toJs(new NullPointerException_0('Cannot fire null event'));
  }
  try {
    ++this$static.firingDepth;
    handlers = (directHandlers = $getHandlerList(this$static, event_0.getAssociatedType(), null) , directHandlers);
    causes = null;
    it = this$static.isReverseOrder?handlers.listIterator_0(handlers.size_1()):handlers.listIterator();
    while (this$static.isReverseOrder?it.hasPrevious():it.hasNext_0()) {
      handler = this$static.isReverseOrder?it.previous():it.next_1();
      try {
        event_0.dispatch(castTo(handler, 15));
      }
       catch ($e0) {
        $e0 = toJava($e0);
        if (instanceOf($e0, 9)) {
          e = $e0;
          !causes && (causes = new HashSet);
          $put_0(causes.map_0, e, causes);
        }
         else 
          throw toJs($e0);
      }
    }
    if (causes) {
      throw toJs(new UmbrellaException(causes));
    }
  }
   finally {
    --this$static.firingDepth;
    this$static.firingDepth == 0 && $handleQueuedAddsAndRemoves(this$static);
  }
}

function $doRemoveNow(this$static, type_0, source, handler){
  var l, removed, sourceMap;
  l = $getHandlerList(this$static, type_0, source);
  removed = l.remove_2(handler);
  removed && l.isEmpty() && (sourceMap = castTo($get_2(this$static.map_0, type_0), 35) , castTo(sourceMap.remove_3(source), 29) , sourceMap.isEmpty() && $remove_2(this$static.map_0, type_0) , undefined);
}

function $ensureHandlerList(this$static, type_0, source){
  var handlers, sourceMap;
  sourceMap = castTo($get_2(this$static.map_0, type_0), 35);
  if (!sourceMap) {
    sourceMap = new HashMap;
    $put_0(this$static.map_0, type_0, sourceMap);
  }
  handlers = castTo(sourceMap.get_0(source), 29);
  if (!handlers) {
    handlers = new ArrayList;
    sourceMap.put(source, handlers);
  }
  return handlers;
}

function $getHandlerList(this$static, type_0, source){
  var handlers, sourceMap;
  sourceMap = castTo($get_2(this$static.map_0, type_0), 35);
  if (!sourceMap) {
    return $clinit_Collections() , $clinit_Collections() , EMPTY_LIST;
  }
  handlers = castTo(sourceMap.get_0(source), 29);
  if (!handlers) {
    return $clinit_Collections() , $clinit_Collections() , EMPTY_LIST;
  }
  return handlers;
}

function $handleQueuedAddsAndRemoves(this$static){
  var c, c$iterator;
  if (this$static.deferredDeltas) {
    try {
      for (c$iterator = new ArrayList$1(this$static.deferredDeltas); c$iterator.i < c$iterator.this$01.array.length;) {
        c = castTo($next_0(c$iterator), 312);
        c.execute_1();
      }
    }
     finally {
      this$static.deferredDeltas = null;
    }
  }
}

defineClass(182, 338, {});
_.firingDepth = 0;
_.isReverseOrder = false;
var Lcom_google_web_bindery_event_shared_SimpleEventBus_2_classLit = createForClass('com.google.web.bindery.event.shared', 'SimpleEventBus', 182, Lcom_google_web_bindery_event_shared_EventBus_2_classLit);
function $doRemove(this$static, type_0, source, handler){
  this$static.firingDepth > 0?$defer(this$static, new SimpleEventBus$3(this$static, type_0, source, handler)):$doRemoveNow(this$static, type_0, source, handler);
}

function HandlerManager$Bus(){
  this.map_0 = new HashMap;
  this.isReverseOrder = false;
}

defineClass(183, 182, {}, HandlerManager$Bus);
var Lcom_google_gwt_event_shared_HandlerManager$Bus_2_classLit = createForClass('com.google.gwt.event.shared', 'HandlerManager/Bus', 183, Lcom_google_web_bindery_event_shared_SimpleEventBus_2_classLit);
function LegacyHandlerWrapper(real){
  this.real = real;
}

defineClass(289, 1, {}, LegacyHandlerWrapper);
var Lcom_google_gwt_event_shared_LegacyHandlerWrapper_2_classLit = createForClass('com.google.gwt.event.shared', 'LegacyHandlerWrapper', 289, Ljava_lang_Object_2_classLit);
function UmbrellaException(causes){
  var cause, cause$iterator, i_0;
  RuntimeException_1.call(this, makeMessage(causes), causes.isEmpty()?null:castTo(causes.iterator().next_1(), 9));
  this.causes = causes;
  i_0 = 0;
  for (cause$iterator = causes.iterator(); cause$iterator.hasNext_0();) {
    cause = castTo(cause$iterator.next_1(), 9);
    if (i_0++ == 0) {
      continue;
    }
    $addSuppressed(this, cause);
  }
}

function makeMessage(causes){
  var b, count, first, t, t$iterator;
  count = causes.size_1();
  if (count == 0) {
    return null;
  }
  b = new StringBuilder_0(count == 1?'Exception caught: ':count + ' exceptions caught: ');
  first = true;
  for (t$iterator = causes.iterator(); t$iterator.hasNext_0();) {
    t = castTo(t$iterator.next_1(), 9);
    first?(first = false):(b.string += '; ' , b);
    $append_2(b, t.getMessage());
  }
  return b.string;
}

defineClass(58, 18, $intern_8, UmbrellaException);
var Lcom_google_web_bindery_event_shared_UmbrellaException_2_classLit = createForClass('com.google.web.bindery.event.shared', 'UmbrellaException', 58, Ljava_lang_RuntimeException_2_classLit);
function UmbrellaException_0(causes){
  UmbrellaException.call(this, causes);
}

defineClass(109, 58, $intern_8, UmbrellaException_0);
var Lcom_google_gwt_event_shared_UmbrellaException_2_classLit = createForClass('com.google.gwt.event.shared', 'UmbrellaException', 109, Lcom_google_web_bindery_event_shared_UmbrellaException_2_classLit);
function $clinit_DateTimeFormat(){
  $clinit_DateTimeFormat = emptyMethod;
  cache = new HashMap;
}

function $addPart(this$static, buf, count){
  var oldLength;
  if (buf.string.length > 0) {
    $add_2(this$static.patternParts, new DateTimeFormat$PatternPart(buf.string, count));
    oldLength = buf.string.length;
    0 < oldLength?(buf.string = buf.string.substr(0, 0)):0 > oldLength && (buf.string += valueOf_3(initUnidimensionalArray(C_classLit, $intern_9, 42, -oldLength, 15, 1)));
  }
}

function $format(this$static, date, timeZone){
  var ch_0, diff, i_0, j, keepDate, keepTime, n, toAppendTo, trailQuote;
  !timeZone && (timeZone = createTimeZone(date.jsdate.getTimezoneOffset()));
  diff = (date.jsdate.getTimezoneOffset() - (timeZone.standardOffset - $getDaylightAdjustment(timeZone, date))) * 60000;
  keepDate = new Date_1(add_1(fromDouble_0(date.jsdate.getTime()), diff));
  keepTime = keepDate;
  if (keepDate.jsdate.getTimezoneOffset() != date.jsdate.getTimezoneOffset()) {
    diff > 0?(diff -= $intern_10):(diff += $intern_10);
    keepTime = new Date_1(add_1(fromDouble_0(date.jsdate.getTime()), diff));
  }
  toAppendTo = new StringBuilder;
  n = this$static.pattern.length;
  for (i_0 = 0; i_0 < n;) {
    ch_0 = $charAt(this$static.pattern, i_0);
    if (ch_0 >= 97 && ch_0 <= 122 || ch_0 >= 65 && ch_0 <= 90) {
      for (j = i_0 + 1; j < n && $charAt(this$static.pattern, j) == ch_0; ++j)
      ;
      $subFormat(toAppendTo, ch_0, j - i_0, date, keepDate, keepTime, timeZone);
      i_0 = j;
    }
     else if (ch_0 == 39) {
      ++i_0;
      if (i_0 < n && $charAt(this$static.pattern, i_0) == 39) {
        toAppendTo.string += "'";
        ++i_0;
        continue;
      }
      trailQuote = false;
      while (!trailQuote) {
        j = i_0;
        while (j < n && $charAt(this$static.pattern, j) != 39) {
          ++j;
        }
        if (j >= n) {
          throw toJs(new IllegalArgumentException("Missing trailing '"));
        }
        j + 1 < n && $charAt(this$static.pattern, j + 1) == 39?++j:(trailQuote = true);
        $append_2(toAppendTo, $substring_0(this$static.pattern, i_0, j));
        i_0 = j + 1;
      }
    }
     else {
      toAppendTo.string += String.fromCharCode(ch_0);
      ++i_0;
    }
  }
  return toAppendTo.string;
}

function $formatFractionalSeconds(buf, count, date){
  var time, value_0;
  time = fromDouble_0(date.jsdate.getTime());
  if (compare_0(time, 0) < 0) {
    value_0 = 1000 - toInt_0(mod(neg_0(time), 1000));
    value_0 == 1000 && (value_0 = 0);
  }
   else {
    value_0 = toInt_0(mod(time, 1000));
  }
  if (count == 1) {
    value_0 = $wnd.Math.min((value_0 + 50) / 100 | 0, 9);
    buf.string += String.fromCharCode(48 + value_0 & $intern_11);
  }
   else if (count == 2) {
    value_0 = $wnd.Math.min((value_0 + 5) / 10 | 0, 99);
    $zeroPaddingNumber(buf, value_0, 2);
  }
   else {
    $zeroPaddingNumber(buf, value_0, 3);
    count > 3 && $zeroPaddingNumber(buf, 0, count - 3);
  }
}

function $formatMonth(buf, count, date){
  var value_0;
  value_0 = date.jsdate.getMonth();
  switch (count) {
    case 5:
      $append_2(buf, stampJavaTypeInfo(getClassLiteralForArray(Ljava_lang_String_2_classLit, 1), $intern_0, 2, 6, ['J', 'F', 'M', 'A', 'M', 'J', 'J', 'A', 'S', 'O', 'N', 'D'])[value_0]);
      break;
    case 4:
      $append_2(buf, stampJavaTypeInfo(getClassLiteralForArray(Ljava_lang_String_2_classLit, 1), $intern_0, 2, 6, ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'])[value_0]);
      break;
    case 3:
      $append_2(buf, stampJavaTypeInfo(getClassLiteralForArray(Ljava_lang_String_2_classLit, 1), $intern_0, 2, 6, ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'])[value_0]);
      break;
    default:$zeroPaddingNumber(buf, value_0 + 1, count);
  }
}

function $formatYear(buf, count, date){
  var value_0;
  value_0 = date.jsdate.getFullYear() - 1900 + 1900;
  value_0 < 0 && (value_0 = -value_0);
  switch (count) {
    case 1:
      buf.string += value_0;
      break;
    case 2:
      $zeroPaddingNumber(buf, value_0 % 100, 2);
      break;
    default:$zeroPaddingNumber(buf, value_0, count);
  }
}

function $getNextCharCountInPattern(pattern, start_0){
  var ch_0, next;
  ch_0 = (checkCriticalStringElementIndex(start_0, pattern.length) , pattern.charCodeAt(start_0));
  next = start_0 + 1;
  while (next < pattern.length && (checkCriticalStringElementIndex(next, pattern.length) , pattern.charCodeAt(next) == ch_0)) {
    ++next;
  }
  return next - start_0;
}

function $identifyAbutStart(this$static){
  var abut, i_0, len;
  abut = false;
  len = this$static.patternParts.array.length;
  for (i_0 = 0; i_0 < len; i_0++) {
    if ($isNumeric(castTo($get_4(this$static.patternParts, i_0), 71))) {
      if (!abut && i_0 + 1 < len && $isNumeric(castTo($get_4(this$static.patternParts, i_0 + 1), 71))) {
        abut = true;
        castTo($get_4(this$static.patternParts, i_0), 71).abutStart = true;
      }
    }
     else {
      abut = false;
    }
  }
}

function $isNumeric(part){
  var i_0;
  if (part.count <= 0) {
    return false;
  }
  i_0 = $indexOf_0('MLydhHmsSDkK', fromCodePoint($charAt(part.text_0, 0)));
  return i_0 > 1 || i_0 >= 0 && part.count < 3;
}

function $parsePattern(this$static, pattern){
  var buf, ch_0, count, i_0, inQuote;
  buf = new StringBuilder;
  inQuote = false;
  for (i_0 = 0; i_0 < pattern.length; i_0++) {
    ch_0 = (checkCriticalStringElementIndex(i_0, pattern.length) , pattern.charCodeAt(i_0));
    if (ch_0 == 32) {
      $addPart(this$static, buf, 0);
      buf.string += ' ';
      $addPart(this$static, buf, 0);
      while (i_0 + 1 < pattern.length && (checkCriticalStringElementIndex(i_0 + 1, pattern.length) , pattern.charCodeAt(i_0 + 1) == 32)) {
        ++i_0;
      }
      continue;
    }
    if (inQuote) {
      if (ch_0 == 39) {
        if (i_0 + 1 < pattern.length && (checkCriticalStringElementIndex(i_0 + 1, pattern.length) , pattern.charCodeAt(i_0 + 1) == 39)) {
          buf.string += "'";
          ++i_0;
        }
         else {
          inQuote = false;
        }
      }
       else {
        buf.string += String.fromCharCode(ch_0);
      }
      continue;
    }
    if ($indexOf_0('GyMLdkHmsSEcDahKzZv', fromCodePoint(ch_0)) > 0) {
      $addPart(this$static, buf, 0);
      buf.string += String.fromCharCode(ch_0);
      count = $getNextCharCountInPattern(pattern, i_0);
      $addPart(this$static, buf, count);
      i_0 += count - 1;
      continue;
    }
    if (ch_0 == 39) {
      if (i_0 + 1 < pattern.length && (checkCriticalStringElementIndex(i_0 + 1, pattern.length) , pattern.charCodeAt(i_0 + 1) == 39)) {
        buf.string += "'";
        ++i_0;
      }
       else {
        inQuote = true;
      }
    }
     else {
      buf.string += String.fromCharCode(ch_0);
    }
  }
  $addPart(this$static, buf, 0);
  $identifyAbutStart(this$static);
}

function $subFormat(buf, ch_0, count, date, adjustedDate, adjustedTime, timezone){
  var value_0, value0, value1, value10, value2, value3, value4, value5, value6, value7, value8, value9;
  switch (ch_0) {
    case 71:
      value0 = adjustedDate.jsdate.getFullYear() - 1900 >= -1900?1:0;
      count >= 4?$append_2(buf, stampJavaTypeInfo(getClassLiteralForArray(Ljava_lang_String_2_classLit, 1), $intern_0, 2, 6, ['Before Christ', 'Anno Domini'])[value0]):$append_2(buf, stampJavaTypeInfo(getClassLiteralForArray(Ljava_lang_String_2_classLit, 1), $intern_0, 2, 6, ['BC', 'AD'])[value0]);
      break;
    case 121:
      $formatYear(buf, count, adjustedDate);
      break;
    case 77:
      $formatMonth(buf, count, adjustedDate);
      break;
    case 107:
      value1 = adjustedTime.jsdate.getHours();
      value1 == 0?$zeroPaddingNumber(buf, 24, count):$zeroPaddingNumber(buf, value1, count);
      break;
    case 83:
      $formatFractionalSeconds(buf, count, adjustedTime);
      break;
    case 69:
      value2 = adjustedDate.jsdate.getDay();
      count == 5?$append_2(buf, stampJavaTypeInfo(getClassLiteralForArray(Ljava_lang_String_2_classLit, 1), $intern_0, 2, 6, ['S', 'M', 'T', 'W', 'T', 'F', 'S'])[value2]):count == 4?$append_2(buf, stampJavaTypeInfo(getClassLiteralForArray(Ljava_lang_String_2_classLit, 1), $intern_0, 2, 6, ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'])[value2]):$append_2(buf, stampJavaTypeInfo(getClassLiteralForArray(Ljava_lang_String_2_classLit, 1), $intern_0, 2, 6, ['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat'])[value2]);
      break;
    case 97:
      adjustedTime.jsdate.getHours() >= 12 && adjustedTime.jsdate.getHours() < 24?$append_2(buf, stampJavaTypeInfo(getClassLiteralForArray(Ljava_lang_String_2_classLit, 1), $intern_0, 2, 6, ['AM', 'PM'])[1]):$append_2(buf, stampJavaTypeInfo(getClassLiteralForArray(Ljava_lang_String_2_classLit, 1), $intern_0, 2, 6, ['AM', 'PM'])[0]);
      break;
    case 104:
      value3 = adjustedTime.jsdate.getHours() % 12;
      value3 == 0?$zeroPaddingNumber(buf, 12, count):$zeroPaddingNumber(buf, value3, count);
      break;
    case 75:
      value4 = adjustedTime.jsdate.getHours() % 12;
      $zeroPaddingNumber(buf, value4, count);
      break;
    case 72:
      value5 = adjustedTime.jsdate.getHours();
      $zeroPaddingNumber(buf, value5, count);
      break;
    case 99:
      value6 = adjustedDate.jsdate.getDay();
      count == 5?$append_2(buf, stampJavaTypeInfo(getClassLiteralForArray(Ljava_lang_String_2_classLit, 1), $intern_0, 2, 6, ['S', 'M', 'T', 'W', 'T', 'F', 'S'])[value6]):count == 4?$append_2(buf, stampJavaTypeInfo(getClassLiteralForArray(Ljava_lang_String_2_classLit, 1), $intern_0, 2, 6, ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'])[value6]):count == 3?$append_2(buf, stampJavaTypeInfo(getClassLiteralForArray(Ljava_lang_String_2_classLit, 1), $intern_0, 2, 6, ['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat'])[value6]):$zeroPaddingNumber(buf, value6, 1);
      break;
    case 76:
      value7 = adjustedDate.jsdate.getMonth();
      count == 5?$append_2(buf, stampJavaTypeInfo(getClassLiteralForArray(Ljava_lang_String_2_classLit, 1), $intern_0, 2, 6, ['J', 'F', 'M', 'A', 'M', 'J', 'J', 'A', 'S', 'O', 'N', 'D'])[value7]):count == 4?$append_2(buf, stampJavaTypeInfo(getClassLiteralForArray(Ljava_lang_String_2_classLit, 1), $intern_0, 2, 6, ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'])[value7]):count == 3?$append_2(buf, stampJavaTypeInfo(getClassLiteralForArray(Ljava_lang_String_2_classLit, 1), $intern_0, 2, 6, ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'])[value7]):$zeroPaddingNumber(buf, value7 + 1, count);
      break;
    case 81:
      value8 = adjustedDate.jsdate.getMonth() / 3 | 0;
      count < 4?$append_2(buf, stampJavaTypeInfo(getClassLiteralForArray(Ljava_lang_String_2_classLit, 1), $intern_0, 2, 6, ['Q1', 'Q2', 'Q3', 'Q4'])[value8]):$append_2(buf, stampJavaTypeInfo(getClassLiteralForArray(Ljava_lang_String_2_classLit, 1), $intern_0, 2, 6, ['1st quarter', '2nd quarter', '3rd quarter', '4th quarter'])[value8]);
      break;
    case 100:
      value9 = adjustedDate.jsdate.getDate();
      $zeroPaddingNumber(buf, value9, count);
      break;
    case 109:
      value10 = adjustedTime.jsdate.getMinutes();
      $zeroPaddingNumber(buf, value10, count);
      break;
    case 115:
      value_0 = adjustedTime.jsdate.getSeconds();
      $zeroPaddingNumber(buf, value_0, count);
      break;
    case 122:
      count < 4?$append_2(buf, timezone.tzNames[$getDaylightAdjustment(timezone, date) > 0?2:0]):$append_2(buf, timezone.tzNames[$getDaylightAdjustment(timezone, date) > 0?3:1]);
      break;
    case 118:
      $append_2(buf, timezone.timezoneID);
      break;
    case 90:
      count < 3?$append_2(buf, $getRFCTimeZoneString(timezone, date)):count == 3?$append_2(buf, $getISOTimeZoneString(timezone, date)):$append_2(buf, composeGMTString(timezone.standardOffset - $getDaylightAdjustment(timezone, date)));
      break;
    default:return false;
  }
  return true;
}

function $zeroPaddingNumber(buf, value_0, minWidth){
  var b, i_0;
  b = 10;
  for (i_0 = 0; i_0 < minWidth - 1; i_0++) {
    value_0 < b && (buf.string += '0' , buf);
    b *= 10;
  }
  buf.string += value_0;
}

function DateTimeFormat(pattern){
  $clinit_DateTimeFormat();
  this.patternParts = new ArrayList;
  this.pattern = pattern;
  $parsePattern(this, pattern);
}

function getFormat(pattern, dtfi){
  $clinit_DateTimeFormat();
  var defaultDtfi, dtf;
  defaultDtfi = $getDateTimeFormatInfo(($clinit_LocaleInfo() , $clinit_LocaleInfo() , instance_1));
  dtf = null;
  dtfi == defaultDtfi && (dtf = castTo($getStringValue(cache, pattern), 70));
  if (!dtf) {
    dtf = new DateTimeFormat(pattern);
    dtfi == defaultDtfi && $putStringValue(cache, pattern, dtf);
  }
  return dtf;
}

defineClass(70, 1, {70:1}, DateTimeFormat);
var cache;
var Lcom_google_gwt_i18n_shared_DateTimeFormat_2_classLit = createForClass('com.google.gwt.i18n.shared', 'DateTimeFormat', 70, Ljava_lang_Object_2_classLit);
function $clinit_DateTimeFormat_0(){
  $clinit_DateTimeFormat_0 = emptyMethod;
  $clinit_DateTimeFormat();
  cache_0 = new HashMap;
}

function DateTimeFormat_0(pattern){
  DateTimeFormat.call(this, pattern);
}

function getFormat_0(pattern, dtfi){
  $clinit_DateTimeFormat_0();
  var defaultDtfi, dtf;
  defaultDtfi = $getDateTimeFormatInfo(($clinit_LocaleInfo() , $clinit_LocaleInfo() , instance_1));
  dtf = null;
  dtfi == defaultDtfi && (dtf = castTo($getStringValue(cache_0, pattern), 96));
  if (!dtf) {
    dtf = new DateTimeFormat_0(pattern);
    dtfi == defaultDtfi && $putStringValue(cache_0, pattern, dtf);
  }
  return dtf;
}

defineClass(96, 70, {96:1, 70:1}, DateTimeFormat_0);
var cache_0;
var Lcom_google_gwt_i18n_client_DateTimeFormat_2_classLit = createForClass('com.google.gwt.i18n.client', 'DateTimeFormat', 96, Lcom_google_gwt_i18n_shared_DateTimeFormat_2_classLit);
defineClass(348, 1, {});
var Lcom_google_gwt_i18n_shared_DefaultDateTimeFormatInfo_2_classLit = createForClass('com.google.gwt.i18n.shared', 'DefaultDateTimeFormatInfo', 348, Ljava_lang_Object_2_classLit);
defineClass(349, 348, {});
var Lcom_google_gwt_i18n_client_DefaultDateTimeFormatInfo_2_classLit = createForClass('com.google.gwt.i18n.client', 'DefaultDateTimeFormatInfo', 349, Lcom_google_gwt_i18n_shared_DefaultDateTimeFormatInfo_2_classLit);
function $clinit_LocaleInfo(){
  $clinit_LocaleInfo = emptyMethod;
  instance_1 = new LocaleInfo;
}

function $getDateTimeFormatInfo(this$static){
  !this$static.dateTimeFormatInfo && (this$static.dateTimeFormatInfo = new DateTimeFormatInfoImpl);
  return this$static.dateTimeFormatInfo;
}

function LocaleInfo(){
}

defineClass(287, 1, {}, LocaleInfo);
var instance_1;
var Lcom_google_gwt_i18n_client_LocaleInfo_2_classLit = createForClass('com.google.gwt.i18n.client', 'LocaleInfo', 287, Ljava_lang_Object_2_classLit);
function $getDaylightAdjustment(this$static, date){
  var index_0, timeInHours;
  if (this$static.transitionPoints == null) {
    return 0;
  }
  timeInHours = div_0(div_0(fromDouble_0(date.jsdate.getTime()), 1000), 3600);
  index_0 = 0;
  while (index_0 < this$static.transitionPoints.length && gte_0(timeInHours, this$static.transitionPoints[index_0])) {
    ++index_0;
  }
  return index_0 == 0?0:this$static.adjustments[index_0 - 1];
}

function $getISOTimeZoneString(this$static, date){
  var data_0, offset;
  offset = -(this$static.standardOffset - $getDaylightAdjustment(this$static, date));
  data_0 = stampJavaTypeInfo(getClassLiteralForArray(C_classLit, 1), $intern_9, 42, 15, [43, 48, 48, 58, 48, 48]);
  if (offset < 0) {
    data_0[0] = 45;
    offset = -offset;
  }
  data_0[1] = data_0[1] + ((offset / 60 | 0) / 10 | 0) & $intern_11;
  data_0[2] = data_0[2] + (offset / 60 | 0) % 10 & $intern_11;
  data_0[4] = data_0[4] + (offset % 60 / 10 | 0) & $intern_11;
  data_0[5] = data_0[5] + offset % 10 & $intern_11;
  return valueOf_4(data_0, data_0.length);
}

function $getOffset(this$static, date){
  return this$static.standardOffset - $getDaylightAdjustment(this$static, date);
}

function $getRFCTimeZoneString(this$static, date){
  var data_0, offset;
  offset = -(this$static.standardOffset - $getDaylightAdjustment(this$static, date));
  data_0 = stampJavaTypeInfo(getClassLiteralForArray(C_classLit, 1), $intern_9, 42, 15, [43, 48, 48, 48, 48]);
  if (offset < 0) {
    data_0[0] = 45;
    offset = -offset;
  }
  data_0[1] = data_0[1] + ((offset / 60 | 0) / 10 | 0) & $intern_11;
  data_0[2] = data_0[2] + (offset / 60 | 0) % 10 & $intern_11;
  data_0[3] = data_0[3] + (offset % 60 / 10 | 0) & $intern_11;
  data_0[4] = data_0[4] + offset % 10 & $intern_11;
  return valueOf_4(data_0, data_0.length);
}

function TimeZone(){
}

function composeGMTString(offset){
  var data_0;
  data_0 = stampJavaTypeInfo(getClassLiteralForArray(C_classLit, 1), $intern_9, 42, 15, [71, 77, 84, 45, 48, 48, 58, 48, 48]);
  if (offset <= 0) {
    data_0[3] = 43;
    offset = -offset;
  }
  data_0[4] = data_0[4] + ((offset / 60 | 0) / 10 | 0) & $intern_11;
  data_0[5] = data_0[5] + (offset / 60 | 0) % 10 & $intern_11;
  data_0[7] = data_0[7] + (offset % 60 / 10 | 0) & $intern_11;
  data_0[8] = data_0[8] + offset % 10 & $intern_11;
  return valueOf_4(data_0, data_0.length);
}

function composePOSIXTimeZoneID(offset){
  var str;
  if (offset == 0) {
    return 'Etc/GMT';
  }
  if (offset < 0) {
    offset = -offset;
    str = 'Etc/GMT-';
  }
   else {
    str = 'Etc/GMT+';
  }
  return str + offsetDisplay(offset);
}

function composeUTCString(offset){
  var str;
  if (offset == 0) {
    return 'UTC';
  }
  if (offset < 0) {
    offset = -offset;
    str = 'UTC+';
  }
   else {
    str = 'UTC-';
  }
  return str + offsetDisplay(offset);
}

function createTimeZone(timeZoneOffsetInMinutes){
  var tz;
  tz = new TimeZone;
  tz.standardOffset = timeZoneOffsetInMinutes;
  tz.timezoneID = composePOSIXTimeZoneID(timeZoneOffsetInMinutes);
  tz.tzNames = initUnidimensionalArray(Ljava_lang_String_2_classLit, $intern_0, 2, 2, 6, 1);
  tz.tzNames[0] = composeUTCString(timeZoneOffsetInMinutes);
  tz.tzNames[1] = composeUTCString(timeZoneOffsetInMinutes);
  tz.transitionPoints = null;
  tz.adjustments = null;
  return tz;
}

function createTimeZone_0(timezoneData){
  var i_0, i0, jsTimezoneNames, transitionNum, transitions, tz;
  tz = new TimeZone;
  tz.timezoneID = timezoneData.id;
  tz.standardOffset = -timezoneData.std_offset;
  jsTimezoneNames = timezoneData.names;
  tz.tzNames = initUnidimensionalArray(Ljava_lang_String_2_classLit, $intern_0, 2, jsTimezoneNames.length, 6, 1);
  for (i0 = 0; i0 < jsTimezoneNames.length; i0++) {
    tz.tzNames[i0] = jsTimezoneNames[i0];
  }
  transitions = timezoneData.transitions;
  if (!transitions || transitions.length == 0) {
    tz.transitionPoints = null;
    tz.adjustments = null;
  }
   else {
    transitionNum = transitions.length / 2 | 0;
    tz.transitionPoints = initUnidimensionalArray(I_classLit, $intern_9, 42, transitionNum, 15, 1);
    tz.adjustments = initUnidimensionalArray(I_classLit, $intern_9, 42, transitionNum, 15, 1);
    for (i_0 = 0; i_0 < transitionNum; ++i_0) {
      tz.transitionPoints[i_0] = transitions[i_0 * 2];
      tz.adjustments[i_0] = transitions[i_0 * 2 + 1];
    }
  }
  return tz;
}

function offsetDisplay(offset){
  var hour, mins;
  hour = offset / 60 | 0;
  mins = offset % 60;
  if (mins == 0) {
    return '' + hour;
  }
  return '' + hour + ':' + ('' + mins);
}

defineClass(113, 1, {}, TimeZone);
_.standardOffset = 0;
var Lcom_google_gwt_i18n_client_TimeZone_2_classLit = createForClass('com.google.gwt.i18n.client', 'TimeZone', 113, Ljava_lang_Object_2_classLit);
function DateTimeFormatInfoImpl(){
}

defineClass(297, 349, {}, DateTimeFormatInfoImpl);
var Lcom_google_gwt_i18n_client_impl_cldr_DateTimeFormatInfoImpl_2_classLit = createForClass('com.google.gwt.i18n.client.impl.cldr', 'DateTimeFormatInfoImpl', 297, Lcom_google_gwt_i18n_client_DefaultDateTimeFormatInfo_2_classLit);
function DateTimeFormat$PatternPart(txt, cnt){
  this.text_0 = txt;
  this.count = cnt;
  this.abutStart = false;
}

defineClass(71, 1, {71:1}, DateTimeFormat$PatternPart);
_.abutStart = false;
_.count = 0;
var Lcom_google_gwt_i18n_shared_DateTimeFormat$PatternPart_2_classLit = createForClass('com.google.gwt.i18n.shared', 'DateTimeFormat/PatternPart', 71, Ljava_lang_Object_2_classLit);
defineClass(335, 1, {});
_.isArray_0 = function isArray(){
  return null;
}
;
_.isBoolean = function isBoolean_0(){
  return null;
}
;
_.isNull = function isNull(){
  return null;
}
;
_.isNumber = function isNumber_0(){
  return null;
}
;
_.isObject = function isObject(){
  return null;
}
;
_.isString = function isString(){
  return null;
}
;
var Lcom_google_gwt_json_client_JSONValue_2_classLit = createForClass('com.google.gwt.json.client', 'JSONValue', 335, Ljava_lang_Object_2_classLit);
function $get(this$static, index_0){
  var v = this$static.jsArray[index_0];
  var func = ($clinit_JSONParser() , typeMap)[typeof v];
  return func?func(v):throwUnknownTypeException(typeof v);
}

function $set(this$static, index_0, value_0){
  var previous;
  previous = $get(this$static, index_0);
  $set0(this$static, index_0, value_0);
  return previous;
}

function $set0(this$static, index_0, value_0){
  if (value_0) {
    var func = value_0.getUnwrapper();
    value_0 = func(value_0);
  }
   else {
    value_0 = undefined;
  }
  this$static.jsArray[index_0] = value_0;
}

function JSONArray(){
  this.jsArray = [];
}

function JSONArray_0(arr){
  this.jsArray = arr;
}

function unwrap(value_0){
  return value_0.jsArray;
}

defineClass(77, 335, {77:1}, JSONArray, JSONArray_0);
_.equals_0 = function equals_1(other){
  if (!instanceOf(other, 77)) {
    return false;
  }
  return $equals(this.jsArray, castTo(other, 77).jsArray);
}
;
_.getUnwrapper = function getUnwrapper(){
  return unwrap;
}
;
_.hashCode_0 = function hashCode_3(){
  return $hashCode(this.jsArray);
}
;
_.isArray_0 = function isArray_0(){
  return this;
}
;
_.toString_0 = function toString_5(){
  var c, i_0, sb;
  sb = new StringBuilder_0('[');
  for (i_0 = 0 , c = this.jsArray.length; i_0 < c; i_0++) {
    i_0 > 0 && (sb.string += ',' , sb);
    $append_1(sb, $get(this, i_0));
  }
  sb.string += ']';
  return sb.string;
}
;
var Lcom_google_gwt_json_client_JSONArray_2_classLit = createForClass('com.google.gwt.json.client', 'JSONArray', 77, Lcom_google_gwt_json_client_JSONValue_2_classLit);
function $clinit_JSONBoolean(){
  $clinit_JSONBoolean = emptyMethod;
  FALSE = new JSONBoolean(false);
  TRUE = new JSONBoolean(true);
}

function JSONBoolean(value_0){
  this.value_0 = value_0;
}

function unwrap_0(value_0){
  return value_0.value_0;
}

defineClass(112, 335, {}, JSONBoolean);
_.getUnwrapper = function getUnwrapper_0(){
  return unwrap_0;
}
;
_.isBoolean = function isBoolean_1(){
  return this;
}
;
_.toString_0 = function toString_6(){
  return $clinit_Boolean() , '' + this.value_0;
}
;
_.value_0 = false;
var FALSE, TRUE;
var Lcom_google_gwt_json_client_JSONBoolean_2_classLit = createForClass('com.google.gwt.json.client', 'JSONBoolean', 112, Lcom_google_gwt_json_client_JSONValue_2_classLit);
function JSONException(message){
  RuntimeException_0.call(this, message);
}

defineClass(246, 18, $intern_1, JSONException);
var Lcom_google_gwt_json_client_JSONException_2_classLit = createForClass('com.google.gwt.json.client', 'JSONException', 246, Ljava_lang_RuntimeException_2_classLit);
function $clinit_JSONNull(){
  $clinit_JSONNull = emptyMethod;
  instance_2 = new JSONNull;
}

function JSONNull(){
}

function unwrap_1(){
  return null;
}

defineClass(188, 335, {}, JSONNull);
_.getUnwrapper = function getUnwrapper_1(){
  return unwrap_1;
}
;
_.isNull = function isNull_0(){
  if (this != instance_2) {
    return null;
  }
  return this;
}
;
_.toString_0 = function toString_7(){
  return 'null';
}
;
var instance_2;
var Lcom_google_gwt_json_client_JSONNull_2_classLit = createForClass('com.google.gwt.json.client', 'JSONNull', 188, Lcom_google_gwt_json_client_JSONValue_2_classLit);
function JSONNumber(value_0){
  this.value_0 = value_0;
}

function unwrap_2(value_0){
  return value_0.value_0;
}

defineClass(49, 335, {49:1}, JSONNumber);
_.equals_0 = function equals_2(other){
  if (!instanceOf(other, 49)) {
    return false;
  }
  return this.value_0 == castTo(other, 49).value_0;
}
;
_.getUnwrapper = function getUnwrapper_2(){
  return unwrap_2;
}
;
_.hashCode_0 = function hashCode_4(){
  return $hashCode_0(this.value_0);
}
;
_.isNumber = function isNumber_1(){
  return this;
}
;
_.toString_0 = function toString_8(){
  return this.value_0 + '';
}
;
_.value_0 = 0;
var Lcom_google_gwt_json_client_JSONNumber_2_classLit = createForClass('com.google.gwt.json.client', 'JSONNumber', 49, Lcom_google_gwt_json_client_JSONValue_2_classLit);
function $computeKeys0(this$static, result){
  var jsObject = this$static.jsObject;
  var i_0 = 0;
  for (var key in jsObject) {
    jsObject.hasOwnProperty(key) && (result[i_0++] = key);
  }
  return result;
}

function $get_0(this$static, key){
  if (key == null) {
    throw toJs(new NullPointerException);
  }
  return $get0(this$static, key);
}

function $get0(this$static, key){
  var jsObject = this$static.jsObject;
  var v;
  key = String(key);
  jsObject.hasOwnProperty(key) && (v = jsObject[key]);
  var func = ($clinit_JSONParser() , typeMap)[typeof v];
  var ret = func?func(v):throwUnknownTypeException(typeof v);
  return ret;
}

function $put(this$static, key, jsonValue){
  var previous;
  previous = $get_0(this$static, key);
  $put0(this$static, key, jsonValue);
  return previous;
}

function $put0(this$static, key, value_0){
  if (value_0) {
    var func = value_0.getUnwrapper();
    this$static.jsObject[key] = func(value_0);
  }
   else {
    delete this$static.jsObject[key];
  }
}

function JSONObject(){
  JSONObject_0.call(this, {});
}

function JSONObject_0(jsValue){
  this.jsObject = jsValue;
}

function unwrap_3(value_0){
  return value_0.jsObject;
}

defineClass(41, 335, {41:1}, JSONObject, JSONObject_0);
_.equals_0 = function equals_3(other){
  if (!instanceOf(other, 41)) {
    return false;
  }
  return $equals(this.jsObject, castTo(other, 41).jsObject);
}
;
_.getUnwrapper = function getUnwrapper_3(){
  return unwrap_3;
}
;
_.hashCode_0 = function hashCode_5(){
  return $hashCode(this.jsObject);
}
;
_.isObject = function isObject_0(){
  return this;
}
;
_.toString_0 = function toString_9(){
  var first, key, key$index, key$max, keys_0, sb;
  sb = new StringBuilder_0('{');
  first = true;
  keys_0 = $computeKeys0(this, initUnidimensionalArray(Ljava_lang_String_2_classLit, $intern_0, 2, 0, 6, 1));
  for (key$index = 0 , key$max = keys_0.length; key$index < key$max; ++key$index) {
    key = keys_0[key$index];
    first?(first = false):(sb.string += ', ' , sb);
    $append_2(sb, escapeValue(key));
    sb.string += ':';
    $append_1(sb, $get_0(this, key));
  }
  sb.string += '}';
  return sb.string;
}
;
var Lcom_google_gwt_json_client_JSONObject_2_classLit = createForClass('com.google.gwt.json.client', 'JSONObject', 41, Lcom_google_gwt_json_client_JSONValue_2_classLit);
function $clinit_JSONParser(){
  $clinit_JSONParser = emptyMethod;
  typeMap = {'boolean':createBoolean, 'number':createNumber, 'string':createString, 'object':createObject, 'function':createObject, 'undefined':createUndefined};
}

function createBoolean(v){
  return $clinit_JSONBoolean() , v?TRUE:FALSE;
}

function createNumber(v){
  return new JSONNumber(v);
}

function createObject(o){
  if (!o) {
    return $clinit_JSONNull() , instance_2;
  }
  var v = o.valueOf?o.valueOf():o;
  if (v !== o) {
    var func = typeMap[typeof v];
    return func?func(v):throwUnknownTypeException(typeof v);
  }
   else if (o instanceof Array || o instanceof $wnd.Array) {
    return new JSONArray_0(o);
  }
   else {
    return new JSONObject_0(o);
  }
}

function createString(v){
  return new JSONString(v);
}

function createUndefined(){
  return null;
}

function throwUnknownTypeException(typeString){
  $clinit_JSONParser();
  throw toJs(new JSONException("Unexpected typeof result '" + typeString + "'; please report this bug to the GWT team"));
}

var typeMap;
function JSONString(value_0){
  if (value_0 == null) {
    throw toJs(new NullPointerException);
  }
  this.value_0 = value_0;
}

function unwrap_4(value_0){
  return value_0.value_0;
}

defineClass(37, 335, {37:1}, JSONString);
_.equals_0 = function equals_4(other){
  if (!instanceOf(other, 37)) {
    return false;
  }
  return $equals_0(this.value_0, castTo(other, 37).value_0);
}
;
_.getUnwrapper = function getUnwrapper_4(){
  return unwrap_4;
}
;
_.hashCode_0 = function hashCode_6(){
  return getHashCode_1(this.value_0);
}
;
_.isString = function isString_0(){
  return this;
}
;
_.toString_0 = function toString_10(){
  return escapeValue(this.value_0);
}
;
var Lcom_google_gwt_json_client_JSONString_2_classLit = createForClass('com.google.gwt.json.client', 'JSONString', 37, Lcom_google_gwt_json_client_JSONValue_2_classLit);
function canSet(array, value_0){
  var elementTypeCategory;
  switch (getElementTypeCategory(array)) {
    case 6:
      return instanceOfString(value_0);
    case 7:
      return instanceOfDouble(value_0);
    case 8:
      return instanceOfBoolean(value_0);
    case 3:
      return Array.isArray(value_0) && (elementTypeCategory = getElementTypeCategory(value_0) , !(elementTypeCategory >= 14 && elementTypeCategory <= 16));
    case 11:
      return value_0 != null && isFunction(value_0);
    case 12:
      return value_0 != null && (typeof value_0 === 'object' || typeof value_0 == 'function');
    case 0:
      return canCast(value_0, array.__elementTypeId$);
    case 2:
      return isJsObjectOrFunction(value_0) && !(value_0.typeMarker === typeMarkerFn);
    case 1:
      return isJsObjectOrFunction(value_0) && !(value_0.typeMarker === typeMarkerFn) || canCast(value_0, array.__elementTypeId$);
    default:return true;
  }
}

function getClassLiteralForArray(clazz, dimensions){
  return getClassLiteralForArray_0(clazz, dimensions);
}

function getElementTypeCategory(array){
  return array.__elementTypeCategory$ == null?10:array.__elementTypeCategory$;
}

function initUnidimensionalArray(leafClassLiteral, castableTypeMap, elementTypeId, length_0, elementTypeCategory, dimensions){
  var result;
  result = initializeArrayElementsWithDefaults(elementTypeCategory, length_0);
  elementTypeCategory != 10 && stampJavaTypeInfo(getClassLiteralForArray(leafClassLiteral, dimensions), castableTypeMap, elementTypeId, elementTypeCategory, result);
  return result;
}

function initializeArrayElementsWithDefaults(elementTypeCategory, length_0){
  var array = new Array(length_0);
  var initValue;
  switch (elementTypeCategory) {
    case 14:
    case 15:
      initValue = 0;
      break;
    case 16:
      initValue = false;
      break;
    default:return array;
  }
  for (var i_0 = 0; i_0 < length_0; ++i_0) {
    array[i_0] = initValue;
  }
  return array;
}

function isJavaArray(src_0){
  return Array.isArray(src_0) && src_0.typeMarker === typeMarkerFn;
}

function setCheck(array, index_0, value_0){
  checkCriticalArrayType(value_0 == null || canSet(array, value_0));
  return array[index_0] = value_0;
}

function stampJavaTypeInfo(arrayClass, castableTypeMap, elementTypeId, elementTypeCategory, array){
  array.___clazz = arrayClass;
  array.castableTypeMap = castableTypeMap;
  array.typeMarker = typeMarkerFn;
  array.__elementTypeId$ = elementTypeId;
  array.__elementTypeCategory$ = elementTypeCategory;
  return array;
}

function stampJavaTypeInfo_0(array, referenceType){
  getElementTypeCategory(referenceType) != 10 && stampJavaTypeInfo(getClass__Ljava_lang_Class___devirtual$(referenceType), referenceType.castableTypeMap, referenceType.__elementTypeId$, getElementTypeCategory(referenceType), array);
  return array;
}

function create(value_0){
  var a0, a1, a2;
  a0 = value_0 & $intern_12;
  a1 = value_0 >> 22 & $intern_12;
  a2 = value_0 < 0?$intern_13:0;
  return create0(a0, a1, a2);
}

function create_0(a){
  return create0(a.l, a.m, a.h);
}

function create0(l, m, h){
  return {l:l, m:m, h:h};
}

function divMod(a, b, computeRemainder){
  var aIsCopy, aIsMinValue, aIsNegative, bpower, c, negative;
  if (b.l == 0 && b.m == 0 && b.h == 0) {
    throw toJs(new ArithmeticException);
  }
  if (a.l == 0 && a.m == 0 && a.h == 0) {
    computeRemainder && (remainder = create0(0, 0, 0));
    return create0(0, 0, 0);
  }
  if (b.h == $intern_14 && b.m == 0 && b.l == 0) {
    return divModByMinValue(a, computeRemainder);
  }
  negative = false;
  if (b.h >> 19 != 0) {
    b = neg(b);
    negative = true;
  }
  bpower = powerOfTwo(b);
  aIsNegative = false;
  aIsMinValue = false;
  aIsCopy = false;
  if (a.h == $intern_14 && a.m == 0 && a.l == 0) {
    aIsMinValue = true;
    aIsNegative = true;
    if (bpower == -1) {
      a = create_0(($clinit_BigLongLib$Const() , MAX_VALUE));
      aIsCopy = true;
      negative = !negative;
    }
     else {
      c = shr(a, bpower);
      negative && negate(c);
      computeRemainder && (remainder = create0(0, 0, 0));
      return c;
    }
  }
   else if (a.h >> 19 != 0) {
    aIsNegative = true;
    a = neg(a);
    aIsCopy = true;
    negative = !negative;
  }
  if (bpower != -1) {
    return divModByShift(a, bpower, negative, aIsNegative, computeRemainder);
  }
  if (compare(a, b) < 0) {
    computeRemainder && (aIsNegative?(remainder = neg(a)):(remainder = create0(a.l, a.m, a.h)));
    return create0(0, 0, 0);
  }
  return divModHelper(aIsCopy?a:create0(a.l, a.m, a.h), b, negative, aIsNegative, aIsMinValue, computeRemainder);
}

function divModByMinValue(a, computeRemainder){
  if (a.h == $intern_14 && a.m == 0 && a.l == 0) {
    computeRemainder && (remainder = create0(0, 0, 0));
    return create_0(($clinit_BigLongLib$Const() , ONE));
  }
  computeRemainder && (remainder = create0(a.l, a.m, a.h));
  return create0(0, 0, 0);
}

function divModByShift(a, bpower, negative, aIsNegative, computeRemainder){
  var c;
  c = shr(a, bpower);
  negative && negate(c);
  if (computeRemainder) {
    a = maskRight(a, bpower);
    aIsNegative?(remainder = neg(a)):(remainder = create0(a.l, a.m, a.h));
  }
  return c;
}

function divModHelper(a, b, negative, aIsNegative, aIsMinValue, computeRemainder){
  var bshift, gte, quotient, shift_0, a1, a2, a0;
  shift_0 = numberOfLeadingZeros(b) - numberOfLeadingZeros(a);
  bshift = shl(b, shift_0);
  quotient = create0(0, 0, 0);
  while (shift_0 >= 0) {
    gte = trialSubtract(a, bshift);
    if (gte) {
      shift_0 < 22?(quotient.l |= 1 << shift_0 , undefined):shift_0 < 44?(quotient.m |= 1 << shift_0 - 22 , undefined):(quotient.h |= 1 << shift_0 - 44 , undefined);
      if (a.l == 0 && a.m == 0 && a.h == 0) {
        break;
      }
    }
    a1 = bshift.m;
    a2 = bshift.h;
    a0 = bshift.l;
    bshift.h = a2 >>> 1;
    bshift.m = a1 >>> 1 | (a2 & 1) << 21;
    bshift.l = a0 >>> 1 | (a1 & 1) << 21;
    --shift_0;
  }
  negative && negate(quotient);
  if (computeRemainder) {
    if (aIsNegative) {
      remainder = neg(a);
      aIsMinValue && (remainder = sub_0(remainder, ($clinit_BigLongLib$Const() , ONE)));
    }
     else {
      remainder = create0(a.l, a.m, a.h);
    }
  }
  return quotient;
}

function maskRight(a, bits){
  var b0, b1, b2;
  if (bits <= 22) {
    b0 = a.l & (1 << bits) - 1;
    b1 = b2 = 0;
  }
   else if (bits <= 44) {
    b0 = a.l;
    b1 = a.m & (1 << bits - 22) - 1;
    b2 = 0;
  }
   else {
    b0 = a.l;
    b1 = a.m;
    b2 = a.h & (1 << bits - 44) - 1;
  }
  return create0(b0, b1, b2);
}

function negate(a){
  var neg0, neg1, neg2;
  neg0 = ~a.l + 1 & $intern_12;
  neg1 = ~a.m + (neg0 == 0?1:0) & $intern_12;
  neg2 = ~a.h + (neg0 == 0 && neg1 == 0?1:0) & $intern_13;
  a.l = neg0;
  a.m = neg1;
  a.h = neg2;
}

function numberOfLeadingZeros(a){
  var b1, b2;
  b2 = numberOfLeadingZeros_0(a.h);
  if (b2 == 32) {
    b1 = numberOfLeadingZeros_0(a.m);
    return b1 == 32?numberOfLeadingZeros_0(a.l) + 32:b1 + 20 - 10;
  }
   else {
    return b2 - 12;
  }
}

function powerOfTwo(a){
  var h, l, m;
  l = a.l;
  if ((l & l - 1) != 0) {
    return -1;
  }
  m = a.m;
  if ((m & m - 1) != 0) {
    return -1;
  }
  h = a.h;
  if ((h & h - 1) != 0) {
    return -1;
  }
  if (h == 0 && m == 0 && l == 0) {
    return -1;
  }
  if (h == 0 && m == 0 && l != 0) {
    return numberOfTrailingZeros(l);
  }
  if (h == 0 && m != 0 && l == 0) {
    return numberOfTrailingZeros(m) + 22;
  }
  if (h != 0 && m == 0 && l == 0) {
    return numberOfTrailingZeros(h) + 44;
  }
  return -1;
}

function toDoubleHelper(a){
  return a.l + a.m * $intern_15 + a.h * $intern_16;
}

function trialSubtract(a, b){
  var sum0, sum1, sum2;
  sum2 = a.h - b.h;
  if (sum2 < 0) {
    return false;
  }
  sum0 = a.l - b.l;
  sum1 = a.m - b.m + (sum0 >> 22);
  sum2 += sum1 >> 22;
  if (sum2 < 0) {
    return false;
  }
  a.l = sum0 & $intern_12;
  a.m = sum1 & $intern_12;
  a.h = sum2 & $intern_13;
  return true;
}

var remainder;
function add_0(a, b){
  var sum0, sum1, sum2;
  sum0 = a.l + b.l;
  sum1 = a.m + b.m + (sum0 >> 22);
  sum2 = a.h + b.h + (sum1 >> 22);
  return create0(sum0 & $intern_12, sum1 & $intern_12, sum2 & $intern_13);
}

function compare(a, b){
  var a0, a1, a2, b0, b1, b2, signA, signB;
  signA = a.h >> 19;
  signB = b.h >> 19;
  if (signA != signB) {
    return signB - signA;
  }
  a2 = a.h;
  b2 = b.h;
  if (a2 != b2) {
    return a2 - b2;
  }
  a1 = a.m;
  b1 = b.m;
  if (a1 != b1) {
    return a1 - b1;
  }
  a0 = a.l;
  b0 = b.l;
  return a0 - b0;
}

function fromDouble(value_0){
  var a0, a1, a2, negative, result;
  if (isNaN(value_0)) {
    return $clinit_BigLongLib$Const() , ZERO;
  }
  if (value_0 < -9223372036854775808) {
    return $clinit_BigLongLib$Const() , MIN_VALUE;
  }
  if (value_0 >= 9223372036854775807) {
    return $clinit_BigLongLib$Const() , MAX_VALUE;
  }
  negative = false;
  if (value_0 < 0) {
    negative = true;
    value_0 = -value_0;
  }
  a2 = 0;
  if (value_0 >= $intern_16) {
    a2 = round_int(value_0 / $intern_16);
    value_0 -= a2 * $intern_16;
  }
  a1 = 0;
  if (value_0 >= $intern_15) {
    a1 = round_int(value_0 / $intern_15);
    value_0 -= a1 * $intern_15;
  }
  a0 = round_int(value_0);
  result = create0(a0, a1, a2);
  negative && negate(result);
  return result;
}

function mul(a, b){
  var a0, a1, a2, a3, a4, b0, b1, b2, b3, b4, c0, c00, c01, c1, c10, c11, c12, c13, c2, c22, c23, c24, p0, p1, p2, p3, p4;
  a0 = a.l & 8191;
  a1 = a.l >> 13 | (a.m & 15) << 9;
  a2 = a.m >> 4 & 8191;
  a3 = a.m >> 17 | (a.h & 255) << 5;
  a4 = (a.h & 1048320) >> 8;
  b0 = b.l & 8191;
  b1 = b.l >> 13 | (b.m & 15) << 9;
  b2 = b.m >> 4 & 8191;
  b3 = b.m >> 17 | (b.h & 255) << 5;
  b4 = (b.h & 1048320) >> 8;
  p0 = a0 * b0;
  p1 = a1 * b0;
  p2 = a2 * b0;
  p3 = a3 * b0;
  p4 = a4 * b0;
  if (b1 != 0) {
    p1 += a0 * b1;
    p2 += a1 * b1;
    p3 += a2 * b1;
    p4 += a3 * b1;
  }
  if (b2 != 0) {
    p2 += a0 * b2;
    p3 += a1 * b2;
    p4 += a2 * b2;
  }
  if (b3 != 0) {
    p3 += a0 * b3;
    p4 += a1 * b3;
  }
  b4 != 0 && (p4 += a0 * b4);
  c00 = p0 & $intern_12;
  c01 = (p1 & 511) << 13;
  c0 = c00 + c01;
  c10 = p0 >> 22;
  c11 = p1 >> 9;
  c12 = (p2 & 262143) << 4;
  c13 = (p3 & 31) << 17;
  c1 = c10 + c11 + c12 + c13;
  c22 = p2 >> 18;
  c23 = p3 >> 5;
  c24 = (p4 & 4095) << 8;
  c2 = c22 + c23 + c24;
  c1 += c0 >> 22;
  c0 &= $intern_12;
  c2 += c1 >> 22;
  c1 &= $intern_12;
  c2 &= $intern_13;
  return create0(c0, c1, c2);
}

function neg(a){
  var neg0, neg1, neg2;
  neg0 = ~a.l + 1 & $intern_12;
  neg1 = ~a.m + (neg0 == 0?1:0) & $intern_12;
  neg2 = ~a.h + (neg0 == 0 && neg1 == 0?1:0) & $intern_13;
  return create0(neg0, neg1, neg2);
}

function shl(a, n){
  var res0, res1, res2;
  n &= 63;
  if (n < 22) {
    res0 = a.l << n;
    res1 = a.m << n | a.l >> 22 - n;
    res2 = a.h << n | a.m >> 22 - n;
  }
   else if (n < 44) {
    res0 = 0;
    res1 = a.l << n - 22;
    res2 = a.m << n - 22 | a.l >> 44 - n;
  }
   else {
    res0 = 0;
    res1 = 0;
    res2 = a.l << n - 44;
  }
  return create0(res0 & $intern_12, res1 & $intern_12, res2 & $intern_13);
}

function shr(a, n){
  var a2, negative, res0, res1, res2;
  n &= 63;
  a2 = a.h;
  negative = (a2 & $intern_14) != 0;
  negative && (a2 |= -1048576);
  if (n < 22) {
    res2 = a2 >> n;
    res1 = a.m >> n | a2 << 22 - n;
    res0 = a.l >> n | a.m << 22 - n;
  }
   else if (n < 44) {
    res2 = negative?$intern_13:0;
    res1 = a2 >> n - 22;
    res0 = a.m >> n - 22 | a2 << 44 - n;
  }
   else {
    res2 = negative?$intern_13:0;
    res1 = negative?$intern_12:0;
    res0 = a2 >> n - 44;
  }
  return create0(res0 & $intern_12, res1 & $intern_12, res2 & $intern_13);
}

function shru(a, n){
  var a2, res0, res1, res2;
  n &= 63;
  a2 = a.h & $intern_13;
  if (n < 22) {
    res2 = a2 >>> n;
    res1 = a.m >> n | a2 << 22 - n;
    res0 = a.l >> n | a.m << 22 - n;
  }
   else if (n < 44) {
    res2 = 0;
    res1 = a2 >>> n - 22;
    res0 = a.m >> n - 22 | a.h << 44 - n;
  }
   else {
    res2 = 0;
    res1 = 0;
    res0 = a2 >>> n - 44;
  }
  return create0(res0 & $intern_12, res1 & $intern_12, res2 & $intern_13);
}

function sub_0(a, b){
  var sum0, sum1, sum2;
  sum0 = a.l - b.l;
  sum1 = a.m - b.m + (sum0 >> 22);
  sum2 = a.h - b.h + (sum1 >> 22);
  return create0(sum0 & $intern_12, sum1 & $intern_12, sum2 & $intern_13);
}

function toDouble(a){
  if (compare(a, ($clinit_BigLongLib$Const() , ZERO)) < 0) {
    return -toDoubleHelper(neg(a));
  }
  return a.l + a.m * $intern_15 + a.h * $intern_16;
}

function toInt(a){
  return a.l | a.m << 22;
}

function toString_11(a){
  var digits, rem, res, tenPowerLong, zeroesNeeded;
  if (a.l == 0 && a.m == 0 && a.h == 0) {
    return '0';
  }
  if (a.h == $intern_14 && a.m == 0 && a.l == 0) {
    return '-9223372036854775808';
  }
  if (a.h >> 19 != 0) {
    return '-' + toString_11(neg(a));
  }
  rem = a;
  res = '';
  while (!(rem.l == 0 && rem.m == 0 && rem.h == 0)) {
    tenPowerLong = create(1000000000);
    rem = divMod(rem, tenPowerLong, true);
    digits = '' + toInt(remainder);
    if (!(rem.l == 0 && rem.m == 0 && rem.h == 0)) {
      zeroesNeeded = 9 - digits.length;
      for (; zeroesNeeded > 0; zeroesNeeded--) {
        digits = '0' + digits;
      }
    }
    res = digits + res;
  }
  return res;
}

function xor(a, b){
  return create0(a.l ^ b.l, a.m ^ b.m, a.h ^ b.h);
}

function $clinit_BigLongLib$Const(){
  $clinit_BigLongLib$Const = emptyMethod;
  MAX_VALUE = create0($intern_12, $intern_12, 524287);
  MIN_VALUE = create0(0, 0, $intern_14);
  ONE = create(1);
  create(2);
  ZERO = create(0);
}

var MAX_VALUE, MIN_VALUE, ONE, ZERO;
function toJava(e){
  var javaException;
  if (instanceOf(e, 9)) {
    return e;
  }
  javaException = e && e['__java$exception'];
  if (!javaException) {
    javaException = new JavaScriptException(e);
    captureStackTrace(javaException);
  }
  return javaException;
}

function toJs(t){
  return t.backingJsObject;
}

function add_1(a, b){
  var result;
  if (isSmallLong0(a) && isSmallLong0(b)) {
    result = a + b;
    if ($intern_17 < result && result < $intern_16) {
      return result;
    }
  }
  return createLongEmul(add_0(isSmallLong0(a)?toBigLong(a):a, isSmallLong0(b)?toBigLong(b):b));
}

function compare_0(a, b){
  var result;
  if (isSmallLong0(a) && isSmallLong0(b)) {
    result = a - b;
    if (!isNaN(result)) {
      return result;
    }
  }
  return compare(isSmallLong0(a)?toBigLong(a):a, isSmallLong0(b)?toBigLong(b):b);
}

function createLongEmul(big_0){
  var a2;
  a2 = big_0.h;
  if (a2 == 0) {
    return big_0.l + big_0.m * $intern_15;
  }
  if (a2 == $intern_13) {
    return big_0.l + big_0.m * $intern_15 - $intern_16;
  }
  return big_0;
}

function div_0(a, b){
  var result;
  if (isSmallLong0(a) && isSmallLong0(b)) {
    result = a / b;
    if ($intern_17 < result && result < $intern_16) {
      return result < 0?$wnd.Math.ceil(result):$wnd.Math.floor(result);
    }
  }
  return createLongEmul(divMod(isSmallLong0(a)?toBigLong(a):a, isSmallLong0(b)?toBigLong(b):b, false));
}

function eq(a, b){
  return compare_0(a, b) == 0;
}

function fromDouble_0(value_0){
  if ($intern_17 < value_0 && value_0 < $intern_16) {
    return value_0 < 0?$wnd.Math.ceil(value_0):$wnd.Math.floor(value_0);
  }
  return createLongEmul(fromDouble(value_0));
}

function gt(a, b){
  return compare_0(a, b) > 0;
}

function gte_0(a, b){
  return compare_0(a, b) >= 0;
}

function isSmallLong0(value_0){
  return typeof value_0 === 'number';
}

function lt(a, b){
  return compare_0(a, b) < 0;
}

function lte(a, b){
  return compare_0(a, b) <= 0;
}

function mod(a, b){
  var result;
  if (isSmallLong0(a) && isSmallLong0(b)) {
    result = a % b;
    if ($intern_17 < result && result < $intern_16) {
      return result;
    }
  }
  return createLongEmul((divMod(isSmallLong0(a)?toBigLong(a):a, isSmallLong0(b)?toBigLong(b):b, true) , remainder));
}

function mul_0(a, b){
  var result;
  if (isSmallLong0(a) && isSmallLong0(b)) {
    result = a * b;
    if ($intern_17 < result && result < $intern_16) {
      return result;
    }
  }
  return createLongEmul(mul(isSmallLong0(a)?toBigLong(a):a, isSmallLong0(b)?toBigLong(b):b));
}

function neg_0(a){
  var result;
  if (isSmallLong0(a)) {
    result = 0 - a;
    if (!isNaN(result)) {
      return result;
    }
  }
  return createLongEmul(neg(a));
}

function neq(a, b){
  return compare_0(a, b) != 0;
}

function sub_1(a, b){
  var result;
  if (isSmallLong0(a) && isSmallLong0(b)) {
    result = a - b;
    if ($intern_17 < result && result < $intern_16) {
      return result;
    }
  }
  return createLongEmul(sub_0(isSmallLong0(a)?toBigLong(a):a, isSmallLong0(b)?toBigLong(b):b));
}

function toBigLong(longValue){
  var a0, a1, a3, value_0;
  value_0 = longValue;
  a3 = 0;
  if (value_0 < 0) {
    value_0 += $intern_16;
    a3 = $intern_13;
  }
  a1 = round_int(value_0 / $intern_15);
  a0 = round_int(value_0 - a1 * $intern_15);
  return create0(a0, a1, a3);
}

function toDouble_0(a){
  var d;
  if (isSmallLong0(a)) {
    d = a;
    return d == -0.?0:d;
  }
  return toDouble(a);
}

function toInt_0(a){
  if (isSmallLong0(a)) {
    return a | 0;
  }
  return toInt(a);
}

function toString_12(a){
  if (isSmallLong0(a)) {
    return '' + a;
  }
  return toString_11(a);
}

function xor_0(a, b){
  return createLongEmul(xor(isSmallLong0(a)?toBigLong(a):a, isSmallLong0(b)?toBigLong(b):b));
}

function init(){
  $onModuleLoad();
  $clinit_ExporterUtil();
  new ExportAllExporterImpl;
  $wnd.ganttLibReady && $wnd.ganttLibReady();
}

function $exec(this$static, input_0){
  return this$static.exec(input_0);
}

function Point(x_0, y_0){
  this.x_0 = x_0;
  this.y_0 = y_0;
}

defineClass(34, 1, {34:1}, Point);
_.equals_0 = function equals_5(obj){
  var c;
  if (!instanceOf(obj, 34)) {
    return false;
  }
  c = castTo(obj, 34);
  return this.x_0 == c.x_0 && this.y_0 == c.y_0;
}
;
_.hashCode_0 = function hashCode_7(){
  return round_int(this.x_0) ^ round_int(this.y_0);
}
;
_.toString_0 = function toString_14(){
  return 'Point(' + this.x_0 + ',' + this.y_0 + ')';
}
;
_.x_0 = 0;
_.y_0 = 0;
var Lcom_google_gwt_touch_client_Point_2_classLit = createForClass('com.google.gwt.touch.client', 'Point', 34, Ljava_lang_Object_2_classLit);
function $clinit_DOM(){
  $clinit_DOM = emptyMethod;
  $clinit_DOMImplStandard();
}

function dispatchEvent_0(evt, elem){
  $clinit_DOM();
  var eventListener;
  eventListener = getEventListener(elem);
  if (!eventListener) {
    return false;
  }
  dispatchEvent_1(evt, elem, eventListener);
  return true;
}

function dispatchEvent_1(evt, elem, listener){
  $clinit_DOM();
  var prevCurrentEvent;
  prevCurrentEvent = currentEvent;
  currentEvent = evt;
  elem == sCaptureElem && $eventGetTypeInt(evt.type) == 8192 && (sCaptureElem = null);
  listener.onBrowserEvent(evt);
  currentEvent = prevCurrentEvent;
}

function insertChild(parent_0, child, index_0){
  $clinit_DOM();
  $insertChild(parent_0, resolve_0(child), index_0);
}

function resolve_0(maybePotential){
  $clinit_DOM();
  return maybePotential.__gwt_resolve?maybePotential.__gwt_resolve():maybePotential;
}

function sinkBitlessEvent(elem, eventTypeName){
  var dispatchMap, dispatcher;
  $clinit_DOM();
  $maybeInitializeEventSystem();
  dispatchMap = bitlessEventDispatchers;
  dispatcher = dispatchMap[eventTypeName] || dispatchMap['_default_'];
  elem.addEventListener(eventTypeName, dispatcher, false);
}

function sinkEvents(elem, eventBits){
  $clinit_DOM();
  $maybeInitializeEventSystem();
  $sinkEventsImpl(elem, eventBits);
}

var currentEvent = null, sCaptureElem;
function $onModuleLoad(){
  var allowedModes, currentMode, i_0;
  currentMode = $doc.compatMode;
  allowedModes = stampJavaTypeInfo(getClassLiteralForArray(Ljava_lang_String_2_classLit, 1), $intern_0, 2, 6, ['CSS1Compat']);
  for (i_0 = 0; i_0 < allowedModes.length; i_0++) {
    if ($equals_0(allowedModes[i_0], currentMode)) {
      return;
    }
  }
  allowedModes.length == 1 && $equals_0('CSS1Compat', allowedModes[0]) && $equals_0('BackCompat', currentMode)?"GWT no longer supports Quirks Mode (document.compatMode=' BackCompat').<br>Make sure your application's host HTML page has a Standards Mode (document.compatMode=' CSS1Compat') doctype,<br>e.g. by using &lt;!doctype html&gt; at the start of your application's HTML page.<br><br>To continue using this unsupported rendering mode and risk layout problems, suppress this message by adding<br>the following line to your*.gwt.xml module file:<br>&nbsp;&nbsp;&lt;extend-configuration-property name=\"document.compatMode\" value=\"" + currentMode + '"/&gt;':"Your *.gwt.xml module configuration prohibits the use of the current document rendering mode (document.compatMode=' " + currentMode + "').<br>Modify your application's host HTML page doctype, or update your custom " + "'document.compatMode' configuration property settings.";
}

function getTypeInt(typeName){
  return $eventGetTypeInt(($clinit_DOM() , typeName));
}

function releaseCapture(elem){
  $clinit_DOM();
  !!sCaptureElem && elem == sCaptureElem && (sCaptureElem = null);
  $maybeInitializeEventSystem();
  ($clinit_DOMImplStandard() , captureElem) == elem && (captureElem = null);
}

function setCapture(elem){
  $clinit_DOM();
  sCaptureElem = elem;
  $maybeInitializeEventSystem();
  $clinit_DOMImplStandard() , captureElem = elem;
}

function sinkEvents_0(elem){
  $clinit_DOM();
  $maybeInitializeEventSystem();
  $sinkEventsImpl(elem, $intern_18);
}

function addCloseHandler(handler){
  maybeInitializeCloseHandlers();
  return addHandler(TYPE_8?TYPE_8:(TYPE_8 = new GwtEvent$Type), handler);
}

function addHandler(type_0, handler){
  return $addHandler((!handlers_0 && (handlers_0 = new Window$WindowHandlers) , handlers_0), type_0, handler);
}

function maybeInitializeCloseHandlers(){
  if (!closeHandlersInitialized) {
    $initWindowCloseHandler();
    closeHandlersInitialized = true;
  }
}

function onClosing(){
  var event_0;
  if (closeHandlersInitialized) {
    event_0 = new Window$ClosingEvent;
    !!handlers_0 && $fireEvent(handlers_0, event_0);
    return null;
  }
  return null;
}

var closeHandlersInitialized = false, handlers_0;
function $clinit_Window$ClosingEvent(){
  $clinit_Window$ClosingEvent = emptyMethod;
  TYPE_9 = new GwtEvent$Type;
}

function Window$ClosingEvent(){
  $clinit_Window$ClosingEvent();
}

defineClass(180, 337, {}, Window$ClosingEvent);
_.dispatch = function dispatch_9(handler){
  throwClassCastExceptionUnlessNull(handler);
  null.$_nullMethod();
}
;
_.getAssociatedType = function getAssociatedType_10(){
  return TYPE_9;
}
;
var TYPE_9;
var Lcom_google_gwt_user_client_Window$ClosingEvent_2_classLit = createForClass('com.google.gwt.user.client', 'Window/ClosingEvent', 180, Lcom_google_gwt_event_shared_GwtEvent_2_classLit);
function Window$WindowHandlers(){
  HandlerManager.call(this, null);
}

defineClass(114, 78, {25:1}, Window$WindowHandlers);
var Lcom_google_gwt_user_client_Window$WindowHandlers_2_classLit = createForClass('com.google.gwt.user.client', 'Window/WindowHandlers', 114, Lcom_google_gwt_event_shared_HandlerManager_2_classLit);
function $eventGetTypeInt(eventType){
  switch (eventType) {
    case 'blur':
      return 4096;
    case 'change':
      return 1024;
    case 'click':
      return 1;
    case 'dblclick':
      return 2;
    case 'focus':
      return 2048;
    case 'keydown':
      return 128;
    case 'keypress':
      return 256;
    case 'keyup':
      return 512;
    case 'load':
      return 32768;
    case 'losecapture':
      return 8192;
    case 'mousedown':
      return 4;
    case 'mousemove':
      return 64;
    case 'mouseout':
      return 32;
    case 'mouseover':
      return 16;
    case 'mouseup':
      return 8;
    case 'scroll':
      return 16384;
    case 'error':
      return $intern_19;
    case 'DOMMouseScroll':
    case 'mousewheel':
      return 131072;
    case 'contextmenu':
      return $intern_18;
    case 'paste':
      return $intern_14;
    case 'touchstart':
      return 1048576;
    case 'touchmove':
      return 2097152;
    case 'touchend':
      return $intern_15;
    case 'touchcancel':
      return 8388608;
    case 'gesturestart':
      return $intern_20;
    case 'gesturechange':
      return $intern_21;
    case 'gestureend':
      return $intern_22;
    default:return -1;
  }
}

function $maybeInitializeEventSystem(){
  if (!eventSystemIsInitialized) {
    $initEventSystem();
    eventSystemIsInitialized = true;
  }
}

function getEventListener(elem){
  var maybeListener = elem.__listener;
  return !instanceOfJso(maybeListener) && instanceOf(maybeListener, 31)?maybeListener:null;
}

function setEventListener(elem, listener){
  elem.__listener = listener;
}

var eventSystemIsInitialized = false;
function $clinit_DOMImplStandard(){
  $clinit_DOMImplStandard = emptyMethod;
  bitlessEventDispatchers = {_default_:dispatchEvent_3, dragenter:dispatchDragEvent, dragover:dispatchDragEvent};
  captureEventDispatchers = {click:dispatchCapturedMouseEvent, dblclick:dispatchCapturedMouseEvent, mousedown:dispatchCapturedMouseEvent, mouseup:dispatchCapturedMouseEvent, mousemove:dispatchCapturedMouseEvent, mouseover:dispatchCapturedMouseEvent, mouseout:dispatchCapturedMouseEvent, mousewheel:dispatchCapturedMouseEvent, keydown:dispatchCapturedEvent, keyup:dispatchCapturedEvent, keypress:dispatchCapturedEvent, touchstart:dispatchCapturedMouseEvent, touchend:dispatchCapturedMouseEvent, touchmove:dispatchCapturedMouseEvent, touchcancel:dispatchCapturedMouseEvent, gesturestart:dispatchCapturedMouseEvent, gestureend:dispatchCapturedMouseEvent, gesturechange:dispatchCapturedMouseEvent};
}

function $getChildIndex(parent_0, toFind){
  var count = 0, child = parent_0.firstChild;
  while (child) {
    if (child === toFind) {
      return count;
    }
    child.nodeType == 1 && ++count;
    child = child.nextSibling;
  }
  return -1;
}

function $initEventSystem(){
  dispatchEvent_2 = $entry(dispatchEvent_3);
  dispatchUnhandledEvent = $entry(dispatchUnhandledEvent_0);
  var foreach = foreach_0;
  var bitlessEvents = bitlessEventDispatchers;
  foreach(bitlessEvents, function(e, fn){
    bitlessEvents[e] = $entry(fn);
  }
  );
  var captureEvents_0 = captureEventDispatchers;
  foreach(captureEvents_0, function(e, fn){
    captureEvents_0[e] = $entry(fn);
  }
  );
  foreach(captureEvents_0, function(e, fn){
    $wnd.addEventListener(e, fn, true);
  }
  );
}

function $insertChild(parent_0, toAdd, index_0){
  var count = 0, child = parent_0.firstChild, before = null;
  while (child) {
    if (child.nodeType == 1) {
      if (count == index_0) {
        before = child;
        break;
      }
      ++count;
    }
    child = child.nextSibling;
  }
  parent_0.insertBefore(toAdd, before);
}

function $sinkEventsImpl(elem, bits){
  var chMask = (elem.__eventBits || 0) ^ bits;
  elem.__eventBits = bits;
  if (!chMask)
    return;
  chMask & 1 && (elem.onclick = bits & 1?dispatchEvent_2:null);
  chMask & 2 && (elem.ondblclick = bits & 2?dispatchEvent_2:null);
  chMask & 4 && (elem.onmousedown = bits & 4?dispatchEvent_2:null);
  chMask & 8 && (elem.onmouseup = bits & 8?dispatchEvent_2:null);
  chMask & 16 && (elem.onmouseover = bits & 16?dispatchEvent_2:null);
  chMask & 32 && (elem.onmouseout = bits & 32?dispatchEvent_2:null);
  chMask & 64 && (elem.onmousemove = bits & 64?dispatchEvent_2:null);
  chMask & 128 && (elem.onkeydown = bits & 128?dispatchEvent_2:null);
  chMask & 256 && (elem.onkeypress = bits & 256?dispatchEvent_2:null);
  chMask & 512 && (elem.onkeyup = bits & 512?dispatchEvent_2:null);
  chMask & 1024 && (elem.onchange = bits & 1024?dispatchEvent_2:null);
  chMask & 2048 && (elem.onfocus = bits & 2048?dispatchEvent_2:null);
  chMask & 4096 && (elem.onblur = bits & 4096?dispatchEvent_2:null);
  chMask & 8192 && (elem.onlosecapture = bits & 8192?dispatchEvent_2:null);
  chMask & 16384 && (elem.onscroll = bits & 16384?dispatchEvent_2:null);
  chMask & 32768 && (elem.onload = bits & 32768?dispatchUnhandledEvent:null);
  chMask & $intern_19 && (elem.onerror = bits & $intern_19?dispatchEvent_2:null);
  chMask & 131072 && (elem.onmousewheel = bits & 131072?dispatchEvent_2:null);
  chMask & $intern_18 && (elem.oncontextmenu = bits & $intern_18?dispatchEvent_2:null);
  chMask & $intern_14 && (elem.onpaste = bits & $intern_14?dispatchEvent_2:null);
  chMask & 1048576 && (elem.ontouchstart = bits & 1048576?dispatchEvent_2:null);
  chMask & 2097152 && (elem.ontouchmove = bits & 2097152?dispatchEvent_2:null);
  chMask & $intern_15 && (elem.ontouchend = bits & $intern_15?dispatchEvent_2:null);
  chMask & 8388608 && (elem.ontouchcancel = bits & 8388608?dispatchEvent_2:null);
  chMask & $intern_20 && (elem.ongesturestart = bits & $intern_20?dispatchEvent_2:null);
  chMask & $intern_21 && (elem.ongesturechange = bits & $intern_21?dispatchEvent_2:null);
  chMask & $intern_22 && (elem.ongestureend = bits & $intern_22?dispatchEvent_2:null);
}

function dispatchCapturedEvent(evt){
  $clinit_DOM();
}

function dispatchCapturedMouseEvent(evt){
  $clinit_DOM();
  if (!captureElem) {
    return;
  }
  dispatchEvent_0(evt, captureElem) && (evt.stopPropagation() , undefined);
}

function dispatchDragEvent(evt){
  evt.preventDefault();
  dispatchEvent_3(evt);
}

function dispatchEvent_3(evt){
  var element;
  element = getFirstAncestorWithListener(evt);
  if (!element) {
    return;
  }
  dispatchEvent_1(evt, element.nodeType != 1?null:element, getEventListener(element));
}

function dispatchUnhandledEvent_0(evt){
  var element;
  element = evt.currentTarget || $wnd;
  element['__gwtLastUnhandledEvent'] = evt.type;
  dispatchEvent_3(evt);
}

function getFirstAncestorWithListener(evt){
  var curElem;
  curElem = evt.currentTarget || $wnd;
  while (!!curElem && !getEventListener(curElem)) {
    curElem = curElem.parentNode;
  }
  return curElem;
}

var bitlessEventDispatchers, captureElem, captureEventDispatchers, dispatchEvent_2, dispatchUnhandledEvent;
function foreach_0(map_0, fn){
  for (var e in map_0) {
    map_0.hasOwnProperty(e) && fn(e, map_0[e]);
  }
}

function $initWindowCloseHandler(){
  var oldOnBeforeUnload = $wnd.onbeforeunload;
  var oldOnUnload = $wnd.onunload;
  $wnd.onbeforeunload = function(evt){
    var ret, oldRet;
    try {
      ret = $entry(onClosing)();
    }
     finally {
      oldRet = oldOnBeforeUnload && oldOnBeforeUnload(evt);
    }
    if (ret != null) {
      return ret;
    }
    if (oldRet != null) {
      return oldRet;
    }
  }
  ;
  $wnd.onunload = $entry(function(evt){
    try {
      closeHandlersInitialized && fire_0((!handlers_0 && (handlers_0 = new Window$WindowHandlers) , handlers_0));
    }
     finally {
      oldOnUnload && oldOnUnload(evt);
      $wnd.onresize = null;
      $wnd.onscroll = null;
      $wnd.onbeforeunload = null;
      $wnd.onunload = null;
    }
  }
  );
}

function $addStyleName(this$static, style){
  setStyleName(($clinit_DOM() , this$static.element), style);
}

function $getElement(this$static){
  return $clinit_DOM() , this$static.element;
}

function $setElement(this$static, elem){
  $setElement_0(this$static, ($clinit_DOM() , elem));
}

function $setElement_0(this$static, elem){
  this$static.element = elem;
}

function $sinkBitlessEvent(this$static, eventTypeName){
  sinkBitlessEvent(($clinit_DOM() , this$static.element), eventTypeName);
}

function getStylePrimaryName(elem){
  var fullClassName, spaceIdx;
  fullClassName = elem.className || '';
  spaceIdx = $indexOf_0(fullClassName, fromCodePoint(32));
  if (spaceIdx >= 0) {
    return fullClassName.substr(0, spaceIdx);
  }
  return fullClassName;
}

function setStyleName(elem, style){
  if (!elem) {
    throw toJs(new RuntimeException_0('Null widget handle. If you are creating a composite, ensure that initWidget() has been called.'));
  }
  style = $trim(style);
  if (style.length == 0) {
    throw toJs(new IllegalArgumentException('Style names cannot be empty'));
  }
  $addClassName(elem, style);
}

function setStylePrimaryName(elem, style){
  if (!elem) {
    throw toJs(new RuntimeException_0('Null widget handle. If you are creating a composite, ensure that initWidget() has been called.'));
  }
  style = $trim(style);
  if (style.length == 0) {
    throw toJs(new IllegalArgumentException('Style names cannot be empty'));
  }
  updatePrimaryAndDependentStyleNames(elem, style);
}

function updatePrimaryAndDependentStyleNames(elem, newPrimaryStyle){
  var classes = (elem.className || '').split(/\s+/);
  if (!classes) {
    return;
  }
  var oldPrimaryStyle = classes[0];
  var oldPrimaryStyleLen = oldPrimaryStyle.length;
  classes[0] = newPrimaryStyle;
  for (var i_0 = 1, n = classes.length; i_0 < n; i_0++) {
    var name_0 = classes[i_0];
    name_0.length > oldPrimaryStyleLen && name_0.charAt(oldPrimaryStyleLen) == '-' && name_0.indexOf(oldPrimaryStyle) == 0 && (classes[i_0] = newPrimaryStyle + name_0.substring(oldPrimaryStyleLen));
  }
  elem.className = classes.join(' ');
}

defineClass(22, 1, {30:1, 22:1});
_.toString_0 = function toString_15(){
  if (!this.element) {
    return '(null handle)';
  }
  return ($clinit_DOM() , this.element).outerHTML;
}
;
var Lcom_google_gwt_user_client_ui_UIObject_2_classLit = createForClass('com.google.gwt.user.client.ui', 'UIObject', 22, Ljava_lang_Object_2_classLit);
function $addDomHandler(this$static, handler, type_0){
  var typeInt;
  typeInt = getTypeInt(type_0.name_0);
  typeInt == -1?$sinkBitlessEvent(this$static, type_0.name_0):this$static.eventsToSink == -1?sinkEvents(($clinit_DOM() , this$static.element), typeInt | (this$static.element.__eventBits || 0)):(this$static.eventsToSink |= typeInt);
  return $addHandler(!this$static.handlerManager?(this$static.handlerManager = new HandlerManager(this$static)):this$static.handlerManager, type_0, handler);
}

function $addHandler_0(this$static, handler, type_0){
  return $addHandler(!this$static.handlerManager?(this$static.handlerManager = new HandlerManager(this$static)):this$static.handlerManager, type_0, handler);
}

function $fireEvent_0(this$static, event_0){
  !!this$static.handlerManager && $fireEvent(this$static.handlerManager, event_0);
}

function $onAttach(this$static){
  var bitsToAdd;
  if (this$static.attached) {
    throw toJs(new IllegalStateException_0("Should only call onAttach when the widget is detached from the browser's document"));
  }
  this$static.attached = true;
  $clinit_DOM();
  setEventListener(this$static.element, this$static);
  bitsToAdd = this$static.eventsToSink;
  this$static.eventsToSink = -1;
  bitsToAdd > 0 && (this$static.eventsToSink == -1?sinkEvents(this$static.element, bitsToAdd | (this$static.element.__eventBits || 0)):(this$static.eventsToSink |= bitsToAdd));
  this$static.doAttachChildren();
}

function $onDetach(this$static){
  if (!this$static.attached) {
    throw toJs(new IllegalStateException_0("Should only call onDetach when the widget is attached to the browser's document"));
  }
  try {
    this$static.onUnload();
  }
   finally {
    try {
      this$static.doDetachChildren();
    }
     finally {
      $clinit_DOM();
      this$static.element.__listener = null;
      this$static.attached = false;
    }
  }
}

function $removeFromParent_0(this$static){
  if (!this$static.parent_0) {
    $clinit_RootPanel();
    $contains_1(widgetsToDetach, this$static) && detachNow(this$static);
  }
   else if (this$static.parent_0) {
    this$static.parent_0.remove_0(this$static);
  }
   else if (this$static.parent_0) {
    throw toJs(new IllegalStateException_0("This widget's parent does not implement HasWidgets"));
  }
}

function $setParent(this$static, parent_0){
  var oldParent;
  oldParent = this$static.parent_0;
  if (!parent_0) {
    try {
      !!oldParent && oldParent.attached && this$static.onDetach();
    }
     finally {
      this$static.parent_0 = null;
    }
  }
   else {
    if (oldParent) {
      throw toJs(new IllegalStateException_0('Cannot set a new parent without first clearing the old parent'));
    }
    this$static.parent_0 = parent_0;
    parent_0.attached && this$static.onAttach();
  }
}

defineClass(20, 22, $intern_23);
_.doAttachChildren = function doAttachChildren(){
}
;
_.doDetachChildren = function doDetachChildren(){
}
;
_.onAttach = function onAttach(){
  $onAttach(this);
}
;
_.onBrowserEvent = function onBrowserEvent(event_0){
  var related;
  switch ($clinit_DOM() , $eventGetTypeInt(event_0.type)) {
    case 16:
    case 32:
      related = event_0.relatedTarget;
      if (!!related && $isOrHasChild(this.element, related)) {
        return;
      }

  }
  fireNativeEvent(event_0, this, this.element);
}
;
_.onDetach = function onDetach(){
  $onDetach(this);
}
;
_.onUnload = function onUnload(){
}
;
_.attached = false;
_.eventsToSink = 0;
var Lcom_google_gwt_user_client_ui_Widget_2_classLit = createForClass('com.google.gwt.user.client.ui', 'Widget', 20, Lcom_google_gwt_user_client_ui_UIObject_2_classLit);
defineClass(334, 20, $intern_23);
_.doAttachChildren = function doAttachChildren_0(){
  tryCommand(this, ($clinit_AttachDetachException() , attachCommand));
}
;
_.doDetachChildren = function doDetachChildren_0(){
  tryCommand(this, ($clinit_AttachDetachException() , detachCommand));
}
;
var Lcom_google_gwt_user_client_ui_Panel_2_classLit = createForClass('com.google.gwt.user.client.ui', 'Panel', 334, Lcom_google_gwt_user_client_ui_Widget_2_classLit);
function $add(this$static, child, container){
  $add_0(this$static, child, ($clinit_DOM() , container));
}

function $add_0(this$static, child, container){
  $removeFromParent_0(child);
  $add_1(this$static.children_0, child);
  $clinit_DOM();
  $appendChild(container, resolve_0(child.element));
  $setParent(child, this$static);
}

function $adjustIndex(this$static, child, beforeIndex){
  var idx;
  $checkIndexBoundsForInsertion(this$static, beforeIndex);
  if (child.parent_0 == this$static) {
    idx = $indexOf(this$static.children_0, child);
    idx < beforeIndex && --beforeIndex;
  }
  return beforeIndex;
}

function $checkIndexBoundsForInsertion(this$static, index_0){
  if (index_0 < 0 || index_0 > this$static.children_0.size_0) {
    throw toJs(new IndexOutOfBoundsException);
  }
}

function $getWidget(this$static, index_0){
  return $get_1(this$static.children_0, index_0);
}

function $remove(this$static, w){
  var elem;
  if (w.parent_0 != this$static) {
    return false;
  }
  try {
    $setParent(w, null);
  }
   finally {
    elem = ($clinit_DOM() , w.element);
    $removeChild((null , $getParentElement(elem)), elem);
    $remove_1(this$static.children_0, w);
  }
  return true;
}

function ComplexPanel(){
  this.children_0 = new WidgetCollection(this);
}

defineClass(85, 334, $intern_23);
_.iterator = function iterator_0(){
  return new WidgetCollection$WidgetIterator(this.children_0);
}
;
_.remove_0 = function remove_0(w){
  return $remove(this, w);
}
;
var Lcom_google_gwt_user_client_ui_ComplexPanel_2_classLit = createForClass('com.google.gwt.user.client.ui', 'ComplexPanel', 85, Lcom_google_gwt_user_client_ui_Panel_2_classLit);
function changeToStaticPositioning(elem){
  elem.style['left'] = '';
  elem.style['top'] = '';
  elem.style['position'] = '';
}

defineClass(256, 85, $intern_23);
_.remove_0 = function remove_1(w){
  var removed;
  removed = $remove(this, w);
  removed && changeToStaticPositioning(($clinit_DOM() , w.element));
  return removed;
}
;
var Lcom_google_gwt_user_client_ui_AbsolutePanel_2_classLit = createForClass('com.google.gwt.user.client.ui', 'AbsolutePanel', 256, Lcom_google_gwt_user_client_ui_ComplexPanel_2_classLit);
function maybeRecalculateNativeScrollbarSize(){
  var content_0, parent_0, scrollable;
  if (nativeHeight > -1) {
    return;
  }
  scrollable = $doc.createElement('div');
  scrollable.style['position'] = ($clinit_Style$Position() , 'absolute');
  scrollable.style['top'] = ($clinit_Style$Unit() , '-1000.0px');
  scrollable.style['left'] = '-1000.0px';
  scrollable.style['height'] = '100.0px';
  scrollable.style['width'] = '100.0px';
  scrollable.style['overflow'] = ($clinit_Style$Overflow() , 'scroll');
  scrollable.style['direction'] = 'rtl';
  $appendChild($doc.body, scrollable);
  content_0 = $doc.createElement('div');
  content_0.textContent = 'content';
  scrollable.appendChild(content_0);
  nativeHeight = ((scrollable.offsetHeight || 0) | 0) - (scrollable.clientHeight | 0);
  nativeWidth = ((scrollable.offsetWidth || 0) | 0) - (scrollable.clientWidth | 0);
  $getAbsoluteLeft(content_0) > $getAbsoluteLeft(scrollable);
  parent_0 = $getParentElement(scrollable);
  !!parent_0 && parent_0.removeChild(scrollable);
}

var nativeHeight = -1, nativeWidth = -1;
function $clinit_AttachDetachException(){
  $clinit_AttachDetachException = emptyMethod;
  attachCommand = new AttachDetachException$1;
  detachCommand = new AttachDetachException$2;
}

function AttachDetachException(causes){
  UmbrellaException_0.call(this, causes);
}

function tryCommand(hasWidgets, c){
  $clinit_AttachDetachException();
  var caught, e, w, w$iterator;
  caught = null;
  for (w$iterator = hasWidgets.iterator(); w$iterator.hasNext_0();) {
    w = castTo(w$iterator.next_1(), 20);
    try {
      c.execute_2(w);
    }
     catch ($e0) {
      $e0 = toJava($e0);
      if (instanceOf($e0, 9)) {
        e = $e0;
        !caught && (caught = new HashSet);
        $put_0(caught.map_0, e, caught);
      }
       else 
        throw toJs($e0);
    }
  }
  if (caught) {
    throw toJs(new AttachDetachException(caught));
  }
}

defineClass(155, 109, $intern_8, AttachDetachException);
var attachCommand, detachCommand;
var Lcom_google_gwt_user_client_ui_AttachDetachException_2_classLit = createForClass('com.google.gwt.user.client.ui', 'AttachDetachException', 155, Lcom_google_gwt_event_shared_UmbrellaException_2_classLit);
function AttachDetachException$1(){
}

defineClass(156, 1, {}, AttachDetachException$1);
_.execute_2 = function execute_2(w){
  w.onAttach();
}
;
var Lcom_google_gwt_user_client_ui_AttachDetachException$1_2_classLit = createForClass('com.google.gwt.user.client.ui', 'AttachDetachException/1', 156, Ljava_lang_Object_2_classLit);
function AttachDetachException$2(){
}

defineClass(157, 1, {}, AttachDetachException$2);
_.execute_2 = function execute_3(w){
  w.onDetach();
}
;
var Lcom_google_gwt_user_client_ui_AttachDetachException$2_2_classLit = createForClass('com.google.gwt.user.client.ui', 'AttachDetachException/2', 157, Ljava_lang_Object_2_classLit);
function $clinit_RootPanel(){
  $clinit_RootPanel = emptyMethod;
  maybeDetachCommand = new RootPanel$1;
  rootPanels = new HashMap;
  widgetsToDetach = new HashSet;
}

function RootPanel(elem){
  ComplexPanel.call(this);
  $setElement_0(this, ($clinit_DOM() , elem));
  $onAttach(this);
}

function detachNow(widget){
  $clinit_RootPanel();
  try {
    widget.onDetach();
  }
   finally {
    $remove_7(widgetsToDetach, widget);
  }
}

function detachWidgets(){
  $clinit_RootPanel();
  try {
    tryCommand(widgetsToDetach, maybeDetachCommand);
  }
   finally {
    $reset(widgetsToDetach.map_0);
    $reset(rootPanels);
  }
}

function get_0(){
  $clinit_RootPanel();
  var rp;
  rp = castTo($get_2(rootPanels, null), 79);
  if (rp) {
    return rp;
  }
  if ($size(rootPanels) == 0) {
    addCloseHandler(new RootPanel$2);
    $clinit_LocaleInfo();
  }
  rp = new RootPanel$DefaultRootPanel;
  $put_0(rootPanels, null, rp);
  $add_3(widgetsToDetach, rp);
  return rp;
}

defineClass(79, 256, $intern_24);
var maybeDetachCommand, rootPanels, widgetsToDetach;
var Lcom_google_gwt_user_client_ui_RootPanel_2_classLit = createForClass('com.google.gwt.user.client.ui', 'RootPanel', 79, Lcom_google_gwt_user_client_ui_AbsolutePanel_2_classLit);
function RootPanel$1(){
}

defineClass(258, 1, {}, RootPanel$1);
_.execute_2 = function execute_4(w){
  w.attached && w.onDetach();
}
;
var Lcom_google_gwt_user_client_ui_RootPanel$1_2_classLit = createForClass('com.google.gwt.user.client.ui', 'RootPanel/1', 258, Ljava_lang_Object_2_classLit);
function RootPanel$2(){
}

defineClass(259, 1, {355:1, 15:1}, RootPanel$2);
var Lcom_google_gwt_user_client_ui_RootPanel$2_2_classLit = createForClass('com.google.gwt.user.client.ui', 'RootPanel/2', 259, Ljava_lang_Object_2_classLit);
function RootPanel$DefaultRootPanel(){
  RootPanel.call(this, $doc.body);
}

defineClass(257, 79, $intern_24, RootPanel$DefaultRootPanel);
var Lcom_google_gwt_user_client_ui_RootPanel$DefaultRootPanel_2_classLit = createForClass('com.google.gwt.user.client.ui', 'RootPanel/DefaultRootPanel', 257, Lcom_google_gwt_user_client_ui_RootPanel_2_classLit);
function $add_1(this$static, w){
  $insert(this$static, w, this$static.size_0);
}

function $get_1(this$static, index_0){
  if (index_0 < 0 || index_0 >= this$static.size_0) {
    throw toJs(new IndexOutOfBoundsException);
  }
  return this$static.array[index_0];
}

function $indexOf(this$static, w){
  var i_0;
  for (i_0 = 0; i_0 < this$static.size_0; ++i_0) {
    if (this$static.array[i_0] == w) {
      return i_0;
    }
  }
  return -1;
}

function $insert(this$static, w, beforeIndex){
  var i_0, i0, newArray;
  if (beforeIndex < 0 || beforeIndex > this$static.size_0) {
    throw toJs(new IndexOutOfBoundsException);
  }
  if (this$static.size_0 == this$static.array.length) {
    newArray = initUnidimensionalArray(Lcom_google_gwt_user_client_ui_Widget_2_classLit, $intern_0, 20, this$static.array.length * 2, 0, 1);
    for (i0 = 0; i0 < this$static.array.length; ++i0) {
      newArray[i0] = this$static.array[i0];
    }
    this$static.array = newArray;
  }
  ++this$static.size_0;
  for (i_0 = this$static.size_0 - 1; i_0 > beforeIndex; --i_0) {
    this$static.array[i_0] = this$static.array[i_0 - 1];
  }
  this$static.array[beforeIndex] = w;
}

function $remove_0(this$static, index_0){
  var i_0;
  if (index_0 < 0 || index_0 >= this$static.size_0) {
    throw toJs(new IndexOutOfBoundsException);
  }
  --this$static.size_0;
  for (i_0 = index_0; i_0 < this$static.size_0; ++i_0) {
    this$static.array[i_0] = this$static.array[i_0 + 1];
  }
  this$static.array[this$static.size_0] = null;
}

function $remove_1(this$static, w){
  var index_0;
  index_0 = $indexOf(this$static, w);
  if (index_0 == -1) {
    throw toJs(new NoSuchElementException);
  }
  $remove_0(this$static, index_0);
}

function WidgetCollection(parent_0){
  this.parent_0 = parent_0;
  this.array = initUnidimensionalArray(Lcom_google_gwt_user_client_ui_Widget_2_classLit, $intern_0, 20, 4, 0, 1);
}

defineClass(115, 1, {}, WidgetCollection);
_.iterator = function iterator_1(){
  return new WidgetCollection$WidgetIterator(this);
}
;
_.size_0 = 0;
var Lcom_google_gwt_user_client_ui_WidgetCollection_2_classLit = createForClass('com.google.gwt.user.client.ui', 'WidgetCollection', 115, Ljava_lang_Object_2_classLit);
function $next(this$static){
  if (this$static.index_0 >= this$static.this$01.size_0) {
    throw toJs(new NoSuchElementException);
  }
  this$static.currentWidget = this$static.this$01.array[this$static.index_0];
  ++this$static.index_0;
  return this$static.currentWidget;
}

function WidgetCollection$WidgetIterator(this$0){
  this.this$01 = this$0;
}

defineClass(91, 1, {}, WidgetCollection$WidgetIterator);
_.next_1 = function next_0(){
  return $next(this);
}
;
_.hasNext_0 = function hasNext(){
  return this.index_0 < this.this$01.size_0;
}
;
_.remove_1 = function remove_2(){
  if (!this.currentWidget) {
    throw toJs(new IllegalStateException);
  }
  this.this$01.parent_0.remove_0(this.currentWidget);
  --this.index_0;
  this.currentWidget = null;
}
;
_.index_0 = 0;
var Lcom_google_gwt_user_client_ui_WidgetCollection$WidgetIterator_2_classLit = createForClass('com.google.gwt.user.client.ui', 'WidgetCollection/WidgetIterator', 91, Ljava_lang_Object_2_classLit);
function $removeHandler(this$static){
  $doRemove(this$static.this$01, this$static.val$type2, this$static.val$source3, this$static.val$handler4);
}

function SimpleEventBus$1(this$0, val$type, val$handler){
  this.this$01 = this$0;
  this.val$type2 = val$type;
  this.val$source3 = null;
  this.val$handler4 = val$handler;
}

defineClass(184, 1, {}, SimpleEventBus$1);
var Lcom_google_web_bindery_event_shared_SimpleEventBus$1_2_classLit = createForClass('com.google.web.bindery.event.shared', 'SimpleEventBus/1', 184, Ljava_lang_Object_2_classLit);
function SimpleEventBus$2(this$0, val$type, val$handler){
  this.this$01 = this$0;
  this.val$type2 = val$type;
  this.val$source3 = null;
  this.val$handler4 = val$handler;
}

defineClass(185, 1, {312:1}, SimpleEventBus$2);
_.execute_1 = function execute_5(){
  $doAddNow(this.this$01, this.val$type2, this.val$source3, this.val$handler4);
}
;
var Lcom_google_web_bindery_event_shared_SimpleEventBus$2_2_classLit = createForClass('com.google.web.bindery.event.shared', 'SimpleEventBus/2', 185, Ljava_lang_Object_2_classLit);
function SimpleEventBus$3(this$0, val$type, val$source, val$handler){
  this.this$01 = this$0;
  this.val$type2 = val$type;
  this.val$source3 = val$source;
  this.val$handler4 = val$handler;
}

defineClass(186, 1, {312:1}, SimpleEventBus$3);
_.execute_1 = function execute_6(){
  $doRemoveNow(this.this$01, this.val$type2, this.val$source3, this.val$handler4);
}
;
var Lcom_google_web_bindery_event_shared_SimpleEventBus$3_2_classLit = createForClass('com.google.web.bindery.event.shared', 'SimpleEventBus/3', 186, Ljava_lang_Object_2_classLit);
function $clinit_BrowserInfo(){
  $clinit_BrowserInfo = emptyMethod;
  var browserClassnames;
  browserClassnames = $getCSSClass((!instance_3 && (instance_3 = new BrowserInfo) , instance_3));
  $addStyleName(($clinit_RootPanel() , get_0()), browserClassnames);
}

function $detectTouchDevice(){
  try {
    document.createEvent('TouchEvent');
    return true;
  }
   catch (e) {
    return false;
  }
}

function $getCSSClass(this$static){
  var browserEngineClass, browserIdentifier, majorVersionClass, minorVersionClass, osClass;
  if (cssClass == null) {
    browserIdentifier = '';
    majorVersionClass = '';
    minorVersionClass = '';
    browserEngineClass = '';
    if (this$static.browserDetails.isFirefox) {
      browserIdentifier = 'ff';
      majorVersionClass = 'ff' + this$static.browserDetails.browserMajorVersion;
      minorVersionClass = majorVersionClass + this$static.browserDetails.browserMinorVersion;
      browserEngineClass = 'gecko';
    }
     else if (this$static.browserDetails.isChrome) {
      browserIdentifier = 'sa';
      majorVersionClass = 'ch';
      browserEngineClass = 'webkit';
    }
     else if (this$static.browserDetails.isSafari) {
      browserIdentifier = 'sa';
      majorVersionClass = 'sa' + this$static.browserDetails.browserMajorVersion;
      minorVersionClass = majorVersionClass + this$static.browserDetails.browserMinorVersion;
      browserEngineClass = 'webkit';
    }
     else if (this$static.browserDetails.isPhantomJS) {
      browserIdentifier = 'sa';
      majorVersionClass = 'sa' + this$static.browserDetails.browserMajorVersion;
      minorVersionClass = majorVersionClass + this$static.browserDetails.browserMinorVersion;
      browserEngineClass = 'webkit';
    }
     else if (this$static.browserDetails.isIE) {
      browserIdentifier = 'ie';
      majorVersionClass = 'ie' + this$static.browserDetails.browserMajorVersion;
      minorVersionClass = majorVersionClass + this$static.browserDetails.browserMinorVersion;
      browserEngineClass = 'trident';
    }
     else if (this$static.browserDetails.isEdge) {
      browserIdentifier = 'edge';
      majorVersionClass = 'edge' + this$static.browserDetails.browserMajorVersion;
      minorVersionClass = majorVersionClass + this$static.browserDetails.browserMinorVersion;
      browserEngineClass = '';
    }
     else if (this$static.browserDetails.isOpera) {
      browserIdentifier = 'op';
      majorVersionClass = 'op' + this$static.browserDetails.browserMajorVersion;
      minorVersionClass = majorVersionClass + this$static.browserDetails.browserMinorVersion;
      browserEngineClass = 'presto';
    }
    cssClass = 'v-' + browserIdentifier;
    $equals_0('', majorVersionClass) || (cssClass = cssClass + ' ' + 'v-' + majorVersionClass);
    $equals_0('', minorVersionClass) || (cssClass = cssClass + ' ' + 'v-' + minorVersionClass);
    $equals_0('', browserEngineClass) || (cssClass = cssClass + ' ' + 'v-' + browserEngineClass);
    osClass = $getOperatingSystemClass(this$static);
    osClass != null && (cssClass = cssClass + ' ' + osClass);
    this$static.touchDevice && (cssClass = cssClass + ' ' + 'v-' + 'touch');
  }
  return cssClass;
}

function $getIEDocumentMode(){
  var mode = $wnd.document.documentMode;
  if (!mode)
    return -1;
  return mode;
}

function $getOperatingSystemClass(this$static){
  if (this$static.browserDetails.os == 5) {
    return 'v-android';
  }
   else if (this$static.browserDetails.os == 4) {
    return 'v-ios v-ios' + this$static.browserDetails.osMajorVersion;
  }
   else if (this$static.browserDetails.os == 1) {
    return 'v-win';
  }
   else if (this$static.browserDetails.os == 3) {
    return 'v-lin';
  }
   else if (this$static.browserDetails.os == 2) {
    return 'v-mac';
  }
  return null;
}

function BrowserInfo(){
  $clinit_BrowserInfo();
  var documentMode;
  this.browserDetails = new VBrowserDetails($wnd.navigator.userAgent);
  if (this.browserDetails.isIE) {
    documentMode = $getIEDocumentMode();
    documentMode != -1 && $setIEMode(this.browserDetails, documentMode);
  }
  this.browserDetails.isChrome?(this.touchDevice = 'ontouchstart' in window):this.browserDetails.isIE?(this.touchDevice = !!navigator.msMaxTouchPoints):(this.touchDevice = !this.browserDetails.isPhantomJS && $detectTouchDevice());
}

defineClass(120, 1, {}, BrowserInfo);
_.touchDevice = false;
var cssClass = null, instance_3;
var Lcom_vaadin_client_BrowserInfo_2_classLit = createForClass('com.vaadin.client', 'BrowserInfo', 120, Ljava_lang_Object_2_classLit);
function buildMouseEventDetails(evt, relativeToElement){
  var mouseEventDetails;
  mouseEventDetails = new MouseEventDetails;
  $setType(mouseEventDetails, getTypeInt(evt.type));
  $setClientX(mouseEventDetails, ($clinit_WidgetUtil() , $clinit_WidgetUtil() , evt.type.indexOf('touch') != -1?(evt.changedTouches[0].clientX || 0) | 0:(evt.clientX || 0) | 0));
  $setClientY(mouseEventDetails, (null , evt.type.indexOf('touch') != -1?(evt.changedTouches[0].clientY || 0) | 0:(evt.clientY || 0) | 0));
  $eventGetButton(evt) == 1?$setButton(mouseEventDetails, ($clinit_MouseEventDetails$MouseButton() , LEFT)):$eventGetButton(evt) == 2?$setButton(mouseEventDetails, ($clinit_MouseEventDetails$MouseButton() , RIGHT)):$eventGetButton(evt) == 4?$setButton(mouseEventDetails, ($clinit_MouseEventDetails$MouseButton() , MIDDLE)):$setButton(mouseEventDetails, ($clinit_MouseEventDetails$MouseButton() , LEFT));
  $setAltKey(mouseEventDetails, !!evt.altKey);
  $setCtrlKey(mouseEventDetails, !!evt.ctrlKey);
  $setMetaKey(mouseEventDetails, !!evt.metaKey);
  $setShiftKey(mouseEventDetails, !!evt.shiftKey);
  if (relativeToElement) {
    $setRelativeX(mouseEventDetails, getRelativeX(mouseEventDetails.clientX_0, relativeToElement));
    $setRelativeY(mouseEventDetails, getRelativeY(mouseEventDetails.clientY_0, relativeToElement));
  }
  return mouseEventDetails;
}

function getRelativeX(clientX, target){
  return clientX - $getAbsoluteLeft(target) + $getScrollLeft_0(target) + $getScrollLeft_1(target.ownerDocument);
}

function getRelativeY(clientY, target){
  return clientY - $getAbsoluteTop(target) + ((target.scrollTop || 0) | 0) + $getScrollTop_0(target.ownerDocument);
}

function $clinit_WidgetUtil(){
  $clinit_WidgetUtil = emptyMethod;
  $clinit_DOM();
  $doc.createElement('div');
}

defineClass(343, 342, {});
var Lcom_vaadin_client_event_PointerEvent_2_classLit = createForClass('com.vaadin.client.event', 'PointerEvent', 343, Lcom_google_gwt_event_dom_client_MouseEvent_2_classLit);
function $clinit_PointerCancelEvent(){
  $clinit_PointerCancelEvent = emptyMethod;
  TYPE_10 = new DomEvent$Type($getNativeEventName(($clinit_PointerEvent$EventType() , PointerCancel)), new PointerCancelEvent);
}

function PointerCancelEvent(){
}

defineClass(276, 343, {}, PointerCancelEvent);
_.dispatch = function dispatch_10(handler){
  castTo(handler, 308).onPointerCancel(this);
}
;
_.getAssociatedType_0 = function getAssociatedType_11(){
  return TYPE_10;
}
;
var TYPE_10;
var Lcom_vaadin_client_event_PointerCancelEvent_2_classLit = createForClass('com.vaadin.client.event', 'PointerCancelEvent', 276, Lcom_vaadin_client_event_PointerEvent_2_classLit);
function $clinit_PointerDownEvent(){
  $clinit_PointerDownEvent = emptyMethod;
  TYPE_11 = new DomEvent$Type($getNativeEventName(($clinit_PointerEvent$EventType() , PointerDown)), new PointerDownEvent);
}

function PointerDownEvent(){
}

defineClass(273, 343, {}, PointerDownEvent);
_.dispatch = function dispatch_11(handler){
  castTo(handler, 305).onPointerDown(this);
}
;
_.getAssociatedType_0 = function getAssociatedType_12(){
  return TYPE_11;
}
;
var TYPE_11;
var Lcom_vaadin_client_event_PointerDownEvent_2_classLit = createForClass('com.vaadin.client.event', 'PointerDownEvent', 273, Lcom_vaadin_client_event_PointerEvent_2_classLit);
function $clinit_PointerEvent$EventType(){
  $clinit_PointerEvent$EventType = emptyMethod;
  PointerDown = new PointerEvent$EventType('PointerDown', 0);
  PointerMove = new PointerEvent$EventType('PointerMove', 1);
  PointerOut = new PointerEvent$EventType('PointerOut', 2);
  PointerOver = new PointerEvent$EventType('PointerOver', 3);
  PointerUp = new PointerEvent$EventType('PointerUp', 4);
  PointerCancel = new PointerEvent$EventType('PointerCancel', 5);
}

function $getNativeEventName(this$static){
  return (this$static.name_0 != null?this$static.name_0:'' + this$static.ordinal).toLowerCase();
}

function PointerEvent$EventType(enum$name, enum$ordinal){
  Enum.call(this, enum$name, enum$ordinal);
}

function values_6(){
  $clinit_PointerEvent$EventType();
  return stampJavaTypeInfo(getClassLiteralForArray(Lcom_vaadin_client_event_PointerEvent$EventType_2_classLit, 1), $intern_0, 51, 0, [PointerDown, PointerMove, PointerOut, PointerOver, PointerUp, PointerCancel]);
}

defineClass(51, 5, {51:1, 3:1, 6:1, 5:1}, PointerEvent$EventType);
var PointerCancel, PointerDown, PointerMove, PointerOut, PointerOver, PointerUp;
var Lcom_vaadin_client_event_PointerEvent$EventType_2_classLit = createForEnum('com.vaadin.client.event', 'PointerEvent/EventType', 51, Ljava_lang_Enum_2_classLit, values_6);
function $clinit_PointerMoveEvent(){
  $clinit_PointerMoveEvent = emptyMethod;
  TYPE_12 = new DomEvent$Type($getNativeEventName(($clinit_PointerEvent$EventType() , PointerMove)), new PointerMoveEvent);
}

function PointerMoveEvent(){
}

defineClass(275, 343, {}, PointerMoveEvent);
_.dispatch = function dispatch_12(handler){
  castTo(handler, 307).onPointerMove(this);
}
;
_.getAssociatedType_0 = function getAssociatedType_13(){
  return TYPE_12;
}
;
var TYPE_12;
var Lcom_vaadin_client_event_PointerMoveEvent_2_classLit = createForClass('com.vaadin.client.event', 'PointerMoveEvent', 275, Lcom_vaadin_client_event_PointerEvent_2_classLit);
function $clinit_PointerUpEvent(){
  $clinit_PointerUpEvent = emptyMethod;
  TYPE_13 = new DomEvent$Type($getNativeEventName(($clinit_PointerEvent$EventType() , PointerUp)), new PointerUpEvent);
}

function PointerUpEvent(){
}

defineClass(274, 343, {}, PointerUpEvent);
_.dispatch = function dispatch_13(handler){
  castTo(handler, 306).onPointerUp(this);
}
;
_.getAssociatedType_0 = function getAssociatedType_14(){
  return TYPE_13;
}
;
var TYPE_13;
var Lcom_vaadin_client_event_PointerUpEvent_2_classLit = createForClass('com.vaadin.client.event', 'PointerUpEvent', 274, Lcom_vaadin_client_event_PointerEvent_2_classLit);
function $clinit_Polymer(){
  $clinit_Polymer = emptyMethod;
  hasHtmlImports = 'import' in $doc.createElement('link');
  urlImported = new HashSet;
  whenImported = new HashMap;
}

function absoluteHref(hrefOrTag){
  $clinit_Polymer();
  if (!$equals_0(hrefOrTag.substr(0, 4), 'http')) {
    (new RegExp('^([\\w-]+)$')).test(hrefOrTag) && (hrefOrTag = hrefOrTag + '/' + hrefOrTag);
    $equals_0(hrefOrTag.substr(0, 16), 'bower_components') || (hrefOrTag = 'bower_components/' + hrefOrTag);
    (new RegExp('^(.*\\.(html|js)$)$')).test(hrefOrTag) || (hrefOrTag += '.html');
    hrefOrTag = ($clinit_Impl() , $moduleBase) + ('' + hrefOrTag);
  }
  return hrefOrTag;
}

function ensureCustomElement(elem, imports){
  $clinit_Polymer();
  var src_0, src$index, src$max;
  if (isRegisteredElement(elem)) {
    return;
  }
  $schedule(new Polymer$2(elem), 0);
  for (src$index = 0 , src$max = imports.length; src$index < src$max; ++src$index) {
    src_0 = imports[src$index];
    importHref(src_0, null, null);
  }
  whenReady(makeLambdaFunction(Polymer$3.prototype.call_0, Polymer$3, [elem]), elem);
}

function importHref(hrefOrTag, ok, err){
  $clinit_Polymer();
  var done, e, href_0, pending;
  href_0 = absoluteHref(hrefOrTag);
  done = makeLambdaFunction(Polymer$lambda$0$Type.prototype.call_0, Polymer$lambda$0$Type, [href_0]);
  if (Base == null) {
    whenPolymerLoaded(makeLambdaFunction(Polymer$lambda$1$Type.prototype.call_0, Polymer$lambda$1$Type, [hrefOrTag, ok, err]));
    return;
  }
  if (!$contains_1(urlImported, href_0)) {
    e = $createElement($doc, $replaceFirst(href_0, '^.*/(.+).html$', '$1'));
    if (!isRegisteredElement(e)) {
      pending = castTo($getStringValue(whenImported, href_0), 29);
      if (!pending) {
        pending = new ArrayList;
        $putStringValue(whenImported, href_0, pending);
        Base.importHref(href_0, done, err);
      }
      ok != null && pending.add_0(ok);
      return;
    }
    $add_3(urlImported, href_0);
  }
  ok != null && ok(null);
}

function isRegisteredElement(e){
  $clinit_Polymer();
  return !!e && e.constructor !== $wnd.HTMLElement && e.constructor != $wnd.HTMLUnknownElement;
}

function lambda$0(href_0){
  $clinit_Polymer();
  var f, f$iterator, pending;
  $add_3(urlImported, href_0);
  pending = castTo($getStringValue(whenImported, href_0), 29);
  if (pending) {
    for (f$iterator = pending.iterator(); f$iterator.hasNext_0();) {
      f = castToFunction(f$iterator.next_1());
      f(null);
    }
  }
  $removeStringValue(whenImported, href_0);
  return null;
}

function onReady(e, f){
  whenReady(castToFunction(f), e);
}

function restoreProperties(e){
  $clinit_Polymer();
  e && e.__o && onReady(e, function(){
    for (i in e.__o) {
      e[i] = e.__o[i];
    }
    delete e.__o;
  }
  );
}

function saveProperties(e){
  $clinit_Polymer();
  if (!isRegisteredElement(e)) {
    var o = {};
    for (i in e) {
      if (e.hasOwnProperty(i) && !/(^_|_$)/.test(i)) {
        o[i] = e[i];
        delete e[i];
        e.__o = o;
      }
    }
  }
}

function whenPolymerLoaded(ok_0){
  function resolve(){
    Polymer = $wnd.Polymer;
    Base = $wnd.Polymer.Base;
    $doc.body.removeAttribute('unresolved');
  }

  if (!$wnd.Polymer) {
    if (!$wnd._pending_oks) {
      $wnd._pending_oks = [ok_0];
      var l = $doc.createElement('link');
      l.rel = 'import';
      l.href = absoluteHref('polymer');
      l.onload = function(){
        $wnd._pending_oks.forEach(function(ok){
          ok();
        }
        );
        $wnd._pending_oks = undefined;
        resolve();
      }
      ;
      $doc.head.appendChild(l);
    }
    $wnd._pending_oks.push(ok_0);
  }
   else {
    resolve();
    ok_0();
  }
}

function whenReady(f, e){
  function registered(){
    if (e) {
      var id_0 = setInterval(function(){
        if (isRegisteredElement(e)) {
          clearInterval(id_0);
          f && f(e);
        }
      }
      , 10);
    }
     else {
      f && f();
    }
  }

  function done(){
    $wnd.HTMLImports.whenReady(registered);
  }

  function loadPolyfill(){
    var s = $doc.createElement('script');
    s.src = absoluteHref('webcomponentsjs/webcomponents-lite.min.js');
    s.onreadystatechange = s.onload = done;
    $doc.head.appendChild(s);
  }

  !$wnd.HTMLImports?hasHtmlImports?registered():loadPolyfill():done();
}

var Base, Polymer, hasHtmlImports = false, urlImported, whenImported;
function Polymer$2(val$elem){
  this.val$elem1 = val$elem;
  Timer.call(this);
}

defineClass(254, 48, {}, Polymer$2);
_.run = function run_0(){
  saveProperties(this.val$elem1);
}
;
var Lcom_vaadin_polymer_Polymer$2_2_classLit = createForClass('com.vaadin.polymer', 'Polymer/2', 254, Lcom_google_gwt_user_client_Timer_2_classLit);
function Polymer$3(val$elem){
  this.val$elem1 = val$elem;
}

defineClass(379, $wnd.Function, $intern_25, Polymer$3);
_.call_0 = function call_0(arg){
  restoreProperties(this.val$elem1);
  return null;
}
;
function Polymer$lambda$0$Type(href_0){
  this.href_0 = href_0;
}

defineClass(377, $wnd.Function, $intern_25, Polymer$lambda$0$Type);
_.call_0 = function call_1(arg0){
  return lambda$0(this.href_0);
}
;
function Polymer$lambda$1$Type(hrefOrTag_0, ok_1, err_2){
  this.hrefOrTag_0 = hrefOrTag_0;
  this.ok_1 = ok_1;
  this.err_2 = err_2;
}

defineClass(378, $wnd.Function, $intern_25, Polymer$lambda$1$Type);
_.call_0 = function call_2(arg0){
  return $clinit_Polymer() , importHref(this.hrefOrTag_0, this.ok_1, this.err_2) , null;
}
;
function $clinit_MouseEventDetails(){
  $clinit_MouseEventDetails = emptyMethod;
  $clinit_MouseEventDetails$MouseButton();
}

function $setAltKey(this$static, altKey){
  this$static.altKey_0 = altKey;
}

function $setButton(this$static, button){
  this$static.button_0 = button;
}

function $setClientX(this$static, clientX){
  this$static.clientX_0 = clientX;
}

function $setClientY(this$static, clientY){
  this$static.clientY_0 = clientY;
}

function $setCtrlKey(this$static, ctrlKey){
  this$static.ctrlKey_0 = ctrlKey;
}

function $setMetaKey(this$static, metaKey){
  this$static.metaKey_0 = metaKey;
}

function $setRelativeX(this$static, relativeX){
  this$static.relativeX = relativeX;
}

function $setRelativeY(this$static, relativeY){
  this$static.relativeY = relativeY;
}

function $setShiftKey(this$static, shiftKey){
  this$static.shiftKey_0 = shiftKey;
}

function $setType(this$static, type_0){
  this$static.type_0 = type_0;
}

function MouseEventDetails(){
  $clinit_MouseEventDetails();
}

defineClass(111, 1, $intern_9, MouseEventDetails);
_.toString_0 = function toString_16(){
  return $toString_0(this.button_0) + ',' + this.clientX_0 + ',' + this.clientY_0 + ',' + this.altKey_0 + ',' + this.ctrlKey_0 + ',' + this.metaKey_0 + ',' + this.shiftKey_0 + ',' + this.type_0 + ',' + this.relativeX + ',' + this.relativeY;
}
;
_.altKey_0 = false;
_.clientX_0 = 0;
_.clientY_0 = 0;
_.ctrlKey_0 = false;
_.metaKey_0 = false;
_.relativeX = -1;
_.relativeY = -1;
_.shiftKey_0 = false;
_.type_0 = 0;
var Lcom_vaadin_shared_MouseEventDetails_2_classLit = createForClass('com.vaadin.shared', 'MouseEventDetails', 111, Ljava_lang_Object_2_classLit);
function $clinit_MouseEventDetails$MouseButton(){
  $clinit_MouseEventDetails$MouseButton = emptyMethod;
  LEFT = new MouseEventDetails$MouseButton('LEFT', 0);
  RIGHT = new MouseEventDetails$MouseButton('RIGHT', 1);
  MIDDLE = new MouseEventDetails$MouseButton('MIDDLE', 2);
}

function MouseEventDetails$MouseButton(enum$name, enum$ordinal){
  Enum.call(this, enum$name, enum$ordinal);
}

function values_7(){
  $clinit_MouseEventDetails$MouseButton();
  return stampJavaTypeInfo(getClassLiteralForArray(Lcom_vaadin_shared_MouseEventDetails$MouseButton_2_classLit, 1), $intern_0, 68, 0, [LEFT, RIGHT, MIDDLE]);
}

defineClass(68, 5, {68:1, 3:1, 6:1, 5:1}, MouseEventDetails$MouseButton);
var LEFT, MIDDLE, RIGHT;
var Lcom_vaadin_shared_MouseEventDetails$MouseButton_2_classLit = createForEnum('com.vaadin.shared', 'MouseEventDetails/MouseButton', 68, Ljava_lang_Enum_2_classLit, values_7);
function $parseAndroidVersion(this$static, userAgent){
  var osVersionString, parts;
  if (userAgent.indexOf('android') == -1) {
    return;
  }
  osVersionString = $safeSubstring(userAgent, userAgent.indexOf('android ') + 8, userAgent.length);
  osVersionString = $safeSubstring(osVersionString, 0, osVersionString.indexOf(';'));
  parts = $split(osVersionString, '\\.');
  $parseOsVersion(this$static, parts);
}

function $parseChromeOSVersion(this$static, userAgent){
  var cur, end, osVersionString, parts, start_0;
  start_0 = userAgent.indexOf('; cros ');
  if (start_0 == -1) {
    return;
  }
  end = $indexOf_1(userAgent, fromCodePoint(41), start_0);
  if (end == -1) {
    return;
  }
  cur = end;
  while (cur >= start_0 && (checkCriticalStringElementIndex(cur, userAgent.length) , userAgent.charCodeAt(cur) != 32)) {
    --cur;
  }
  if (cur == start_0) {
    return;
  }
  osVersionString = userAgent.substr(cur + 1, end - (cur + 1));
  parts = $split(osVersionString, '\\.');
  $parseChromeOsVersion(this$static, parts);
}

function $parseChromeOsVersion(this$static, parts){
  this$static.osMajorVersion = -1;
  this$static.osMinorVersion = -1;
  if (parts.length > 2) {
    try {
      this$static.osMajorVersion = __parseAndValidateInt(parts[1]);
    }
     catch ($e0) {
      $e0 = toJava($e0);
      if (!instanceOf($e0, 10))
        throw toJs($e0);
    }
    try {
      this$static.osMinorVersion = __parseAndValidateInt(parts[0]);
    }
     catch ($e1) {
      $e1 = toJava($e1);
      if (!instanceOf($e1, 10))
        throw toJs($e1);
    }
  }
}

function $parseIOSVersion(this$static, userAgent){
  var osVersionString, parts;
  if (userAgent.indexOf('os ') == -1 || userAgent.indexOf(' like mac') == -1) {
    return;
  }
  osVersionString = $safeSubstring(userAgent, userAgent.indexOf('os ') + 3, userAgent.indexOf(' like mac'));
  parts = $split(osVersionString, '_');
  $parseOsVersion(this$static, parts);
}

function $parseOsVersion(this$static, parts){
  this$static.osMajorVersion = -1;
  this$static.osMinorVersion = -1;
  if (parts.length >= 1) {
    try {
      this$static.osMajorVersion = __parseAndValidateInt(parts[0]);
    }
     catch ($e0) {
      $e0 = toJava($e0);
      if (!instanceOf($e0, 10))
        throw toJs($e0);
    }
  }
  if (parts.length >= 2) {
    try {
      this$static.osMinorVersion = __parseAndValidateInt(parts[1]);
    }
     catch ($e1) {
      $e1 = toJava($e1);
      if (!instanceOf($e1, 10))
        throw toJs($e1);
    }
    if (this$static.osMinorVersion == -1 && parts[1].indexOf('-') != -1) {
      try {
        this$static.osMinorVersion = __parseAndValidateInt($substring_0(parts[1], 0, $indexOf_0(parts[1], fromCodePoint(45))));
      }
       catch ($e2) {
        $e2 = toJava($e2);
        if (!instanceOf($e2, 10))
          throw toJs($e2);
      }
    }
  }
}

function $parseVersionString(this$static, versionString){
  var idx, idx2;
  idx = $indexOf_0(versionString, fromCodePoint(46));
  idx < 0 && (idx = versionString.length);
  this$static.browserMajorVersion = __parseAndValidateInt($safeSubstring(versionString, 0, idx));
  idx2 = $indexOf_1(versionString, fromCodePoint(46), idx + 1);
  idx2 < 0 && (idx2 = versionString.length);
  try {
    this$static.browserMinorVersion = __parseAndValidateInt($replaceAll($safeSubstring(versionString, idx + 1, idx2), '[^0-9].*', ''));
  }
   catch ($e0) {
    $e0 = toJava($e0);
    if (!instanceOf($e0, 27))
      throw toJs($e0);
  }
}

function $safeSubstring(string, beginIndex, endIndex){
  beginIndex < 0 && (beginIndex = 0);
  (endIndex < 0 || endIndex > string.length) && (endIndex = string.length);
  return string.substr(beginIndex, endIndex - beginIndex);
}

function $setIEMode(this$static, documentMode){
  this$static.browserMajorVersion = documentMode;
  this$static.browserMinorVersion = 0;
}

function VBrowserDetails(userAgent){
  var i_0, ieVersionString, rvPos, tmp;
  userAgent = userAgent.toLowerCase();
  this.isGecko = userAgent.indexOf('gecko') != -1 && userAgent.indexOf('webkit') == -1 && userAgent.indexOf('trident/') == -1;
  userAgent.indexOf(' presto/') != -1;
  this.isTrident = userAgent.indexOf('trident/') != -1;
  this.isWebKit = !this.isTrident && userAgent.indexOf('applewebkit') != -1;
  this.isChrome = userAgent.indexOf(' chrome/') != -1 || userAgent.indexOf(' crios/') != -1;
  this.isOpera = userAgent.indexOf('opera') != -1;
  this.isIE = userAgent.indexOf('msie') != -1 && !this.isOpera && userAgent.indexOf('webtv') == -1;
  this.isIE = this.isIE || this.isTrident;
  this.isPhantomJS = userAgent.indexOf('phantomjs/') != -1;
  this.isSafari = !this.isChrome && !this.isIE && !this.isPhantomJS && userAgent.indexOf('safari') != -1;
  this.isFirefox = userAgent.indexOf(' firefox/') != -1;
  if (userAgent.indexOf(' edge/') != -1) {
    this.isEdge = true;
    this.isChrome = false;
    this.isOpera = false;
    this.isIE = false;
    this.isSafari = false;
    this.isFirefox = false;
    this.isWebKit = false;
    this.isGecko = false;
    this.isPhantomJS = false;
  }
  userAgent.indexOf('chromeframe') != -1;
  try {
    if (this.isGecko) {
      rvPos = userAgent.indexOf('rv:');
      if (rvPos >= 0) {
        tmp = userAgent.substr(rvPos + 3);
        tmp = $replaceFirst(tmp, '(\\.[0-9]+).+', '$1');
        this.browserEngineVersion = parseFloat_0(tmp);
      }
    }
     else if (this.isWebKit) {
      tmp = $substring(userAgent, userAgent.indexOf('webkit/') + 7);
      tmp = $replaceFirst(tmp, '([0-9]+)[^0-9].+', '$1');
      this.browserEngineVersion = parseFloat_0(tmp);
    }
     else if (this.isTrident) {
      tmp = $substring(userAgent, userAgent.indexOf('trident/') + 8);
      tmp = $replaceFirst(tmp, '([0-9]+\\.[0-9]+).*', '$1');
      this.browserEngineVersion = parseFloat_0(tmp);
      this.browserEngineVersion > 7 && (this.browserEngineVersion = 7);
    }
     else 
      this.isEdge && (this.browserEngineVersion = 0);
  }
   catch ($e0) {
    $e0 = toJava($e0);
    if (!instanceOf($e0, 10))
      throw toJs($e0);
  }
  try {
    if (this.isIE) {
      if (userAgent.indexOf('msie') == -1) {
        rvPos = userAgent.indexOf('rv:');
        if (rvPos >= 0) {
          tmp = userAgent.substr(rvPos + 3);
          tmp = $replaceFirst(tmp, '(\\.[0-9]+).+', '$1');
          $parseVersionString(this, tmp);
        }
      }
       else if (this.isTrident) {
        $setIEMode(this, round_int(this.browserEngineVersion) + 4);
      }
       else {
        ieVersionString = $substring(userAgent, userAgent.indexOf('msie ') + 5);
        ieVersionString = $safeSubstring(ieVersionString, 0, ieVersionString.indexOf(';'));
        $parseVersionString(this, ieVersionString);
      }
    }
     else if (this.isFirefox) {
      i_0 = userAgent.indexOf(' firefox/') + 9;
      $parseVersionString(this, $safeSubstring(userAgent, i_0, i_0 + 5));
    }
     else if (this.isChrome) {
      i_0 = userAgent.indexOf(' chrome/');
      i_0 != -1?(i_0 += 8):(i_0 = userAgent.indexOf(' crios/') + 7);
      $parseVersionString(this, $safeSubstring(userAgent, i_0, i_0 + 5));
    }
     else if (this.isSafari) {
      i_0 = userAgent.indexOf(' version/') + 9;
      $parseVersionString(this, $safeSubstring(userAgent, i_0, i_0 + 5));
    }
     else if (this.isOpera) {
      i_0 = userAgent.indexOf(' version/');
      i_0 != -1?(i_0 += 9):(i_0 = userAgent.indexOf('opera/') + 6);
      $parseVersionString(this, $safeSubstring(userAgent, i_0, i_0 + 5));
    }
     else if (this.isEdge) {
      i_0 = userAgent.indexOf(' edge/') + 6;
      $parseVersionString(this, $safeSubstring(userAgent, i_0, i_0 + 8));
    }
     else if (this.isPhantomJS) {
      i_0 = userAgent.indexOf(' phantomjs/') + 11;
      $parseVersionString(this, $safeSubstring(userAgent, i_0, i_0 + 5));
    }
  }
   catch ($e1) {
    $e1 = toJava($e1);
    if (!instanceOf($e1, 10))
      throw toJs($e1);
  }
  if (userAgent.indexOf('windows ') != -1) {
    this.os = 1;
    userAgent.indexOf('windows phone') != -1;
  }
   else if (userAgent.indexOf('android') != -1) {
    this.os = 5;
    $parseAndroidVersion(this, userAgent);
  }
   else if (userAgent.indexOf('linux') != -1) {
    this.os = 3;
  }
   else if (userAgent.indexOf('macintosh') != -1 || userAgent.indexOf('mac osx') != -1 || userAgent.indexOf('mac os x') != -1) {
    this.isIPad = userAgent.indexOf('ipad') != -1;
    this.isIPhone = userAgent.indexOf('iphone') != -1;
    if (this.isIPad || userAgent.indexOf('ipod') != -1 || this.isIPhone) {
      this.os = 4;
      $parseIOSVersion(this, userAgent);
    }
     else {
      this.os = 2;
    }
  }
   else if (userAgent.indexOf('; cros ') != -1) {
    this.os = 6;
    $parseChromeOSVersion(this, userAgent);
  }
}

defineClass(260, 1, $intern_9, VBrowserDetails);
_.browserEngineVersion = -1;
_.browserMajorVersion = -1;
_.browserMinorVersion = -1;
_.isChrome = false;
_.isEdge = false;
_.isFirefox = false;
_.isGecko = false;
_.isIE = false;
_.isIPad = false;
_.isIPhone = false;
_.isOpera = false;
_.isPhantomJS = false;
_.isSafari = false;
_.isTrident = false;
_.isWebKit = false;
_.os = 0;
_.osMajorVersion = -1;
_.osMinorVersion = -1;
var Lcom_vaadin_shared_VBrowserDetails_2_classLit = createForClass('com.vaadin.shared', 'VBrowserDetails', 260, Ljava_lang_Object_2_classLit);
function AbstractStringBuilder(string){
  this.string = string;
}

defineClass(99, 1, {124:1});
_.toString_0 = function toString_17(){
  return this.string;
}
;
var Ljava_lang_AbstractStringBuilder_2_classLit = createForClass('java.lang', 'AbstractStringBuilder', 99, Ljava_lang_Object_2_classLit);
function ArithmeticException(){
  RuntimeException_0.call(this, 'divide by zero');
}

defineClass(131, 18, $intern_1, ArithmeticException);
var Ljava_lang_ArithmeticException_2_classLit = createForClass('java.lang', 'ArithmeticException', 131, Ljava_lang_RuntimeException_2_classLit);
function ArrayStoreException(){
  RuntimeException.call(this);
}

defineClass(130, 18, $intern_1, ArrayStoreException);
var Ljava_lang_ArrayStoreException_2_classLit = createForClass('java.lang', 'ArrayStoreException', 130, Ljava_lang_RuntimeException_2_classLit);
function $clinit_Boolean(){
  $clinit_Boolean = emptyMethod;
}

booleanCastMap = {3:1, 6:1};
var Ljava_lang_Boolean_2_classLit = createForClass('java.lang', 'Boolean', 324, Ljava_lang_Object_2_classLit);
function digit(c){
  if (c >= 48 && c < 48 + $wnd.Math.min(10, 10)) {
    return c - 48;
  }
  if (c >= 97 && c < 97) {
    return c - 97 + 10;
  }
  if (c >= 65 && c < 65) {
    return c - 65 + 10;
  }
  return -1;
}

function ClassCastException(){
  RuntimeException_0.call(this, null);
}

defineClass(126, 18, $intern_1, ClassCastException);
var Ljava_lang_ClassCastException_2_classLit = createForClass('java.lang', 'ClassCastException', 126, Ljava_lang_RuntimeException_2_classLit);
function __parseAndValidateDouble(s){
  floatRegex == null && (floatRegex = new RegExp('^\\s*[+-]?(NaN|Infinity|((\\d+\\.?\\d*)|(\\.\\d+))([eE][+-]?\\d+)?[dDfF]?)\\s*$'));
  if (!floatRegex.test(s)) {
    throw toJs(new NumberFormatException('For input string: "' + s + '"'));
  }
  return parseFloat(s);
}

function __parseAndValidateInt(s){
  var i_0, isTooLow, length_0, startIndex, toReturn;
  if (s == null) {
    throw toJs(new NumberFormatException('null'));
  }
  length_0 = s.length;
  startIndex = length_0 > 0 && (checkCriticalStringElementIndex(0, s.length) , s.charCodeAt(0) == 45 || (checkCriticalStringElementIndex(0, s.length) , s.charCodeAt(0) == 43))?1:0;
  for (i_0 = startIndex; i_0 < length_0; i_0++) {
    if (digit((checkCriticalStringElementIndex(i_0, s.length) , s.charCodeAt(i_0))) == -1) {
      throw toJs(new NumberFormatException('For input string: "' + s + '"'));
    }
  }
  toReturn = parseInt(s, 10);
  isTooLow = toReturn < -2147483648;
  if (isNaN(toReturn)) {
    throw toJs(new NumberFormatException('For input string: "' + s + '"'));
  }
   else if (isTooLow || toReturn > 2147483647) {
    throw toJs(new NumberFormatException('For input string: "' + s + '"'));
  }
  return toReturn;
}

function __parseAndValidateLong(s){
  var c, firstTime, head, i_0, length_0, maxDigits, minValue, negative, orig, radixPower, toReturn;
  if (s == null) {
    throw toJs(new NumberFormatException('null'));
  }
  orig = s;
  length_0 = s.length;
  negative = false;
  if (length_0 > 0) {
    c = (checkCriticalStringElementIndex(0, s.length) , s.charCodeAt(0));
    if (c == 45 || c == 43) {
      s = s.substr(1);
      --length_0;
      negative = c == 45;
    }
  }
  if (length_0 == 0) {
    throw toJs(new NumberFormatException('For input string: "' + orig + '"'));
  }
  while (s.length > 0 && (checkCriticalStringElementIndex(0, s.length) , s.charCodeAt(0) == 48)) {
    s = s.substr(1);
    --length_0;
  }
  if (length_0 > ($clinit_Number$__ParseLong() , maxLengthForRadix)[10]) {
    throw toJs(new NumberFormatException('For input string: "' + orig + '"'));
  }
  for (i_0 = 0; i_0 < length_0; i_0++) {
    if (digit((checkCriticalStringElementIndex(i_0, s.length) , s.charCodeAt(i_0))) == -1) {
      throw toJs(new NumberFormatException('For input string: "' + orig + '"'));
    }
  }
  toReturn = 0;
  maxDigits = maxDigitsForRadix[10];
  radixPower = maxDigitsRadixPower[10];
  minValue = neg_0(maxValueForRadix[10]);
  firstTime = true;
  head = length_0 % maxDigits;
  if (head > 0) {
    toReturn = -parseInt(s.substr(0, head), 10);
    s = s.substr(head);
    length_0 -= head;
    firstTime = false;
  }
  while (length_0 >= maxDigits) {
    head = parseInt(s.substr(0, maxDigits), 10);
    s = s.substr(maxDigits);
    length_0 -= maxDigits;
    if (firstTime) {
      firstTime = false;
    }
     else {
      if (compare_0(toReturn, minValue) < 0) {
        throw toJs(new NumberFormatException('For input string: "' + orig + '"'));
      }
      toReturn = mul_0(toReturn, radixPower);
    }
    toReturn = sub_1(toReturn, head);
  }
  if (compare_0(toReturn, 0) > 0) {
    throw toJs(new NumberFormatException('For input string: "' + orig + '"'));
  }
  if (!negative) {
    toReturn = neg_0(toReturn);
    if (compare_0(toReturn, 0) < 0) {
      throw toJs(new NumberFormatException('For input string: "' + orig + '"'));
    }
  }
  return toReturn;
}

defineClass(75, 1, {3:1, 75:1});
var floatRegex;
var Ljava_lang_Number_2_classLit = createForClass('java.lang', 'Number', 75, Ljava_lang_Object_2_classLit);
function $hashCode_0(this$static){
  return round_int((checkCriticalNotNull(this$static) , this$static));
}

function $intValue(this$static){
  return round_int((checkCriticalNotNull(this$static) , this$static));
}

function $longValue(this$static){
  return fromDouble_0((checkCriticalNotNull(this$static) , this$static));
}

doubleCastMap = {3:1, 6:1, 75:1};
var Ljava_lang_Double_2_classLit = createForClass('java.lang', 'Double', 325, Ljava_lang_Number_2_classLit);
function parseFloat_0(s){
  var doubleValue;
  doubleValue = __parseAndValidateDouble(s);
  if (doubleValue > 3.4028234663852886E38) {
    return Infinity;
  }
   else if (doubleValue < -3.4028234663852886E38) {
    return -Infinity;
  }
  return doubleValue;
}

function IllegalArgumentException(message){
  RuntimeException_0.call(this, message);
}

defineClass(44, 18, $intern_1, IllegalArgumentException);
var Ljava_lang_IllegalArgumentException_2_classLit = createForClass('java.lang', 'IllegalArgumentException', 44, Ljava_lang_RuntimeException_2_classLit);
function IllegalStateException(){
  RuntimeException.call(this);
}

function IllegalStateException_0(s){
  RuntimeException_0.call(this, s);
}

defineClass(52, 18, $intern_1, IllegalStateException, IllegalStateException_0);
var Ljava_lang_IllegalStateException_2_classLit = createForClass('java.lang', 'IllegalStateException', 52, Ljava_lang_RuntimeException_2_classLit);
function IndexOutOfBoundsException(){
  RuntimeException.call(this);
}

function IndexOutOfBoundsException_0(message){
  RuntimeException_0.call(this, message);
}

defineClass(40, 18, $intern_1, IndexOutOfBoundsException, IndexOutOfBoundsException_0);
var Ljava_lang_IndexOutOfBoundsException_2_classLit = createForClass('java.lang', 'IndexOutOfBoundsException', 40, Ljava_lang_RuntimeException_2_classLit);
function Integer(value_0){
  this.value_0 = value_0;
}

function numberOfLeadingZeros_0(i_0){
  var m, n, y_0;
  if (i_0 < 0) {
    return 0;
  }
   else if (i_0 == 0) {
    return 32;
  }
   else {
    y_0 = -(i_0 >> 16);
    m = y_0 >> 16 & 16;
    n = 16 - m;
    i_0 = i_0 >> m;
    y_0 = i_0 - 256;
    m = y_0 >> 16 & 8;
    n += m;
    i_0 <<= m;
    y_0 = i_0 - 4096;
    m = y_0 >> 16 & 4;
    n += m;
    i_0 <<= m;
    y_0 = i_0 - 16384;
    m = y_0 >> 16 & 2;
    n += m;
    i_0 <<= m;
    y_0 = i_0 >> 14;
    m = y_0 & ~(y_0 >> 1);
    return n + 2 - m;
  }
}

function numberOfTrailingZeros(i_0){
  var r, rtn;
  if (i_0 == 0) {
    return 32;
  }
   else {
    rtn = 0;
    for (r = 1; (r & i_0) == 0; r <<= 1) {
      ++rtn;
    }
    return rtn;
  }
}

function valueOf_0(i_0){
  var rebase, result;
  if (i_0 > -129 && i_0 < 128) {
    rebase = i_0 + 128;
    result = ($clinit_Integer$BoxedValues() , boxedValues)[rebase];
    !result && (result = boxedValues[rebase] = new Integer(i_0));
    return result;
  }
  return new Integer(i_0);
}

defineClass(43, 75, {3:1, 6:1, 43:1, 75:1}, Integer);
_.equals_0 = function equals_6(o){
  return instanceOf(o, 43) && castTo(o, 43).value_0 == this.value_0;
}
;
_.hashCode_0 = function hashCode_8(){
  return this.value_0;
}
;
_.toString_0 = function toString_19(){
  return '' + this.value_0;
}
;
_.value_0 = 0;
var Ljava_lang_Integer_2_classLit = createForClass('java.lang', 'Integer', 43, Ljava_lang_Number_2_classLit);
function $clinit_Integer$BoxedValues(){
  $clinit_Integer$BoxedValues = emptyMethod;
  boxedValues = initUnidimensionalArray(Ljava_lang_Integer_2_classLit, $intern_0, 43, 256, 0, 1);
}

var boxedValues;
function Long(value_0){
  this.value_0 = value_0;
}

function valueOf_1(i_0){
  var rebase, result;
  if (compare_0(i_0, -129) > 0 && compare_0(i_0, 128) < 0) {
    rebase = toInt_0(i_0) + 128;
    result = ($clinit_Long$BoxedValues() , boxedValues_0)[rebase];
    !result && (result = boxedValues_0[rebase] = new Long(i_0));
    return result;
  }
  return new Long(i_0);
}

defineClass(56, 75, {3:1, 6:1, 56:1, 75:1}, Long);
_.equals_0 = function equals_7(o){
  return instanceOf(o, 56) && eq(castTo(o, 56).value_0, this.value_0);
}
;
_.hashCode_0 = function hashCode_9(){
  return toInt_0(this.value_0);
}
;
_.toString_0 = function toString_20(){
  return '' + toString_12(this.value_0);
}
;
_.value_0 = 0;
var Ljava_lang_Long_2_classLit = createForClass('java.lang', 'Long', 56, Ljava_lang_Number_2_classLit);
function $clinit_Long$BoxedValues(){
  $clinit_Long$BoxedValues = emptyMethod;
  boxedValues_0 = initUnidimensionalArray(Ljava_lang_Long_2_classLit, $intern_0, 56, 256, 0, 1);
}

var boxedValues_0;
function min_1(x_0, y_0){
  return compare_0(x_0, y_0) < 0?x_0:y_0;
}

defineClass(396, 1, {});
function NullPointerException(){
  RuntimeException.call(this);
}

function NullPointerException_0(message){
  RuntimeException_0.call(this, message);
}

defineClass(53, 82, $intern_1, NullPointerException, NullPointerException_0);
_.createError = function createError_0(msg){
  return new TypeError(msg);
}
;
var Ljava_lang_NullPointerException_2_classLit = createForClass('java.lang', 'NullPointerException', 53, Ljava_lang_JsException_2_classLit);
function $clinit_Number$__ParseLong(){
  $clinit_Number$__ParseLong = emptyMethod;
  var i_0;
  maxDigitsForRadix = stampJavaTypeInfo(getClassLiteralForArray(I_classLit, 1), $intern_9, 42, 15, [-1, -1, 30, 19, 15, 13, 11, 11, 10, 9, 9, 8, 8, 8, 8, 7, 7, 7, 7, 7, 7, 7, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 5]);
  maxDigitsRadixPower = initUnidimensionalArray(I_classLit, $intern_9, 42, 37, 15, 1);
  maxLengthForRadix = stampJavaTypeInfo(getClassLiteralForArray(I_classLit, 1), $intern_9, 42, 15, [-1, -1, 63, 40, 32, 28, 25, 23, 21, 20, 19, 19, 18, 18, 17, 17, 16, 16, 16, 15, 15, 15, 15, 14, 14, 14, 14, 14, 14, 13, 13, 13, 13, 13, 13, 13, 13]);
  maxValueForRadix = initUnidimensionalArray(J_classLit, $intern_9, 42, 37, 14, 1);
  for (i_0 = 2; i_0 <= 36; i_0++) {
    maxDigitsRadixPower[i_0] = round_int($wnd.Math.pow(i_0, maxDigitsForRadix[i_0]));
    maxValueForRadix[i_0] = div_0({l:$intern_12, m:$intern_12, h:524287}, maxDigitsRadixPower[i_0]);
  }
}

var maxDigitsForRadix, maxDigitsRadixPower, maxLengthForRadix, maxValueForRadix;
function NumberFormatException(message){
  IllegalArgumentException.call(this, message);
}

defineClass(27, 44, {3:1, 10:1, 27:1, 9:1}, NumberFormatException);
var Ljava_lang_NumberFormatException_2_classLit = createForClass('java.lang', 'NumberFormatException', 27, Ljava_lang_IllegalArgumentException_2_classLit);
function $charAt(this$static, index_0){
  checkCriticalStringElementIndex(index_0, this$static.length);
  return this$static.charCodeAt(index_0);
}

function $equals_0(this$static, other){
  return checkCriticalNotNull(this$static) , this$static === other;
}

function $equalsIgnoreCase(this$static, other){
  checkCriticalNotNull(this$static);
  if (other == null) {
    return false;
  }
  if ($equals_0(this$static, other)) {
    return true;
  }
  return this$static.length == other.length && $equals_0(this$static.toLowerCase(), other.toLowerCase());
}

function $indexOf_0(this$static, str){
  return this$static.indexOf(str);
}

function $indexOf_1(this$static, str, startIndex){
  return this$static.indexOf(str, startIndex);
}

function $replace(this$static){
  var replacement;
  replacement = $replaceAll('$1', '\\$', '\\\\$');
  return $replaceAll(this$static, ' \\*\\(\\[;:\\]\\) \\*', replacement);
}

function $replaceAll(this$static, regex, replace){
  replace = translateReplaceString(replace);
  return this$static.replace(new RegExp(regex, 'g'), replace);
}

function $replaceFirst(this$static, regex, replace){
  var jsRegEx;
  replace = translateReplaceString(replace);
  jsRegEx = new RegExp(regex);
  return this$static.replace(jsRegEx, replace);
}

function $split(this$static, regex){
  var compiled, count, lastNonEmpty, lastTrail, matchIndex, matchObj, out, trail;
  compiled = new RegExp(regex, 'g');
  out = initUnidimensionalArray(Ljava_lang_String_2_classLit, $intern_0, 2, 0, 6, 1);
  count = 0;
  trail = this$static;
  lastTrail = null;
  while (true) {
    matchObj = compiled.exec(trail);
    if (matchObj == null || trail == '') {
      out[count] = trail;
      break;
    }
     else {
      matchIndex = matchObj.index;
      out[count] = trail.substr(0, matchIndex);
      trail = $substring_0(trail, matchIndex + matchObj[0].length, trail.length);
      compiled.lastIndex = 0;
      if (lastTrail == trail) {
        out[count] = trail.substr(0, 1);
        trail = trail.substr(1);
      }
      lastTrail = trail;
      ++count;
    }
  }
  if (this$static.length > 0) {
    lastNonEmpty = out.length;
    while (lastNonEmpty > 0 && out[lastNonEmpty - 1] == '') {
      --lastNonEmpty;
    }
    lastNonEmpty < out.length && (out.length = lastNonEmpty);
  }
  return out;
}

function $substring(this$static, beginIndex){
  return this$static.substr(beginIndex);
}

function $substring_0(this$static, beginIndex, endIndex){
  return this$static.substr(beginIndex, endIndex - beginIndex);
}

function $trim(this$static){
  var end, length_0, start_0;
  length_0 = this$static.length;
  start_0 = 0;
  while (start_0 < length_0 && (checkCriticalStringElementIndex(start_0, this$static.length) , this$static.charCodeAt(start_0) <= 32)) {
    ++start_0;
  }
  end = length_0;
  while (end > start_0 && (checkCriticalStringElementIndex(end - 1, this$static.length) , this$static.charCodeAt(end - 1) <= 32)) {
    --end;
  }
  return start_0 > 0 || end < length_0?this$static.substr(start_0, end - start_0):this$static;
}

function fromCharCode(array){
  return String.fromCharCode.apply(null, array);
}

function fromCodePoint(codePoint){
  var hiSurrogate, loSurrogate;
  if (codePoint >= $intern_19) {
    hiSurrogate = 55296 + (codePoint - $intern_19 >> 10 & 1023) & $intern_11;
    loSurrogate = 56320 + (codePoint - $intern_19 & 1023) & $intern_11;
    return String.fromCharCode(hiSurrogate) + ('' + String.fromCharCode(loSurrogate));
  }
   else {
    return String.fromCharCode(codePoint & $intern_11);
  }
}

function translateReplaceString(replaceStr){
  var pos;
  pos = 0;
  while (0 <= (pos = replaceStr.indexOf('\\', pos))) {
    checkCriticalStringElementIndex(pos + 1, replaceStr.length);
    replaceStr.charCodeAt(pos + 1) == 36?(replaceStr = replaceStr.substr(0, pos) + '$' + $substring(replaceStr, ++pos)):(replaceStr = replaceStr.substr(0, pos) + ('' + $substring(replaceStr, ++pos)));
  }
  return replaceStr;
}

function valueOf_2(x_0){
  return x_0 == null?'null':toString_13(x_0);
}

function valueOf_3(x_0){
  return valueOf_4(x_0, x_0.length);
}

function valueOf_4(x_0, count){
  var batchEnd, batchStart, s;
  checkCriticalStringBounds(count, x_0.length);
  s = '';
  for (batchStart = 0; batchStart < count;) {
    batchEnd = $wnd.Math.min(batchStart + 10000, count);
    s += fromCharCode(x_0.slice(batchStart, batchEnd));
    batchStart = batchEnd;
  }
  return s;
}

stringCastMap = {3:1, 124:1, 6:1, 2:1};
var Ljava_lang_String_2_classLit = createForClass('java.lang', 'String', 2, Ljava_lang_Object_2_classLit);
function $append(this$static, x_0){
  this$static.string += x_0;
  return this$static;
}

function $append_0(this$static, x_0){
  this$static.string += '' + x_0;
  return this$static;
}

function $append_1(this$static, x_0){
  this$static.string += '' + x_0;
  return this$static;
}

function $append_2(this$static, x_0){
  this$static.string += '' + x_0;
  return this$static;
}

function StringBuilder(){
  AbstractStringBuilder.call(this, '');
}

function StringBuilder_0(s){
  AbstractStringBuilder.call(this, (checkCriticalNotNull(s) , s));
}

defineClass(45, 99, {124:1}, StringBuilder, StringBuilder_0);
var Ljava_lang_StringBuilder_2_classLit = createForClass('java.lang', 'StringBuilder', 45, Ljava_lang_AbstractStringBuilder_2_classLit);
function StringIndexOutOfBoundsException(message){
  IndexOutOfBoundsException_0.call(this, message);
}

defineClass(101, 40, $intern_1, StringIndexOutOfBoundsException);
var Ljava_lang_StringIndexOutOfBoundsException_2_classLit = createForClass('java.lang', 'StringIndexOutOfBoundsException', 101, Ljava_lang_IndexOutOfBoundsException_2_classLit);
defineClass(400, 1, {});
function UnsupportedOperationException(){
  RuntimeException.call(this);
}

function UnsupportedOperationException_0(message){
  RuntimeException_0.call(this, message);
}

defineClass(36, 18, $intern_1, UnsupportedOperationException, UnsupportedOperationException_0);
var Ljava_lang_UnsupportedOperationException_2_classLit = createForClass('java.lang', 'UnsupportedOperationException', 36, Ljava_lang_RuntimeException_2_classLit);
function $advanceToFind(this$static, o, remove){
  var e, iter;
  for (iter = this$static.iterator(); iter.hasNext_0();) {
    e = iter.next_1();
    if (maskUndefined(o) === maskUndefined(e) || o != null && equals_Ljava_lang_Object__Z__devirtual$(o, e)) {
      remove && iter.remove_1();
      return true;
    }
  }
  return false;
}

function $containsAll(this$static, c){
  var e, e$iterator;
  checkCriticalNotNull(c);
  for (e$iterator = c.iterator(); e$iterator.hasNext_0();) {
    e = e$iterator.next_1();
    if (!this$static.contains_0(e)) {
      return false;
    }
  }
  return true;
}

function $toString_1(this$static){
  var e, e$iterator, joiner;
  joiner = new StringJoiner('[', ']');
  for (e$iterator = this$static.iterator(); e$iterator.hasNext_0();) {
    e = e$iterator.next_1();
    $add_5(joiner, e === this$static?'(this Collection)':e == null?'null':toString_13(e));
  }
  return !joiner.builder?joiner.emptyValue:joiner.suffix.length == 0?joiner.builder.string:joiner.builder.string + ('' + joiner.suffix);
}

defineClass(330, 1, {});
_.add_0 = function add_2(o){
  throw toJs(new UnsupportedOperationException_0('Add not supported on this collection'));
}
;
_.contains_0 = function contains(o){
  return $advanceToFind(this, o, false);
}
;
_.isEmpty = function isEmpty(){
  return this.size_1() == 0;
}
;
_.remove_2 = function remove_3(o){
  return $advanceToFind(this, o, true);
}
;
_.toArray = function toArray(){
  return this.toArray_0(initUnidimensionalArray(Ljava_lang_Object_2_classLit, $intern_0, 1, this.size_1(), 5, 1));
}
;
_.toArray_0 = function toArray_0(a){
  var i_0, it, size_0;
  size_0 = this.size_1();
  a.length < size_0 && (a = stampJavaTypeInfo_1(new Array(size_0), a));
  it = this.iterator();
  for (i_0 = 0; i_0 < size_0; ++i_0) {
    setCheck(a, i_0, it.next_1());
  }
  a.length > size_0 && setCheck(a, size_0, null);
  return a;
}
;
_.toString_0 = function toString_21(){
  return $toString_1(this);
}
;
var Ljava_util_AbstractCollection_2_classLit = createForClass('java.util', 'AbstractCollection', 330, Ljava_lang_Object_2_classLit);
function $containsEntry(this$static, entry){
  var key, ourValue, value_0;
  key = entry.getKey();
  value_0 = entry.getValue();
  ourValue = this$static.get_0(key);
  if (!(maskUndefined(value_0) === maskUndefined(ourValue) || value_0 != null && equals_Ljava_lang_Object__Z__devirtual$(value_0, ourValue))) {
    return false;
  }
  if (ourValue == null && !this$static.containsKey(key)) {
    return false;
  }
  return true;
}

function $implFindEntry(this$static, key, remove){
  var entry, iter, k_0;
  for (iter = this$static.entrySet().iterator(); iter.hasNext_0();) {
    entry = castTo(iter.next_1(), 16);
    k_0 = entry.getKey();
    if (maskUndefined(key) === maskUndefined(k_0) || key != null && equals_Ljava_lang_Object__Z__devirtual$(key, k_0)) {
      if (remove) {
        entry = new AbstractMap$SimpleEntry(entry.getKey(), entry.getValue());
        iter.remove_1();
      }
      return entry;
    }
  }
  return null;
}

function $toString_2(this$static, o){
  return o === this$static?'(this Map)':o == null?'null':toString_13(o);
}

function getEntryValueOrNull(entry){
  return !entry?null:entry.getValue();
}

defineClass(329, 1, {35:1});
_.containsKey = function containsKey(key){
  return !!$implFindEntry(this, key, false);
}
;
_.equals_0 = function equals_8(obj){
  var entry, entry$iterator, otherMap;
  if (obj === this) {
    return true;
  }
  if (!instanceOf(obj, 35)) {
    return false;
  }
  otherMap = castTo(obj, 35);
  if (this.size_1() != otherMap.size_1()) {
    return false;
  }
  for (entry$iterator = otherMap.entrySet().iterator(); entry$iterator.hasNext_0();) {
    entry = castTo(entry$iterator.next_1(), 16);
    if (!$containsEntry(this, entry)) {
      return false;
    }
  }
  return true;
}
;
_.get_0 = function get_1(key){
  return getEntryValueOrNull($implFindEntry(this, key, false));
}
;
_.hashCode_0 = function hashCode_10(){
  return hashCode_15(this.entrySet());
}
;
_.isEmpty = function isEmpty_0(){
  return this.size_1() == 0;
}
;
_.keySet = function keySet(){
  return new AbstractMap$1(this);
}
;
_.put = function put(key, value_0){
  throw toJs(new UnsupportedOperationException_0('Put not supported on this map'));
}
;
_.remove_3 = function remove_4(key){
  return getEntryValueOrNull($implFindEntry(this, key, true));
}
;
_.size_1 = function size_1(){
  return this.entrySet().size_1();
}
;
_.toString_0 = function toString_22(){
  var entry, entry$iterator, joiner;
  joiner = new StringJoiner('{', '}');
  for (entry$iterator = this.entrySet().iterator(); entry$iterator.hasNext_0();) {
    entry = castTo(entry$iterator.next_1(), 16);
    $add_5(joiner, $toString_2(this, entry.getKey()) + '=' + $toString_2(this, entry.getValue()));
  }
  return !joiner.builder?joiner.emptyValue:joiner.suffix.length == 0?joiner.builder.string:joiner.builder.string + ('' + joiner.suffix);
}
;
var Ljava_util_AbstractMap_2_classLit = createForClass('java.util', 'AbstractMap', 329, Ljava_lang_Object_2_classLit);
function $containsKey(this$static, key){
  return instanceOfString(key)?$hasStringValue(this$static, key):!!$getEntry(this$static.hashCodeMap, key);
}

function $get_2(this$static, key){
  return instanceOfString(key)?$getStringValue(this$static, key):getEntryValueOrNull($getEntry(this$static.hashCodeMap, key));
}

function $getStringValue(this$static, key){
  return key == null?getEntryValueOrNull($getEntry(this$static.hashCodeMap, null)):$get_5(this$static.stringMap, key);
}

function $hasStringValue(this$static, key){
  return key == null?!!$getEntry(this$static.hashCodeMap, null):$contains_2(this$static.stringMap, key);
}

function $put_0(this$static, key, value_0){
  return instanceOfString(key)?$putStringValue(this$static, key, value_0):$put_1(this$static.hashCodeMap, key, value_0);
}

function $putStringValue(this$static, key, value_0){
  return key == null?$put_1(this$static.hashCodeMap, null, value_0):$put_2(this$static.stringMap, key, value_0);
}

function $remove_2(this$static, key){
  return instanceOfString(key)?$removeStringValue(this$static, key):$remove_8(this$static.hashCodeMap, key);
}

function $removeStringValue(this$static, key){
  return key == null?$remove_8(this$static.hashCodeMap, null):$remove_9(this$static.stringMap, key);
}

function $reset(this$static){
  this$static.hashCodeMap = new InternalHashCodeMap(this$static);
  this$static.stringMap = new InternalStringMap(this$static);
  structureChanged(this$static);
}

function $size(this$static){
  return this$static.hashCodeMap.size_0 + this$static.stringMap.size_0;
}

defineClass(136, 329, {35:1});
_.containsKey = function containsKey_0(key){
  return $containsKey(this, key);
}
;
_.entrySet = function entrySet(){
  return new AbstractHashMap$EntrySet(this);
}
;
_.get_0 = function get_2(key){
  return $get_2(this, key);
}
;
_.put = function put_0(key, value_0){
  return $put_0(this, key, value_0);
}
;
_.remove_3 = function remove_5(key){
  return $remove_2(this, key);
}
;
_.size_1 = function size_2(){
  return $size(this);
}
;
var Ljava_util_AbstractHashMap_2_classLit = createForClass('java.util', 'AbstractHashMap', 136, Ljava_util_AbstractMap_2_classLit);
defineClass(331, 330, $intern_26);
_.equals_0 = function equals_9(o){
  var other;
  if (o === this) {
    return true;
  }
  if (!instanceOf(o, 74)) {
    return false;
  }
  other = castTo(o, 74);
  if (other.size_1() != this.size_1()) {
    return false;
  }
  return $containsAll(this, other);
}
;
_.hashCode_0 = function hashCode_11(){
  return hashCode_15(this);
}
;
var Ljava_util_AbstractSet_2_classLit = createForClass('java.util', 'AbstractSet', 331, Ljava_util_AbstractCollection_2_classLit);
function $contains(this$static, o){
  if (instanceOf(o, 16)) {
    return $containsEntry(this$static.this$01, castTo(o, 16));
  }
  return false;
}

function AbstractHashMap$EntrySet(this$0){
  this.this$01 = this$0;
}

defineClass(137, 331, $intern_26, AbstractHashMap$EntrySet);
_.contains_0 = function contains_0(o){
  return $contains(this, o);
}
;
_.iterator = function iterator_2(){
  return new AbstractHashMap$EntrySetIterator(this.this$01);
}
;
_.remove_2 = function remove_6(entry){
  var key;
  if ($contains(this, entry)) {
    key = castTo(entry, 16).getKey();
    this.this$01.remove_3(key);
    return true;
  }
  return false;
}
;
_.size_1 = function size_3(){
  return this.this$01.size_1();
}
;
var Ljava_util_AbstractHashMap$EntrySet_2_classLit = createForClass('java.util', 'AbstractHashMap/EntrySet', 137, Ljava_util_AbstractSet_2_classLit);
function $computeHasNext(this$static){
  if (this$static.current.hasNext_0()) {
    return true;
  }
  if (this$static.current != this$static.stringMapEntries) {
    return false;
  }
  this$static.current = new InternalHashCodeMap$1(this$static.this$01.hashCodeMap);
  return this$static.current.hasNext_0();
}

function AbstractHashMap$EntrySetIterator(this$0){
  this.this$01 = this$0;
  this.stringMapEntries = new InternalStringMap$1(this.this$01.stringMap);
  this.current = this.stringMapEntries;
  this.hasNext = $computeHasNext(this);
  this.$modCount = this$0.$modCount;
}

defineClass(138, 1, {}, AbstractHashMap$EntrySetIterator);
_.next_1 = function next_1(){
  var rv;
  return checkStructuralChange(this.this$01, this) , checkCriticalElement(this.hasNext) , this.last = this.current , rv = castTo(this.current.next_1(), 16) , this.hasNext = $computeHasNext(this) , rv;
}
;
_.hasNext_0 = function hasNext_0(){
  return this.hasNext;
}
;
_.remove_1 = function remove_7(){
  checkCriticalState(!!this.last);
  checkStructuralChange(this.this$01, this);
  this.last.remove_1();
  this.last = null;
  this.hasNext = $computeHasNext(this);
  recordLastKnownStructure(this.this$01, this);
}
;
_.hasNext = false;
var Ljava_util_AbstractHashMap$EntrySetIterator_2_classLit = createForClass('java.util', 'AbstractHashMap/EntrySetIterator', 138, Ljava_lang_Object_2_classLit);
function $equals_1(this$static, o){
  var elem, elem$iterator, elemOther, iterOther, other;
  if (o === this$static) {
    return true;
  }
  if (!instanceOf(o, 29)) {
    return false;
  }
  other = castTo(o, 29);
  if (this$static.size_1() != other.size_1()) {
    return false;
  }
  iterOther = other.iterator();
  for (elem$iterator = this$static.iterator(); elem$iterator.hasNext_0();) {
    elem = elem$iterator.next_1();
    elemOther = iterOther.next_1();
    if (!(maskUndefined(elem) === maskUndefined(elemOther) || elem != null && equals_Ljava_lang_Object__Z__devirtual$(elem, elemOther))) {
      return false;
    }
  }
  return true;
}

function $indexOf_2(this$static, toFind){
  var i_0, n;
  for (i_0 = 0 , n = this$static.size_1(); i_0 < n; ++i_0) {
    if (equals_16(toFind, this$static.get_1(i_0))) {
      return i_0;
    }
  }
  return -1;
}

defineClass(332, 330, {29:1});
_.add_1 = function add_3(index_0, element){
  throw toJs(new UnsupportedOperationException_0('Add not supported on this list'));
}
;
_.add_0 = function add_4(obj){
  this.add_1(this.size_1(), obj);
  return true;
}
;
_.equals_0 = function equals_10(o){
  return $equals_1(this, o);
}
;
_.hashCode_0 = function hashCode_12(){
  return hashCode_16(this);
}
;
_.iterator = function iterator_3(){
  return new AbstractList$IteratorImpl(this);
}
;
_.listIterator = function listIterator(){
  return this.listIterator_0(0);
}
;
_.listIterator_0 = function listIterator_0(from){
  return new AbstractList$ListIteratorImpl(this, from);
}
;
_.remove_4 = function remove_8(index_0){
  throw toJs(new UnsupportedOperationException_0('Remove not supported on this list'));
}
;
var Ljava_util_AbstractList_2_classLit = createForClass('java.util', 'AbstractList', 332, Ljava_util_AbstractCollection_2_classLit);
function $remove_3(this$static){
  checkCriticalState(this$static.last != -1);
  this$static.this$01_0.remove_4(this$static.last);
  this$static.i = this$static.last;
  this$static.last = -1;
}

function AbstractList$IteratorImpl(this$0){
  this.this$01_0 = this$0;
}

defineClass(106, 1, {}, AbstractList$IteratorImpl);
_.hasNext_0 = function hasNext_1(){
  return this.i < this.this$01_0.size_1();
}
;
_.next_1 = function next_2(){
  checkCriticalElement(this.i < this.this$01_0.size_1());
  return this.this$01_0.get_1(this.last = this.i++);
}
;
_.remove_1 = function remove_9(){
  $remove_3(this);
}
;
_.i = 0;
_.last = -1;
var Ljava_util_AbstractList$IteratorImpl_2_classLit = createForClass('java.util', 'AbstractList/IteratorImpl', 106, Ljava_lang_Object_2_classLit);
function AbstractList$ListIteratorImpl(this$0, start_0){
  this.this$01 = this$0;
  AbstractList$IteratorImpl.call(this, this$0);
  checkCriticalPositionIndex(start_0, this$0.size_1());
  this.i = start_0;
}

defineClass(145, 106, {}, AbstractList$ListIteratorImpl);
_.remove_1 = function remove_10(){
  $remove_3(this);
}
;
_.add_2 = function add_5(o){
  this.this$01.add_1(this.i, o);
  ++this.i;
  this.last = -1;
}
;
_.hasPrevious = function hasPrevious(){
  return this.i > 0;
}
;
_.previous = function previous_0(){
  checkCriticalElement(this.i > 0);
  return this.this$01.get_1(this.last = --this.i);
}
;
var Ljava_util_AbstractList$ListIteratorImpl_2_classLit = createForClass('java.util', 'AbstractList/ListIteratorImpl', 145, Ljava_util_AbstractList$IteratorImpl_2_classLit);
function AbstractMap$1(this$0){
  this.this$01 = this$0;
}

defineClass(63, 331, $intern_26, AbstractMap$1);
_.contains_0 = function contains_1(key){
  return this.this$01.containsKey(key);
}
;
_.iterator = function iterator_4(){
  var outerIter;
  return outerIter = this.this$01.entrySet().iterator() , new AbstractMap$1$1(outerIter);
}
;
_.remove_2 = function remove_11(key){
  if (this.this$01.containsKey(key)) {
    this.this$01.remove_3(key);
    return true;
  }
  return false;
}
;
_.size_1 = function size_4(){
  return this.this$01.size_1();
}
;
var Ljava_util_AbstractMap$1_2_classLit = createForClass('java.util', 'AbstractMap/1', 63, Ljava_util_AbstractSet_2_classLit);
function AbstractMap$1$1(val$outerIter){
  this.val$outerIter2 = val$outerIter;
}

defineClass(64, 1, {}, AbstractMap$1$1);
_.hasNext_0 = function hasNext_2(){
  return this.val$outerIter2.hasNext_0();
}
;
_.next_1 = function next_3(){
  var entry;
  return entry = castTo(this.val$outerIter2.next_1(), 16) , entry.getKey();
}
;
_.remove_1 = function remove_12(){
  this.val$outerIter2.remove_1();
}
;
var Ljava_util_AbstractMap$1$1_2_classLit = createForClass('java.util', 'AbstractMap/1/1', 64, Ljava_lang_Object_2_classLit);
function $setValue(this$static, value_0){
  var oldValue;
  oldValue = this$static.value_0;
  this$static.value_0 = value_0;
  return oldValue;
}

defineClass(139, 1, $intern_27);
_.equals_0 = function equals_11(other){
  var entry;
  if (!instanceOf(other, 16)) {
    return false;
  }
  entry = castTo(other, 16);
  return equals_16(this.key, entry.getKey()) && equals_16(this.value_0, entry.getValue());
}
;
_.getKey = function getKey(){
  return this.key;
}
;
_.getValue = function getValue(){
  return this.value_0;
}
;
_.hashCode_0 = function hashCode_13(){
  return hashCode_19(this.key) ^ hashCode_19(this.value_0);
}
;
_.setValue = function setValue(value_0){
  return $setValue(this, value_0);
}
;
_.toString_0 = function toString_23(){
  return this.key + '=' + this.value_0;
}
;
var Ljava_util_AbstractMap$AbstractEntry_2_classLit = createForClass('java.util', 'AbstractMap/AbstractEntry', 139, Ljava_lang_Object_2_classLit);
function AbstractMap$SimpleEntry(key, value_0){
  this.key = key;
  this.value_0 = value_0;
}

defineClass(83, 139, $intern_27, AbstractMap$SimpleEntry);
var Ljava_util_AbstractMap$SimpleEntry_2_classLit = createForClass('java.util', 'AbstractMap/SimpleEntry', 83, Ljava_util_AbstractMap$AbstractEntry_2_classLit);
defineClass(333, 1, $intern_27);
_.equals_0 = function equals_12(other){
  var entry;
  if (!instanceOf(other, 16)) {
    return false;
  }
  entry = castTo(other, 16);
  return equals_16(this.val$entry2.value[0], entry.getKey()) && equals_16($getValue(this), entry.getValue());
}
;
_.hashCode_0 = function hashCode_14(){
  return hashCode_19(this.val$entry2.value[0]) ^ hashCode_19($getValue(this));
}
;
_.toString_0 = function toString_24(){
  return this.val$entry2.value[0] + '=' + $getValue(this);
}
;
var Ljava_util_AbstractMapEntry_2_classLit = createForClass('java.util', 'AbstractMapEntry', 333, Ljava_lang_Object_2_classLit);
function $get_3(this$static, index_0){
  var iter;
  iter = $listIterator(this$static, index_0);
  try {
    return $next_2(iter);
  }
   catch ($e0) {
    $e0 = toJava($e0);
    if (instanceOf($e0, 50)) {
      throw toJs(new IndexOutOfBoundsException_0("Can't get element " + index_0));
    }
     else 
      throw toJs($e0);
  }
}

defineClass(339, 332, {29:1});
_.add_1 = function add_6(index_0, element){
  var iter;
  iter = this.listIterator_0(index_0);
  iter.add_2(element);
}
;
_.get_1 = function get_3(index_0){
  return $get_3(this, index_0);
}
;
_.iterator = function iterator_5(){
  return $listIterator(this, 0);
}
;
_.remove_4 = function remove_13(index_0){
  var iter, old;
  iter = this.listIterator_0(index_0);
  try {
    old = iter.next_1();
    iter.remove_1();
    return old;
  }
   catch ($e0) {
    $e0 = toJava($e0);
    if (instanceOf($e0, 50)) {
      throw toJs(new IndexOutOfBoundsException_0("Can't remove element " + index_0));
    }
     else 
      throw toJs($e0);
  }
}
;
var Ljava_util_AbstractSequentialList_2_classLit = createForClass('java.util', 'AbstractSequentialList', 339, Ljava_util_AbstractList_2_classLit);
function $add_2(this$static, o){
  this$static.array[this$static.array.length] = o;
  return true;
}

function $addAll(this$static, c){
  var cArray, len;
  cArray = c.toArray();
  len = cArray.length;
  if (len == 0) {
    return false;
  }
  insertTo_0(this$static.array, this$static.array.length, cArray);
  return true;
}

function $get_4(this$static, index_0){
  checkCriticalElementIndex(index_0, this$static.array.length);
  return this$static.array[index_0];
}

function $indexOf_3(this$static, o, index_0){
  for (; index_0 < this$static.array.length; ++index_0) {
    if (equals_16(o, this$static.array[index_0])) {
      return index_0;
    }
  }
  return -1;
}

function $remove_4(this$static, index_0){
  var previous;
  previous = (checkCriticalElementIndex(index_0, this$static.array.length) , this$static.array[index_0]);
  removeFrom(this$static.array, index_0);
  return previous;
}

function $remove_5(this$static, o){
  var i_0;
  i_0 = $indexOf_3(this$static, o, 0);
  if (i_0 == -1) {
    return false;
  }
  checkCriticalElementIndex(i_0, this$static.array.length);
  removeFrom(this$static.array, i_0);
  return true;
}

function $toArray(this$static, out){
  var i_0, size_0;
  size_0 = this$static.array.length;
  out.length < size_0 && (out = stampJavaTypeInfo_1(new Array(size_0), out));
  for (i_0 = 0; i_0 < size_0; ++i_0) {
    setCheck(out, i_0, this$static.array[i_0]);
  }
  out.length > size_0 && setCheck(out, size_0, null);
  return out;
}

function ArrayList(){
  this.array = initUnidimensionalArray(Ljava_lang_Object_2_classLit, $intern_0, 1, 0, 5, 1);
}

defineClass(23, 332, $intern_28, ArrayList);
_.add_1 = function add_7(index_0, o){
  checkCriticalPositionIndex(index_0, this.array.length);
  insertTo(this.array, index_0, o);
}
;
_.add_0 = function add_8(o){
  return $add_2(this, o);
}
;
_.contains_0 = function contains_2(o){
  return $indexOf_3(this, o, 0) != -1;
}
;
_.get_1 = function get_4(index_0){
  return $get_4(this, index_0);
}
;
_.isEmpty = function isEmpty_1(){
  return this.array.length == 0;
}
;
_.iterator = function iterator_6(){
  return new ArrayList$1(this);
}
;
_.remove_4 = function remove_14(index_0){
  return $remove_4(this, index_0);
}
;
_.remove_2 = function remove_15(o){
  return $remove_5(this, o);
}
;
_.size_1 = function size_5(){
  return this.array.length;
}
;
_.toArray = function toArray_1(){
  return clone(this.array, this.array.length);
}
;
_.toArray_0 = function toArray_2(out){
  return $toArray(this, out);
}
;
var Ljava_util_ArrayList_2_classLit = createForClass('java.util', 'ArrayList', 23, Ljava_util_AbstractList_2_classLit);
function $next_0(this$static){
  checkCriticalElement(this$static.i < this$static.this$01.array.length);
  this$static.last = this$static.i++;
  return this$static.this$01.array[this$static.last];
}

function ArrayList$1(this$0){
  this.this$01 = this$0;
}

defineClass(28, 1, {}, ArrayList$1);
_.hasNext_0 = function hasNext_3(){
  return this.i < this.this$01.array.length;
}
;
_.next_1 = function next_4(){
  return $next_0(this);
}
;
_.remove_1 = function remove_16(){
  checkCriticalState(this.last != -1);
  $remove_4(this.this$01, this.i = this.last);
  this.last = -1;
}
;
_.i = 0;
_.last = -1;
var Ljava_util_ArrayList$1_2_classLit = createForClass('java.util', 'ArrayList/1', 28, Ljava_lang_Object_2_classLit);
function $toArray_0(this$static, out){
  var i_0, size_0;
  size_0 = this$static.array.length;
  out.length < size_0 && (out = stampJavaTypeInfo_1(new Array(size_0), out));
  for (i_0 = 0; i_0 < size_0; ++i_0) {
    setCheck(out, i_0, this$static.array[i_0]);
  }
  out.length > size_0 && setCheck(out, size_0, null);
  return out;
}

function Arrays$ArrayList(array){
  checkCriticalNotNull(array);
  this.array = array;
}

defineClass(88, 332, $intern_28, Arrays$ArrayList);
_.contains_0 = function contains_3(o){
  return $indexOf_2(this, o) != -1;
}
;
_.get_1 = function get_5(index_0){
  checkCriticalElementIndex(index_0, this.array.length);
  return this.array[index_0];
}
;
_.size_1 = function size_6(){
  return this.array.length;
}
;
_.toArray = function toArray_3(){
  return $toArray_0(this, initUnidimensionalArray(Ljava_lang_Object_2_classLit, $intern_0, 1, this.array.length, 5, 1));
}
;
_.toArray_0 = function toArray_4(out){
  return $toArray_0(this, out);
}
;
var Ljava_util_Arrays$ArrayList_2_classLit = createForClass('java.util', 'Arrays/ArrayList', 88, Ljava_util_AbstractList_2_classLit);
function $clinit_Collections(){
  $clinit_Collections = emptyMethod;
  EMPTY_LIST = new Collections$EmptyList;
}

function hashCode_15(collection){
  $clinit_Collections();
  var e, e$iterator, hashCode;
  hashCode = 0;
  for (e$iterator = collection.iterator(); e$iterator.hasNext_0();) {
    e = e$iterator.next_1();
    hashCode = hashCode + (e != null?hashCode__I__devirtual$(e):0);
    hashCode = hashCode | 0;
  }
  return hashCode;
}

function hashCode_16(list){
  $clinit_Collections();
  var e, e$iterator, hashCode;
  hashCode = 1;
  for (e$iterator = list.iterator(); e$iterator.hasNext_0();) {
    e = e$iterator.next_1();
    hashCode = 31 * hashCode + (e != null?hashCode__I__devirtual$(e):0);
    hashCode = hashCode | 0;
  }
  return hashCode;
}

var EMPTY_LIST;
function Collections$EmptyList(){
}

defineClass(142, 332, $intern_28, Collections$EmptyList);
_.contains_0 = function contains_4(object){
  return false;
}
;
_.get_1 = function get_6(location_0){
  checkCriticalElementIndex(location_0, 0);
  return null;
}
;
_.iterator = function iterator_7(){
  return $clinit_Collections() , $clinit_Collections$EmptyListIterator() , INSTANCE_0;
}
;
_.listIterator = function listIterator_1(){
  return $clinit_Collections() , $clinit_Collections$EmptyListIterator() , INSTANCE_0;
}
;
_.size_1 = function size_7(){
  return 0;
}
;
var Ljava_util_Collections$EmptyList_2_classLit = createForClass('java.util', 'Collections/EmptyList', 142, Ljava_util_AbstractList_2_classLit);
function $clinit_Collections$EmptyListIterator(){
  $clinit_Collections$EmptyListIterator = emptyMethod;
  INSTANCE_0 = new Collections$EmptyListIterator;
}

function Collections$EmptyListIterator(){
}

defineClass(143, 1, {}, Collections$EmptyListIterator);
_.add_2 = function add_9(o){
  throw toJs(new UnsupportedOperationException);
}
;
_.hasNext_0 = function hasNext_4(){
  return false;
}
;
_.hasPrevious = function hasPrevious_0(){
  return false;
}
;
_.next_1 = function next_5(){
  throw toJs(new NoSuchElementException);
}
;
_.previous = function previous_1(){
  throw toJs(new NoSuchElementException);
}
;
_.remove_1 = function remove_17(){
  throw toJs(new IllegalStateException);
}
;
var INSTANCE_0;
var Ljava_util_Collections$EmptyListIterator_2_classLit = createForClass('java.util', 'Collections/EmptyListIterator', 143, Ljava_lang_Object_2_classLit);
function $contains_0(this$static, o){
  return $advanceToFind(this$static.coll, o, false);
}

defineClass(144, 1, {});
_.add_0 = function add_10(o){
  throw toJs(new UnsupportedOperationException);
}
;
_.isEmpty = function isEmpty_2(){
  return this.coll.size_0 == 0;
}
;
_.iterator = function iterator_8(){
  return new Collections$UnmodifiableCollectionIterator($listIterator(this.coll, 0));
}
;
_.remove_2 = function remove_18(o){
  throw toJs(new UnsupportedOperationException);
}
;
_.size_1 = function size_8(){
  return this.coll.size_0;
}
;
_.toString_0 = function toString_25(){
  return $toString_1(this.coll);
}
;
var Ljava_util_Collections$UnmodifiableCollection_2_classLit = createForClass('java.util', 'Collections/UnmodifiableCollection', 144, Ljava_lang_Object_2_classLit);
function $remove_6(){
  throw toJs(new UnsupportedOperationException);
}

function Collections$UnmodifiableCollectionIterator(it){
  this.it = it;
}

defineClass(76, 1, {}, Collections$UnmodifiableCollectionIterator);
_.hasNext_0 = function hasNext_5(){
  return $hasNext(this.it);
}
;
_.next_1 = function next_6(){
  return $next_2(this.it);
}
;
_.remove_1 = function remove_19(){
  $remove_6();
}
;
var Ljava_util_Collections$UnmodifiableCollectionIterator_2_classLit = createForClass('java.util', 'Collections/UnmodifiableCollectionIterator', 76, Ljava_lang_Object_2_classLit);
function Collections$UnmodifiableList(list){
  this.coll = list;
  this.list = list;
}

defineClass(84, 144, {29:1}, Collections$UnmodifiableList);
_.equals_0 = function equals_13(o){
  return $equals_1(this.list, o);
}
;
_.hashCode_0 = function hashCode_17(){
  return hashCode_16(this.list);
}
;
_.isEmpty = function isEmpty_3(){
  return this.list.size_0 == 0;
}
;
_.listIterator = function listIterator_2(){
  return new Collections$UnmodifiableListIterator($listIterator(this.list, 0));
}
;
_.listIterator_0 = function listIterator_3(from){
  return new Collections$UnmodifiableListIterator($listIterator(this.list, from));
}
;
var Ljava_util_Collections$UnmodifiableList_2_classLit = createForClass('java.util', 'Collections/UnmodifiableList', 84, Ljava_util_Collections$UnmodifiableCollection_2_classLit);
function Collections$UnmodifiableListIterator(lit){
  Collections$UnmodifiableCollectionIterator.call(this, lit);
  this.lit = lit;
}

defineClass(105, 76, {}, Collections$UnmodifiableListIterator);
_.remove_1 = function remove_20(){
  $remove_6();
}
;
_.add_2 = function add_11(o){
  throw toJs(new UnsupportedOperationException);
}
;
_.hasPrevious = function hasPrevious_1(){
  return $hasPrevious(this.lit);
}
;
_.previous = function previous_2(){
  return $previous(this.lit);
}
;
var Ljava_util_Collections$UnmodifiableListIterator_2_classLit = createForClass('java.util', 'Collections/UnmodifiableListIterator', 105, Ljava_util_Collections$UnmodifiableCollectionIterator_2_classLit);
function checkStructuralChange(host, iterator){
  if (iterator.$modCount != host.$modCount) {
    throw toJs(new ConcurrentModificationException);
  }
}

function recordLastKnownStructure(host, iterator){
  iterator.$modCount = host.$modCount;
}

function structureChanged(host){
  var modCount, modCountable;
  modCountable = host;
  modCount = modCountable.$modCount | 0;
  modCountable.$modCount = modCount + 1;
}

function ConcurrentModificationException(){
  RuntimeException.call(this);
}

defineClass(245, 18, $intern_1, ConcurrentModificationException);
var Ljava_util_ConcurrentModificationException_2_classLit = createForClass('java.util', 'ConcurrentModificationException', 245, Ljava_lang_RuntimeException_2_classLit);
function $fixDaylightSavings(this$static, requestedHours){
  var badHours, copy, day, newTime, originalTimeInMillis, timeDiff, timeDiffHours, timeDiffMinutes;
  requestedHours %= 24;
  if (this$static.jsdate.getHours() != requestedHours) {
    copy = new $wnd.Date(this$static.jsdate.getTime());
    copy.setDate(copy.getDate() + 1);
    timeDiff = this$static.jsdate.getTimezoneOffset() - copy.getTimezoneOffset();
    if (timeDiff > 0) {
      timeDiffHours = timeDiff / 60 | 0;
      timeDiffMinutes = timeDiff % 60;
      day = this$static.jsdate.getDate();
      badHours = this$static.jsdate.getHours();
      badHours + timeDiffHours >= 24 && ++day;
      newTime = new $wnd.Date(this$static.jsdate.getFullYear(), this$static.jsdate.getMonth(), day, requestedHours + timeDiffHours, this$static.jsdate.getMinutes() + timeDiffMinutes, this$static.jsdate.getSeconds(), this$static.jsdate.getMilliseconds());
      this$static.jsdate.setTime(newTime.getTime());
    }
  }
  originalTimeInMillis = this$static.jsdate.getTime();
  this$static.jsdate.setTime(originalTimeInMillis + $intern_29);
  this$static.jsdate.getHours() != requestedHours && this$static.jsdate.setTime(originalTimeInMillis);
}

function $setDate(this$static, date){
  var hours;
  hours = this$static.jsdate.getHours();
  this$static.jsdate.setDate(date);
  $fixDaylightSavings(this$static, hours);
}

function Date_0(year, month, date){
  this.jsdate = new $wnd.Date;
  this.jsdate.setFullYear(year + 1900, month, date);
  this.jsdate.setHours(0, 0, 0, 0);
  $fixDaylightSavings(this, 0);
}

function Date_1(date){
  this.jsdate = new $wnd.Date(toDouble_0(date));
}

function padTwo(number){
  return number < 10?'0' + number:'' + number;
}

defineClass(13, 1, {3:1, 6:1, 13:1}, Date_0, Date_1);
_.equals_0 = function equals_14(obj){
  return instanceOf(obj, 13) && eq(fromDouble_0(this.jsdate.getTime()), fromDouble_0(castTo(obj, 13).jsdate.getTime()));
}
;
_.hashCode_0 = function hashCode_18(){
  var time;
  time = fromDouble_0(this.jsdate.getTime());
  return toInt_0(xor_0(time, createLongEmul(shru(isSmallLong0(time)?toBigLong(time):time, 32))));
}
;
_.toString_0 = function toString_26(){
  var hourOffset, minuteOffset, offset;
  offset = -this.jsdate.getTimezoneOffset();
  hourOffset = (offset >= 0?'+':'') + (offset / 60 | 0);
  minuteOffset = padTwo($wnd.Math.abs(offset) % 60);
  return ($clinit_Date$StringData() , DAYS)[this.jsdate.getDay()] + ' ' + MONTHS[this.jsdate.getMonth()] + ' ' + padTwo(this.jsdate.getDate()) + ' ' + padTwo(this.jsdate.getHours()) + ':' + padTwo(this.jsdate.getMinutes()) + ':' + padTwo(this.jsdate.getSeconds()) + ' GMT' + hourOffset + minuteOffset + ' ' + this.jsdate.getFullYear();
}
;
var Ljava_util_Date_2_classLit = createForClass('java.util', 'Date', 13, Ljava_lang_Object_2_classLit);
function $clinit_Date$StringData(){
  $clinit_Date$StringData = emptyMethod;
  DAYS = stampJavaTypeInfo(getClassLiteralForArray(Ljava_lang_String_2_classLit, 1), $intern_0, 2, 6, ['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat']);
  MONTHS = stampJavaTypeInfo(getClassLiteralForArray(Ljava_lang_String_2_classLit, 1), $intern_0, 2, 6, ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']);
}

var DAYS, MONTHS;
function HashMap(){
  $reset(this);
}

defineClass(21, 136, {3:1, 35:1}, HashMap);
_.equals_1 = function equals_15(value1, value2){
  return maskUndefined(value1) === maskUndefined(value2) || value1 != null && equals_Ljava_lang_Object__Z__devirtual$(value1, value2);
}
;
_.getHashCode = function getHashCode(key){
  var hashCode;
  hashCode = hashCode__I__devirtual$(key);
  return hashCode | 0;
}
;
var Ljava_util_HashMap_2_classLit = createForClass('java.util', 'HashMap', 21, Ljava_util_AbstractHashMap_2_classLit);
function $add_3(this$static, o){
  var old;
  old = $put_0(this$static.map_0, o, this$static);
  return old == null;
}

function $contains_1(this$static, o){
  return $containsKey(this$static.map_0, o);
}

function $remove_7(this$static, o){
  return $remove_2(this$static.map_0, o) != null;
}

function HashSet(){
  this.map_0 = new HashMap;
}

defineClass(54, 331, {3:1, 74:1}, HashSet);
_.add_0 = function add_12(o){
  return $add_3(this, o);
}
;
_.contains_0 = function contains_5(o){
  return $contains_1(this, o);
}
;
_.isEmpty = function isEmpty_4(){
  return $size(this.map_0) == 0;
}
;
_.iterator = function iterator_9(){
  var outerIter;
  return outerIter = (new AbstractMap$1(this.map_0)).this$01.entrySet().iterator() , new AbstractMap$1$1(outerIter);
}
;
_.remove_2 = function remove_21(o){
  return $remove_7(this, o);
}
;
_.size_1 = function size_9(){
  return $size(this.map_0);
}
;
var Ljava_util_HashSet_2_classLit = createForClass('java.util', 'HashSet', 54, Ljava_util_AbstractSet_2_classLit);
function $findEntryInChain(this$static, key, chain){
  var entry, entry$index, entry$max;
  for (entry$index = 0 , entry$max = chain.length; entry$index < entry$max; ++entry$index) {
    entry = chain[entry$index];
    if (this$static.host_0.equals_1(key, entry.getKey())) {
      return entry;
    }
  }
  return null;
}

function $getChainOrEmpty(this$static, hashCode){
  var chain;
  chain = this$static.backingMap.get(hashCode);
  return chain == null?new Array:chain;
}

function $getEntry(this$static, key){
  return $findEntryInChain(this$static, key, $getChainOrEmpty(this$static, key == null?0:this$static.host_0.getHashCode(key)));
}

function $put_1(this$static, key, value_0){
  var chain, chain0, entry, hashCode;
  hashCode = key == null?0:this$static.host_0.getHashCode(key);
  chain0 = (chain = this$static.backingMap.get(hashCode) , chain == null?new Array:chain);
  if (chain0.length == 0) {
    this$static.backingMap.set(hashCode, chain0);
  }
   else {
    entry = $findEntryInChain(this$static, key, chain0);
    if (entry) {
      return entry.setValue(value_0);
    }
  }
  setCheck(chain0, chain0.length, new AbstractMap$SimpleEntry(key, value_0));
  ++this$static.size_0;
  structureChanged(this$static.host_0);
  return null;
}

function $remove_8(this$static, key){
  var chain, chain0, entry, hashCode, i_0;
  hashCode = key == null?0:this$static.host_0.getHashCode(key);
  chain0 = (chain = this$static.backingMap.get(hashCode) , chain == null?new Array:chain);
  for (i_0 = 0; i_0 < chain0.length; i_0++) {
    entry = chain0[i_0];
    if (this$static.host_0.equals_1(key, entry.getKey())) {
      if (chain0.length == 1) {
        chain0.length = 0;
        $delete(this$static.backingMap, hashCode);
      }
       else {
        chain0.splice(i_0, 1);
      }
      --this$static.size_0;
      structureChanged(this$static.host_0);
      return entry.getValue();
    }
  }
  return null;
}

function InternalHashCodeMap(host){
  this.backingMap = newJsMap();
  this.host_0 = host;
}

defineClass(146, 1, {}, InternalHashCodeMap);
_.iterator = function iterator_10(){
  return new InternalHashCodeMap$1(this);
}
;
_.size_0 = 0;
var Ljava_util_InternalHashCodeMap_2_classLit = createForClass('java.util', 'InternalHashCodeMap', 146, Ljava_lang_Object_2_classLit);
function InternalHashCodeMap$1(this$0){
  this.this$01 = this$0;
  this.chains = this.this$01.backingMap.entries();
  this.chain = new Array;
}

defineClass(107, 1, {}, InternalHashCodeMap$1);
_.next_1 = function next_7(){
  return this.lastEntry = this.chain[this.itemIndex++] , this.lastEntry;
}
;
_.hasNext_0 = function hasNext_6(){
  var current;
  if (this.itemIndex < this.chain.length) {
    return true;
  }
  current = this.chains.next();
  if (!current.done) {
    this.chain = current.value[1];
    this.itemIndex = 0;
    return true;
  }
  return false;
}
;
_.remove_1 = function remove_22(){
  $remove_8(this.this$01, this.lastEntry.getKey());
  this.itemIndex != 0 && --this.itemIndex;
}
;
_.itemIndex = 0;
_.lastEntry = null;
var Ljava_util_InternalHashCodeMap$1_2_classLit = createForClass('java.util', 'InternalHashCodeMap/1', 107, Ljava_lang_Object_2_classLit);
function $delete(this$static, key){
  var fn;
  fn = this$static['delete'];
  fn.call(this$static, key);
}

function $delete_0(this$static, key){
  var fn;
  fn = this$static['delete'];
  fn.call(this$static, key);
}

function $clinit_InternalJsMapFactory(){
  $clinit_InternalJsMapFactory = emptyMethod;
  jsMapCtor = getJsMapConstructor();
}

function canHandleObjectCreateAndProto(){
  if (!Object.create || !Object.getOwnPropertyNames) {
    return false;
  }
  var protoField = '__proto__';
  var map_0 = Object.create(null);
  if (map_0[protoField] !== undefined) {
    return false;
  }
  var keys_0 = Object.getOwnPropertyNames(map_0);
  if (keys_0.length != 0) {
    return false;
  }
  map_0[protoField] = 42;
  if (map_0[protoField] !== 42) {
    return false;
  }
  if (Object.getOwnPropertyNames(map_0).length == 0) {
    return false;
  }
  return true;
}

function getJsMapConstructor(){
  function isCorrectIterationProtocol(){
    try {
      return (new Map).entries().next().done;
    }
     catch (e) {
      return false;
    }
  }

  if (typeof Map === 'function' && Map.prototype.entries && isCorrectIterationProtocol()) {
    return Map;
  }
   else {
    return getJsMapPolyFill();
  }
}

function getJsMapPolyFill(){
  function Stringmap(){
    this.obj = this.createObject();
  }

  ;
  Stringmap.prototype.createObject = function(key){
    return Object.create(null);
  }
  ;
  Stringmap.prototype.get = function(key){
    return this.obj[key];
  }
  ;
  Stringmap.prototype.set = function(key, value_0){
    this.obj[key] = value_0;
  }
  ;
  Stringmap.prototype['delete'] = function(key){
    delete this.obj[key];
  }
  ;
  Stringmap.prototype.keys = function(){
    return Object.getOwnPropertyNames(this.obj);
  }
  ;
  Stringmap.prototype.entries = function(){
    var keys_0 = this.keys();
    var map_0 = this;
    var nextIndex = 0;
    return {next:function(){
      if (nextIndex >= keys_0.length)
        return {done:true};
      var key = keys_0[nextIndex++];
      return {value:[key, map_0.get(key)], done:false};
    }
    };
  }
  ;
  if (!canHandleObjectCreateAndProto()) {
    Stringmap.prototype.createObject = function(){
      return {};
    }
    ;
    Stringmap.prototype.get = function(key){
      return this.obj[':' + key];
    }
    ;
    Stringmap.prototype.set = function(key, value_0){
      this.obj[':' + key] = value_0;
    }
    ;
    Stringmap.prototype['delete'] = function(key){
      delete this.obj[':' + key];
    }
    ;
    Stringmap.prototype.keys = function(){
      var result = [];
      for (var key in this.obj) {
        key.charCodeAt(0) == 58 && result.push(key.substring(1));
      }
      return result;
    }
    ;
  }
  return Stringmap;
}

function newJsMap(){
  $clinit_InternalJsMapFactory();
  return new jsMapCtor;
}

var jsMapCtor;
function $contains_2(this$static, key){
  return !(this$static.backingMap.get(key) === undefined);
}

function $get_5(this$static, key){
  return this$static.backingMap.get(key);
}

function $put_2(this$static, key, value_0){
  var oldValue;
  oldValue = this$static.backingMap.get(key);
  this$static.backingMap.set(key, value_0 === undefined?null:value_0);
  if (oldValue === undefined) {
    ++this$static.size_0;
    structureChanged(this$static.host_0);
  }
   else {
    ++this$static.valueMod;
  }
  return oldValue;
}

function $remove_9(this$static, key){
  var value_0;
  value_0 = this$static.backingMap.get(key);
  if (value_0 === undefined) {
    ++this$static.valueMod;
  }
   else {
    $delete_0(this$static.backingMap, key);
    --this$static.size_0;
    structureChanged(this$static.host_0);
  }
  return value_0;
}

function InternalStringMap(host){
  this.backingMap = newJsMap();
  this.host_0 = host;
}

defineClass(147, 1, {}, InternalStringMap);
_.iterator = function iterator_11(){
  return new InternalStringMap$1(this);
}
;
_.size_0 = 0;
_.valueMod = 0;
var Ljava_util_InternalStringMap_2_classLit = createForClass('java.util', 'InternalStringMap', 147, Ljava_lang_Object_2_classLit);
function InternalStringMap$1(this$0){
  this.this$01 = this$0;
  this.entries_0 = this.this$01.backingMap.entries();
  this.current = this.entries_0.next();
}

defineClass(108, 1, {}, InternalStringMap$1);
_.next_1 = function next_8(){
  return this.last = this.current , this.current = this.entries_0.next() , new InternalStringMap$2(this.this$01, this.last, this.this$01.valueMod);
}
;
_.hasNext_0 = function hasNext_7(){
  return !this.current.done;
}
;
_.remove_1 = function remove_23(){
  $remove_9(this.this$01, this.last.value[0]);
}
;
var Ljava_util_InternalStringMap$1_2_classLit = createForClass('java.util', 'InternalStringMap/1', 108, Ljava_lang_Object_2_classLit);
function $getValue(this$static){
  if (this$static.this$01.valueMod != this$static.val$lastValueMod3) {
    return $get_5(this$static.this$01, this$static.val$entry2.value[0]);
  }
  return this$static.val$entry2.value[1];
}

function InternalStringMap$2(this$0, val$entry, val$lastValueMod){
  this.this$01 = this$0;
  this.val$entry2 = val$entry;
  this.val$lastValueMod3 = val$lastValueMod;
}

defineClass(148, 333, $intern_27, InternalStringMap$2);
_.getKey = function getKey_0(){
  return this.val$entry2.value[0];
}
;
_.getValue = function getValue_0(){
  return $getValue(this);
}
;
_.setValue = function setValue_0(object){
  return $put_2(this.this$01, this.val$entry2.value[0], object);
}
;
_.val$lastValueMod3 = 0;
var Ljava_util_InternalStringMap$2_2_classLit = createForClass('java.util', 'InternalStringMap/2', 148, Ljava_util_AbstractMapEntry_2_classLit);
function $clear(this$static){
  $reset(this$static.map_0);
  this$static.head_0.prev = this$static.head_0;
  this$static.head_0.next_0 = this$static.head_0;
}

function $get_6(this$static, key){
  var entry;
  entry = castTo($get_2(this$static.map_0, key), 55);
  if (entry) {
    $recordAccess(this$static, entry);
    return entry.value_0;
  }
  return null;
}

function $put_3(this$static, key, value_0){
  var newEntry, old, oldValue;
  old = castTo($get_2(this$static.map_0, key), 55);
  if (!old) {
    newEntry = new LinkedHashMap$ChainEntry_0(this$static, key, value_0);
    $put_0(this$static.map_0, key, newEntry);
    $addToEnd(newEntry);
    return null;
  }
   else {
    oldValue = $setValue(old, value_0);
    $recordAccess(this$static, old);
    return oldValue;
  }
}

function $recordAccess(this$static, entry){
  if (this$static.accessOrder) {
    $remove_11(entry);
    $addToEnd(entry);
  }
}

function $remove_10(this$static, key){
  var entry;
  entry = castTo($remove_2(this$static.map_0, key), 55);
  if (entry) {
    $remove_11(entry);
    return entry.value_0;
  }
  return null;
}

function LinkedHashMap(){
  $reset(this);
  this.head_0 = new LinkedHashMap$ChainEntry(this);
  this.map_0 = new HashMap;
  this.head_0.prev = this.head_0;
  this.head_0.next_0 = this.head_0;
}

defineClass(121, 21, {3:1, 35:1}, LinkedHashMap);
_.containsKey = function containsKey_1(key){
  return $containsKey(this.map_0, key);
}
;
_.entrySet = function entrySet_0(){
  return new LinkedHashMap$EntrySet(this);
}
;
_.get_0 = function get_7(key){
  return $get_6(this, key);
}
;
_.put = function put_1(key, value_0){
  return $put_3(this, key, value_0);
}
;
_.remove_3 = function remove_24(key){
  return $remove_10(this, key);
}
;
_.size_1 = function size_10(){
  return $size(this.map_0);
}
;
_.accessOrder = false;
var Ljava_util_LinkedHashMap_2_classLit = createForClass('java.util', 'LinkedHashMap', 121, Ljava_util_HashMap_2_classLit);
function $addToEnd(this$static){
  var tail;
  tail = this$static.this$01.head_0.prev;
  this$static.prev = tail;
  this$static.next_0 = this$static.this$01.head_0;
  tail.next_0 = this$static.this$01.head_0.prev = this$static;
}

function $remove_11(this$static){
  this$static.next_0.prev = this$static.prev;
  this$static.prev.next_0 = this$static.next_0;
  this$static.next_0 = this$static.prev = null;
}

function LinkedHashMap$ChainEntry(this$0){
  LinkedHashMap$ChainEntry_0.call(this, this$0, null, null);
}

function LinkedHashMap$ChainEntry_0(this$0, key, value_0){
  this.this$01 = this$0;
  AbstractMap$SimpleEntry.call(this, key, value_0);
}

defineClass(55, 83, {55:1, 16:1}, LinkedHashMap$ChainEntry, LinkedHashMap$ChainEntry_0);
var Ljava_util_LinkedHashMap$ChainEntry_2_classLit = createForClass('java.util', 'LinkedHashMap/ChainEntry', 55, Ljava_util_AbstractMap$SimpleEntry_2_classLit);
function $contains_3(this$static, o){
  if (instanceOf(o, 16)) {
    return $containsEntry(this$static.this$01, castTo(o, 16));
  }
  return false;
}

function LinkedHashMap$EntrySet(this$0){
  this.this$01 = this$0;
}

defineClass(94, 331, $intern_26, LinkedHashMap$EntrySet);
_.contains_0 = function contains_6(o){
  return $contains_3(this, o);
}
;
_.iterator = function iterator_12(){
  return new LinkedHashMap$EntrySet$EntryIterator(this);
}
;
_.remove_2 = function remove_25(entry){
  var key;
  if ($contains_3(this, entry)) {
    key = castTo(entry, 16).getKey();
    $remove_10(this.this$01, key);
    return true;
  }
  return false;
}
;
_.size_1 = function size_11(){
  return $size(this.this$01.map_0);
}
;
var Ljava_util_LinkedHashMap$EntrySet_2_classLit = createForClass('java.util', 'LinkedHashMap/EntrySet', 94, Ljava_util_AbstractSet_2_classLit);
function $next_1(this$static){
  checkStructuralChange(this$static.this$11.this$01.map_0, this$static);
  checkCriticalElement(this$static.next_0 != this$static.this$11.this$01.head_0);
  this$static.last = this$static.next_0;
  this$static.next_0 = this$static.next_0.next_0;
  return this$static.last;
}

function LinkedHashMap$EntrySet$EntryIterator(this$1){
  this.this$11 = this$1;
  this.next_0 = this$1.this$01.head_0.next_0;
  recordLastKnownStructure(this$1.this$01.map_0, this);
}

defineClass(95, 1, {}, LinkedHashMap$EntrySet$EntryIterator);
_.next_1 = function next_9(){
  return $next_1(this);
}
;
_.hasNext_0 = function hasNext_8(){
  return this.next_0 != this.this$11.this$01.head_0;
}
;
_.remove_1 = function remove_26(){
  checkCriticalState(!!this.last);
  checkStructuralChange(this.this$11.this$01.map_0, this);
  $remove_11(this.last);
  $remove_2(this.this$11.this$01.map_0, this.last.key);
  recordLastKnownStructure(this.this$11.this$01.map_0, this);
  this.last = null;
}
;
var Ljava_util_LinkedHashMap$EntrySet$EntryIterator_2_classLit = createForClass('java.util', 'LinkedHashMap/EntrySet/EntryIterator', 95, Ljava_lang_Object_2_classLit);
function $add_4(this$static, o){
  $addNode(this$static, o, this$static.tail.prev, this$static.tail);
  return true;
}

function $addNode(this$static, o, prev, next){
  var node;
  node = new LinkedList$Node;
  node.value_0 = o;
  node.prev = prev;
  node.next_0 = next;
  next.prev = prev.next_0 = node;
  ++this$static.size_0;
}

function $listIterator(this$static, index_0){
  var i_0, node;
  checkCriticalPositionIndex(index_0, this$static.size_0);
  if (index_0 >= this$static.size_0 >> 1) {
    node = this$static.tail;
    for (i_0 = this$static.size_0; i_0 > index_0; --i_0) {
      node = node.prev;
    }
  }
   else {
    node = this$static.header.next_0;
    for (i_0 = 0; i_0 < index_0; ++i_0) {
      node = node.next_0;
    }
  }
  return new LinkedList$ListIteratorImpl(this$static, index_0, node);
}

function $removeNode(this$static, node){
  var oldValue;
  oldValue = node.value_0;
  node.next_0.prev = node.prev;
  node.prev.next_0 = node.next_0;
  node.next_0 = node.prev = null;
  node.value_0 = null;
  --this$static.size_0;
  return oldValue;
}

function LinkedList(){
  this.header = new LinkedList$Node;
  this.tail = new LinkedList$Node;
  this.header.next_0 = this.tail;
  this.tail.prev = this.header;
  this.header.prev = this.tail.next_0 = null;
  this.size_0 = 0;
}

defineClass(247, 339, $intern_28, LinkedList);
_.add_0 = function add_13(o){
  return $add_4(this, o);
}
;
_.listIterator_0 = function listIterator_4(index_0){
  return $listIterator(this, index_0);
}
;
_.size_1 = function size_12(){
  return this.size_0;
}
;
_.size_0 = 0;
var Ljava_util_LinkedList_2_classLit = createForClass('java.util', 'LinkedList', 247, Ljava_util_AbstractSequentialList_2_classLit);
function $hasNext(this$static){
  return this$static.currentNode != this$static.this$01.tail;
}

function $hasPrevious(this$static){
  return this$static.currentNode.prev != this$static.this$01.header;
}

function $next_2(this$static){
  checkCriticalElement(this$static.currentNode != this$static.this$01.tail);
  this$static.lastNode = this$static.currentNode;
  this$static.currentNode = this$static.currentNode.next_0;
  ++this$static.currentIndex;
  return this$static.lastNode.value_0;
}

function $previous(this$static){
  checkCriticalElement(this$static.currentNode.prev != this$static.this$01.header);
  this$static.lastNode = this$static.currentNode = this$static.currentNode.prev;
  --this$static.currentIndex;
  return this$static.lastNode.value_0;
}

function LinkedList$ListIteratorImpl(this$0, index_0, startNode){
  this.this$01 = this$0;
  this.currentNode = startNode;
  this.currentIndex = index_0;
}

defineClass(248, 1, {}, LinkedList$ListIteratorImpl);
_.add_2 = function add_14(o){
  $addNode(this.this$01, o, this.currentNode.prev, this.currentNode);
  ++this.currentIndex;
  this.lastNode = null;
}
;
_.hasNext_0 = function hasNext_9(){
  return $hasNext(this);
}
;
_.hasPrevious = function hasPrevious_2(){
  return $hasPrevious(this);
}
;
_.next_1 = function next_10(){
  return $next_2(this);
}
;
_.previous = function previous_3(){
  return $previous(this);
}
;
_.remove_1 = function remove_27(){
  var nextNode;
  checkCriticalState(!!this.lastNode);
  nextNode = this.lastNode.next_0;
  $removeNode(this.this$01, this.lastNode);
  this.currentNode == this.lastNode?(this.currentNode = nextNode):--this.currentIndex;
  this.lastNode = null;
}
;
_.currentIndex = 0;
_.lastNode = null;
var Ljava_util_LinkedList$ListIteratorImpl_2_classLit = createForClass('java.util', 'LinkedList/ListIteratorImpl', 248, Ljava_lang_Object_2_classLit);
function LinkedList$Node(){
}

defineClass(90, 1, {}, LinkedList$Node);
var Ljava_util_LinkedList$Node_2_classLit = createForClass('java.util', 'LinkedList/Node', 90, Ljava_lang_Object_2_classLit);
function NoSuchElementException(){
  RuntimeException.call(this);
}

defineClass(50, 18, {3:1, 10:1, 9:1, 50:1}, NoSuchElementException);
var Ljava_util_NoSuchElementException_2_classLit = createForClass('java.util', 'NoSuchElementException', 50, Ljava_lang_RuntimeException_2_classLit);
function equals_16(a, b){
  return maskUndefined(a) === maskUndefined(b) || a != null && equals_Ljava_lang_Object__Z__devirtual$(a, b);
}

function hashCode_19(o){
  return o != null?hashCode__I__devirtual$(o):0;
}

function $add_5(this$static, newElement){
  !this$static.builder?(this$static.builder = new StringBuilder_0(this$static.prefix)):$append_2(this$static.builder, this$static.delimiter);
  $append_0(this$static.builder, newElement);
  return this$static;
}

function StringJoiner(prefix, suffix){
  this.delimiter = ', ';
  this.prefix = prefix;
  this.suffix = suffix;
  this.emptyValue = this.prefix + ('' + this.suffix);
}

defineClass(100, 1, {}, StringJoiner);
_.toString_0 = function toString_27(){
  return !this.builder?this.emptyValue:this.suffix.length == 0?this.builder.string:this.builder.string + ('' + this.suffix);
}
;
var Ljava_util_StringJoiner_2_classLit = createForClass('java.util', 'StringJoiner', 100, Ljava_lang_Object_2_classLit);
function clone(array, toIndex){
  var result;
  result = array.slice(0, toIndex);
  return stampJavaTypeInfo_0(result, array);
}

function copy_0(src_0, srcOfs, dest, destOfs, len){
  var batchEnd, batchStart, destArray, end, spliceArgs;
  if (src_0 === dest) {
    src_0 = src_0.slice(srcOfs, srcOfs + len);
    srcOfs = 0;
  }
  destArray = dest;
  for (batchStart = srcOfs , end = srcOfs + len; batchStart < end;) {
    batchEnd = $wnd.Math.min(batchStart + 10000, end);
    len = batchEnd - batchStart;
    spliceArgs = src_0.slice(batchStart, batchEnd);
    spliceArgs.splice(0, 0, destOfs, 0);
    Array.prototype.splice.apply(destArray, spliceArgs);
    batchStart = batchEnd;
    destOfs += len;
  }
}

function insertTo(array, index_0, value_0){
  array.splice(index_0, 0, value_0);
}

function insertTo_0(array, index_0, values){
  copy_0(values, 0, array, index_0, values.length);
}

function removeFrom(array, index_0){
  array.splice(index_0, 1);
}

defineClass(398, 1, {});
function stampJavaTypeInfo_1(array, referenceType){
  return stampJavaTypeInfo_0(array, referenceType);
}

defineClass(300, 1, {});
var Ljavaemul_internal_ConsoleLogger_2_classLit = createForClass('javaemul.internal', 'ConsoleLogger', 300, Ljava_lang_Object_2_classLit);
function checkCriticalArgument(expression){
  if (!expression) {
    throw toJs(new IllegalArgumentException('Exception can not suppress itself.'));
  }
}

function checkCriticalArgument_0(expression, errorMessageArgs){
  if (!expression) {
    throw toJs(new IllegalArgumentException(format_0('Enum constant undefined: %s', errorMessageArgs)));
  }
}

function checkCriticalArrayType(expression){
  if (!expression) {
    throw toJs(new ArrayStoreException);
  }
}

function checkCriticalElement(expression){
  if (!expression) {
    throw toJs(new NoSuchElementException);
  }
}

function checkCriticalElementIndex(index_0, size_0){
  if (index_0 < 0 || index_0 >= size_0) {
    throw toJs(new IndexOutOfBoundsException_0('Index: ' + index_0 + ', Size: ' + size_0));
  }
}

function checkCriticalNotNull(reference){
  if (reference == null) {
    throw toJs(new NullPointerException);
  }
  return reference;
}

function checkCriticalNotNull_0(reference){
  if (!reference) {
    throw toJs(new NullPointerException_0('Cannot suppress a null exception.'));
  }
}

function checkCriticalPositionIndex(index_0, size_0){
  if (index_0 < 0 || index_0 > size_0) {
    throw toJs(new IndexOutOfBoundsException_0('Index: ' + index_0 + ', Size: ' + size_0));
  }
}

function checkCriticalState(expression){
  if (!expression) {
    throw toJs(new IllegalStateException);
  }
}

function checkCriticalStringBounds(end, length_0){
  if (end > length_0 || end < 0) {
    throw toJs(new StringIndexOutOfBoundsException('fromIndex: 0, toIndex: ' + end + ', length: ' + length_0));
  }
}

function checkCriticalStringElementIndex(index_0, size_0){
  if (index_0 < 0 || index_0 >= size_0) {
    throw toJs(new StringIndexOutOfBoundsException('Index: ' + index_0 + ', Size: ' + size_0));
  }
}

function checkCriticalType(expression){
  if (!expression) {
    throw toJs(new ClassCastException);
  }
}

function format_0(template, args){
  var builder, i_0, placeholderStart, templateStart;
  template = template;
  builder = new StringBuilder;
  templateStart = 0;
  i_0 = 0;
  while (i_0 < args.length) {
    placeholderStart = template.indexOf('%s', templateStart);
    if (placeholderStart == -1) {
      break;
    }
    $append_2(builder, template.substr(templateStart, placeholderStart - templateStart));
    $append_1(builder, args[i_0++]);
    templateStart = placeholderStart + 2;
  }
  $append_2(builder, template.substr(templateStart));
  if (i_0 < args.length) {
    builder.string += ' [';
    $append_1(builder, args[i_0++]);
    while (i_0 < args.length) {
      builder.string += ', ';
      $append_1(builder, args[i_0++]);
    }
    builder.string += ']';
  }
  return builder.string;
}

function setPropertySafe(map_0, key, value_0){
  try {
    map_0[key] = value_0;
  }
   catch (ignored) {
  }
}

defineClass(395, 1, {});
function getHashCode_0(o){
  return o.$H || (o.$H = ++nextHashId);
}

var nextHashId = 0;
function $clinit_StringHashCache(){
  $clinit_StringHashCache = emptyMethod;
  back_0 = new Object_0;
  front = new Object_0;
}

function compute(str){
  var hashCode, i_0, n, nBatch;
  hashCode = 0;
  n = str.length;
  nBatch = n - 4;
  i_0 = 0;
  while (i_0 < nBatch) {
    hashCode = (checkCriticalStringElementIndex(i_0 + 3, str.length) , str.charCodeAt(i_0 + 3) + (checkCriticalStringElementIndex(i_0 + 2, str.length) , 31 * (str.charCodeAt(i_0 + 2) + (checkCriticalStringElementIndex(i_0 + 1, str.length) , 31 * (str.charCodeAt(i_0 + 1) + (checkCriticalStringElementIndex(i_0, str.length) , 31 * (str.charCodeAt(i_0) + 31 * hashCode)))))));
    hashCode = hashCode | 0;
    i_0 += 4;
  }
  while (i_0 < n) {
    hashCode = hashCode * 31 + $charAt(str, i_0++);
  }
  hashCode = hashCode | 0;
  return hashCode;
}

function getHashCode_1(str){
  $clinit_StringHashCache();
  var hashCode, key, result;
  key = ':' + str;
  result = front[key];
  if (result != null) {
    return round_int((checkCriticalNotNull(result) , result));
  }
  result = back_0[key];
  hashCode = result == null?compute(str):round_int((checkCriticalNotNull(result) , result));
  increment();
  front[key] = hashCode;
  return hashCode;
}

function increment(){
  if (count_0 == 256) {
    back_0 = front;
    front = new Object_0;
    count_0 = 0;
  }
  ++count_0;
}

var back_0, count_0 = 0, front;
function ExportAllExporterImpl(){
  new GanttElementExporterImpl;
  new GanttElementStateExporterImpl;
}

defineClass(125, 1, {}, ExportAllExporterImpl);
var Lorg_timepedia_exporter_client_ExportAllExporterImpl_2_classLit = createForClass('org.timepedia.exporter.client', 'ExportAllExporterImpl', 125, Ljava_lang_Object_2_classLit);
var Lorg_timepedia_exporter_client_Exportable_2_classLit = createForInterface('org.timepedia.exporter.client', 'Exportable');
defineClass(328, 1, {});
var Lorg_timepedia_exporter_client_ExporterBaseImpl_2_classLit = createForClass('org.timepedia.exporter.client', 'ExporterBaseImpl', 328, Ljava_lang_Object_2_classLit);
function $addTypeMap(this$static, type_0, exportedConstructor){
  $put_0(this$static.typeMap, type_0, exportedConstructor);
}

function $computeVarArguments(len, args){
  var ret = [];
  for (i = 0; i < len - 1; i++)
    ret.push(args[i]);
  var alen = args.length;
  var p_0 = len - 1;
  if (alen >= len && Object.prototype.toString.apply(args[p_0]) === '[object Array]') {
    ret.push(args[p_0]);
  }
   else {
    var a = [];
    for (i = p_0; i < alen; i++)
      a.push(args[i]);
    ret.push(a);
  }
  return ret;
}

function $declarePackage(qualifiedExportName){
  var i_0, l, o, prefix, superPackages;
  superPackages = $split(qualifiedExportName, '\\.');
  prefix = $wnd;
  i_0 = 0;
  for (l = superPackages.length - 1; i_0 < l; i_0++) {
    if (!$equals_0(superPackages[i_0], 'client')) {
      prefix[superPackages[i_0]] || (prefix[superPackages[i_0]] = {});
      prefix = getProp(prefix, superPackages[i_0]);
    }
  }
  o = getProp(prefix, superPackages[i_0]);
  return o;
}

function $getMaxArity(jsoMap, meth){
  var o = jsoMap[meth];
  var r = 0;
  for (k in o)
    r = Math.max(r, k);
  return r;
}

function $registerDispatchMap(this$static, clazz, dispMap, isStatic){
  var jso, map_0;
  map_0 = isStatic?this$static.staticDispatchMap:this$static.dispatchMap;
  jso = castToJso(getEntryValueOrNull($getEntry(map_0.hashCodeMap, clazz)));
  !jso?(jso = dispMap):mergeJso(jso, dispMap);
  $put_1(map_0.hashCodeMap, clazz, jso);
}

function $runDispatch(this$static, instance, clazz, meth, arguments_0, isStatic, isVarArgs){
  var args, dmap, i_0, l, ret;
  dmap = isStatic?this$static.staticDispatchMap:this$static.dispatchMap;
  if (isVarArgs) {
    for (l = $getMaxArity(castToJso(getEntryValueOrNull($getEntry(dmap.hashCodeMap, clazz))), meth) , i_0 = l; i_0 >= 1; i_0--) {
      args = $computeVarArguments(i_0, arguments_0);
      ret = $runDispatch_0(instance, dmap, clazz, meth, args);
      if (!ret) {
        args = $unshift(instance, args);
        ret = $runDispatch_0(instance, dmap, clazz, meth, args);
      }
      if (ret) {
        return ret;
      }
    }
  }
   else {
    ret = $runDispatch_0(instance, dmap, clazz, meth, arguments_0);
    if (!ret) {
      arguments_0 = $unshift(instance, arguments_0);
      ret = $runDispatch_0(instance, dmap, clazz, meth, arguments_0);
    }
    if (ret) {
      return ret;
    }
  }
  throw toJs(new RuntimeException_0("Can't find exported method for given arguments: " + meth + ':' + arguments_0.length + '\n' + ''));
}

function $runDispatch_0(instance, dmap, clazz, meth, arguments_0){
  var aFunc, i_0, jFunc, l, r, sig, sigs, wFunc;
  sigs = castToJso(getEntryValueOrNull($getEntry(dmap.hashCodeMap, clazz)))[meth][arguments_0.length];
  jFunc = null;
  wFunc = null;
  aFunc = null;
  for (i_0 = 0 , l = !sigs?0:sigs.length; i_0 < l; i_0++) {
    sig = sigs[i_0];
    if ($matches(sig, arguments_0)) {
      jFunc = sig[0];
      wFunc = sig[1];
      aFunc = sig[2];
      break;
    }
  }
  if (!jFunc) {
    return null;
  }
   else {
    arguments_0 = aFunc?wrapArguments(instance, aFunc, arguments_0):arguments_0;
    r = runDispatch(instance, jFunc, wFunc, arguments_0);
    return r;
  }
}

function $setWrapper(this$static, type_0){
  var cons, wrapper;
  if ((getClass__Ljava_lang_Class___devirtual$(type_0).modifiers & 4) != 0) {
    return [];
  }
  cons = $typeConstructor(this$static, getClass__Ljava_lang_Class___devirtual$(type_0));
  wrapper = cons && typeof cons == 'function'?new cons(type_0):type_0;
  type_0['__gwtex_wrap'] = wrapper;
  return wrapper;
}

function $toArrObject(j, ret){
  var i_0, l, o, s;
  s = j;
  l = s.length;
  for (i_0 = 0; i_0 < l; i_0++) {
    o = s[i_0];
    instanceOfJso(o) && getGwtInstance(castToJso(o)) != null && (o = getGwtInstance(castToJso(o)));
    setCheck(ret, i_0, o);
  }
  return ret;
}

function $toArrString(s){
  var i_0, l, ret;
  l = s.length;
  ret = initUnidimensionalArray(Ljava_lang_String_2_classLit, $intern_0, 2, l, 6, 1);
  for (i_0 = 0; i_0 < l; i_0++) {
    ret[i_0] = s[i_0];
  }
  return ret;
}

function $typeConstructor(this$static, type_0){
  var o, sup_0;
  o = $get_2(this$static.typeMap, type_0);
  sup_0 = type_0.superclass;
  if (o == null && !!sup_0 && sup_0 != Ljava_lang_Object_2_classLit) {
    return $typeConstructor(this$static, sup_0);
  }
  return castToJso(o);
}

function $unshift(o, arr){
  var ret = [o];
  for (i = 0; i < arr.length; i++)
    ret.push(arr[i]);
  return ret;
}

function $wrap(this$static, type_0){
  var wrapper;
  if (type_0 == null) {
    return null;
  }
  return wrapper = type_0['__gwtex_wrap'] , !wrapper && (wrapper = $setWrapper(this$static, type_0)) , wrapper;
}

function $wrap_0(type_0){
  var i_0, wrapperArray;
  if (type_0 == null) {
    return null;
  }
  wrapperArray = [];
  for (i_0 = 0; i_0 < type_0.length; i_0++) {
    wrapperArray[i_0] = type_0[i_0];
  }
  return wrapperArray;
}

function ExporterBaseActual(){
  this.typeMap = new HashMap;
  this.dispatchMap = new HashMap;
  this.staticDispatchMap = new HashMap;
}

function getGwtInstance(o){
  return o && o.g?o.g:null;
}

function getProp(jso, key){
  return jso != null?jso[key]:null;
}

function isAssignableToClass(o, clazz){
  var sup_0;
  if (Ljava_lang_Object_2_classLit == clazz) {
    return true;
  }
  if (Lorg_timepedia_exporter_client_Exportable_2_classLit == clazz && instanceOf(o, 301)) {
    return true;
  }
  if (o != null) {
    for (sup_0 = getClass__Ljava_lang_Class___devirtual$(o); !!sup_0 && sup_0 != Ljava_lang_Object_2_classLit; sup_0 = sup_0.superclass) {
      if (sup_0 == clazz) {
        return true;
      }
    }
  }
  return false;
}

function mergeJso(o1, o2){
  for (p in o2) {
    o1[p] = o2[p];
  }
}

function runDispatch(instance, java_0, wrapper, arguments_0){
  var x_0 = java_0.apply(instance, arguments_0);
  return [wrapper?wrapper(x_0):x_0];
}

function wrapArguments(instance, wrapper, arguments_0){
  return wrapper(instance, arguments_0);
}

defineClass(132, 328, {}, ExporterBaseActual);
var Lorg_timepedia_exporter_client_ExporterBaseActual_2_classLit = createForClass('org.timepedia.exporter.client', 'ExporterBaseActual', 132, Lorg_timepedia_exporter_client_ExporterBaseImpl_2_classLit);
function $matches(this$static, arguments_0){
  var argJsType, gwt, i_0, isBoolean, isClass, isNumber, isPrimitive, jsType, l, o;
  for (i_0 = 0 , l = arguments_0.length; i_0 < l; i_0++) {
    jsType = this$static[i_0 + 3];
    argJsType = typeof_0(arguments_0, i_0);
    if ($equals_0(argJsType, jsType)) {
      continue;
    }
    if ($equals_0('string', jsType) && $equals_0('null', argJsType)) {
      continue;
    }
    isNumber = $equals_0('number', argJsType);
    isBoolean = $equals_0('boolean', argJsType);
    if (maskUndefined(Ljava_lang_Object_2_classLit) === maskUndefined(jsType)) {
      isNumber && (arguments_0[i_0] = arguments_0[i_0] , undefined);
      isBoolean && (arguments_0[i_0] = ($clinit_Boolean() , arguments_0[i_0]?true:false) , undefined);
      continue;
    }
    isPrimitive = isNumber || isBoolean;
    isClass = !isPrimitive && jsType != null && getClass__Ljava_lang_Class___devirtual$(jsType) == Ljava_lang_Class_2_classLit;
    if (isClass) {
      o = arguments_0[i_0];
      if (o == null || isAssignableToClass(o, castTo(jsType, 61))) {
        continue;
      }
      if (instanceOfJso(o)) {
        gwt = getGwtInstance(castToJso(o));
        if (gwt != null) {
          if (isAssignableToClass(gwt, castTo(jsType, 61))) {
            arguments_0[i_0] = gwt;
            continue;
          }
        }
      }
    }
    if ($equals_0('object', jsType) && !isNumber && !isBoolean) {
      continue;
    }
    return false;
  }
  return true;
}

function typeof_0(args, i_0){
  var o = args[i_0];
  var t = o == null?'null':typeof o;
  if (t == 'object') {
    return Object.prototype.toString.call(o) == '[object Array]' || typeof o.length == 'number'?'array':t;
  }
  return t;
}

function $clinit_ExporterUtil(){
  $clinit_ExporterUtil = emptyMethod;
  impl = new ExporterBaseActual;
}

function addTypeMap(type_0, exportedConstructor){
  $clinit_ExporterUtil();
  $addTypeMap(impl, type_0, exportedConstructor);
}

function gwtInstance(o){
  $clinit_ExporterUtil();
  var g;
  return o != null && instanceOfJso(o) && (g = getGwtInstance(castToJso(o))) != null?g:o;
}

function isAssignableToInstance(clazz, args){
  $clinit_ExporterUtil();
  var o;
  return o = args && args[0] && (typeof args[0] == 'object' || typeof args[0] == 'function')?args[0]:null , isAssignableToClass(o, clazz);
}

function registerDispatchMap(clazz, dispMap, isStatic){
  $clinit_ExporterUtil();
  $registerDispatchMap(impl, clazz, dispMap, isStatic);
}

function runDispatch_0(instance, clazz, meth, arguments_0, isStatic, isVarArgs){
  $clinit_ExporterUtil();
  return $runDispatch(impl, instance, clazz, meth, arguments_0, isStatic, isVarArgs);
}

function setWrapper(instance, wrapper){
  $clinit_ExporterUtil();
  instance['__gwtex_wrap'] = wrapper;
}

function unshift(o, arr){
  $clinit_ExporterUtil();
  return $unshift(o, arr);
}

function wrap(type_0){
  $clinit_ExporterUtil();
  return $wrap(impl, type_0);
}

function wrap_0(type_0){
  $clinit_ExporterUtil();
  return $wrap_0(type_0);
}

function wrap_1(type_0){
  $clinit_ExporterUtil();
  return type_0;
}

var impl;
function CustomHTMLPanel(tag){
  ComplexPanel.call(this);
  $setElement(this, $createElement($doc, tag));
  return;
}

defineClass(86, 85, $intern_23);
var Lorg_tltv_gantt_client_CustomHTMLPanel_2_classLit = createForClass('org.tltv.gantt.client', 'CustomHTMLPanel', 86, Lcom_google_gwt_user_client_ui_ComplexPanel_2_classLit);
function $ready(this$static, f){
  whenReady_0(f, ($clinit_DOM() , this$static.element));
}

function $setAttributes(this$static, attributes){
  var attr, attr$array, attr$index, attr$max, e;
  for (attr$array = $split($replace($trim(attributes)), '[; ]+') , attr$index = 0 , attr$max = attr$array.length; attr$index < attr$max; ++attr$index) {
    attr = attr$array[attr$index];
    e = $exec(new RegExp(' *([\\w-]+)( *: *)?(.*)? *'), attr);
    e[3] != null?$setAttribute(($clinit_DOM() , this$static.element), e[1], e[3]):($setAttribute(($clinit_DOM() , this$static.element), e[1], '') , undefined);
  }
}

function PolymerWidget(jselement){
  ComplexPanel.call(this);
  $setElement_0(this, ($clinit_DOM() , jselement));
}

function PolymerWidget_0(tag){
  CustomHTMLPanel.call(this, tag);
}

defineClass(65, 86, $intern_23);
var Lorg_tltv_gantt_client_PolymerWidget_2_classLit = createForClass('org.tltv.gantt.client', 'PolymerWidget', 65, Lorg_tltv_gantt_client_CustomHTMLPanel_2_classLit);
function $$init(this$static){
  this$static.step = null;
  this$static.calculatedHeight = 0;
}

function $getBar(this$static){
  return $clinit_DOM() , this$static.element;
}

function $isEmpty(string){
  return string == null || $trim(string).length == 0;
}

function $setGantt(this$static, gantt, localeDataProvider){
  this$static.gantt_0 = gantt;
  this$static.localeDataProvider = localeDataProvider;
}

function $setReadOnly(this$static, readOnly){
  this$static.readOnly = readOnly;
}

function $setStep(this$static, step){
  this$static.step = step;
  $updateStyle(this$static);
  this$static.step.showProgress?$showProgress(this$static):!!this$static.progressElement && !!$getElement(this$static.progressElement.element) && $removeFromParent($getElement(this$static.progressElement.element));
}

function $showProgress(this$static){
  if (!this$static.progressElement) {
    this$static.progressElement = new ProgressBarElement;
    $init_1(this$static.progressElement, this$static.step.progress);
  }
   else {
    $setProgress(this$static.progressElement, this$static.step.progress);
  }
  !!$getParentElement($getElement(this$static.progressElement.element)) || $insertAfter(($clinit_DOM() , this$static.element).root, $getElement(this$static.progressElement.element), this$static.element.$.barLabel);
}

function $updateStyle(this$static){
  if ($isEmpty(this$static.step.styleName)) {
    if (!$isEmpty(this$static.extraStyle)) {
      $removeClassName(($clinit_DOM() , this$static.element), this$static.extraStyle);
      this$static.extraStyle = null;
    }
  }
   else {
    if (!$equals_0(this$static.step.styleName, this$static.extraStyle)) {
      $isEmpty(this$static.extraStyle) || $removeClassName(($clinit_DOM() , this$static.element), this$static.extraStyle);
      $addClassName(($clinit_DOM() , this$static.element), this$static.step.styleName);
    }
    this$static.extraStyle = this$static.step.styleName;
  }
}

function $updateWidth(this$static){
  if (!this$static.gantt_0 || !this$static.step || !($clinit_DOM() , this$static.element).parentNode) {
    return;
  }
  ($clinit_DOM() , this$static.element).style['left'] = '';
  this$static.element.style['width'] = '';
  this$static.element.style['visibility'] = '';
  lt(this$static.step.startDate, 0) || lt(this$static.step.endDate, 0) || lte(this$static.step.endDate, this$static.step.startDate)?$addClassName(this$static.element, 'invalid'):updateStepCustomStyle(this$static.element, this$static.getLeftPositionPercentageStringForDate(valueOf_1(this$static.getStep().startDate), valueOf_1(this$static.getStep().endDate)), this$static.getWidthPercentageStringForDateInterval(valueOf_1(this$static.getStep().startDate), valueOf_1(this$static.getStep().endDate)));
}

function AbstractStepWidget(){
  PolymerWidget_0.call(this, 'gantt-step');
  $$init(this);
  this.doInit();
}

function registerPositionCalculator(elem, f){
  return elem.registerPositionCalculator(f);
}

function registerWidthCalculator(elem, f){
  return elem.registerWidthCalculator(f);
}

function updateStepCustomStyle(e, left, width_0){
  e.updateStyles({'--gantt-step-left':left, '--gantt-step-width':width_0});
}

defineClass(87, 65, $intern_23);
_.doInit = function doInit(){
  setStyleName(($clinit_DOM() , this.element), 'bar');
  $ready(this, makeLambdaFunction(AbstractStepWidget$1.prototype.call_0, AbstractStepWidget$1, [this]));
}
;
_.getLeftPositionPercentageStringForDate = function getLeftPositionPercentageStringForDate(start_0, end){
  return $getLeftPositionPercentageStringForDate(this.gantt_0, start_0.value_0);
}
;
_.getStep = function getStep(){
  return this.step;
}
;
_.getWidthPercentageStringForDateInterval = function getWidthPercentageStringForDateInterval(start_0, end){
  return $getWidthPercentageStringForDateInterval(this.gantt_0, start_0.value_0, end.value_0);
}
;
_.onDetach = function onDetach_0(){
  $onDetach(this);
}
;
_.calculatedHeight = 0;
_.readOnly = false;
var Lorg_tltv_gantt_client_AbstractStepWidget_2_classLit = createForClass('org.tltv.gantt.client', 'AbstractStepWidget', 87, Lorg_tltv_gantt_client_PolymerWidget_2_classLit);
function AbstractStepWidget$1(this$0){
  this.this$01 = this$0;
}

defineClass(368, $wnd.Function, $intern_25, AbstractStepWidget$1);
_.call_0 = function call_3(args){
  registerPositionCalculator($getElement(this.this$01), makeLambdaFunction(AbstractStepWidget$1$1.prototype.call_0, AbstractStepWidget$1$1, [this]));
  registerWidthCalculator($getElement(this.this$01), makeLambdaFunction(AbstractStepWidget$1$2.prototype.call_0, AbstractStepWidget$1$2, [this]));
  return null;
}
;
function $call(this$static, arg){
  var end, start_0;
  if (!this$static.this$11.this$01.gantt_0 || arg == null) {
    return '0px';
  }
  start_0 = valueOf_1(__parseAndValidateLong(valueOf_2(arg[0])));
  end = valueOf_1(__parseAndValidateLong(valueOf_2(arg[1])));
  return this$static.this$11.this$01.getLeftPositionPercentageStringForDate(start_0, end);
}

function AbstractStepWidget$1$1(this$1){
  this.this$11 = this$1;
}

defineClass(369, $wnd.Function, $intern_25, AbstractStepWidget$1$1);
_.call_0 = function call_4(arg){
  return $call(this, castToArray(arg));
}
;
function $call_0(this$static, arg){
  var end, start_0;
  if (!this$static.this$11.this$01.gantt_0 || arg == null) {
    return '0px';
  }
  start_0 = valueOf_1(__parseAndValidateLong(valueOf_2(arg[0])));
  end = valueOf_1(__parseAndValidateLong(valueOf_2(arg[1])));
  return this$static.this$11.this$01.getWidthPercentageStringForDateInterval(start_0, end);
}

function AbstractStepWidget$1$2(this$1){
  this.this$11 = this$1;
}

defineClass(370, $wnd.Function, $intern_25, AbstractStepWidget$1$2);
_.call_0 = function call_5(arg){
  return $call_0(this, castToArray(arg));
}
;
function $calcEndPointX(this$static){
  return this$static.fromLeft?round_int(this$static.width_0):0;
}

function $calcEndPointY(this$static){
  return this$static.fromTop?round_int(this$static.height) - this$static.predecessorHeightCenter:this$static.predecessorHeightCenter;
}

function $calcStartPointX(this$static){
  return this$static.fromLeft?0:round_int(this$static.width_0);
}

function $calcStartPointY(this$static){
  return this$static.fromTop?this$static.predecessorHeightCenter:round_int(this$static.height) - this$static.predecessorHeightCenter;
}

function ArrowPositionData(from, to){
  this.from = from;
  this.to = to;
  this.predecessorHeightCenter = ((from.offsetHeight || 0) | 0) / 2 | 0;
  this.predecessorTop = (from.offsetTop || 0) | 0;
  this.predecesorBottom = ((from.offsetTop || 0) | 0) + ((from.offsetHeight || 0) | 0);
  this.predecessorRight = ((from.offsetLeft || 0) | 0) + ((from.offsetWidth || 0) | 0);
  this.thisBottom = ((to.offsetTop || 0) | 0) + ((to.offsetHeight || 0) | 0);
  this.thisCenter = ((to.offsetHeight || 0) | 0) / 2 | 0;
  this.top_0 = $wnd.Math.min(this.predecessorTop, (to.offsetTop || 0) | 0);
  this.bottom_0 = $wnd.Math.max(this.predecesorBottom, this.thisBottom);
  this.height = this.bottom_0 - this.top_0;
  this.left_0 = $wnd.Math.min(this.predecessorRight, (to.offsetLeft || 0) | 0);
  this.right_0 = $wnd.Math.max(this.predecessorRight, (to.offsetLeft || 0) | 0);
  this.width_0 = this.right_0 - this.left_0;
  this.fromTop = this.predecessorTop <= ((to.offsetTop || 0) | 0);
  this.fromLeft = this.predecessorRight <= ((to.offsetLeft || 0) | 0);
  this.halfWidth = round_int(this.width_0) / 2 | 0;
}

defineClass(93, 1, {}, ArrowPositionData);
_.bottom_0 = 0;
_.fromLeft = false;
_.fromTop = false;
_.halfWidth = 0;
_.height = 0;
_.left_0 = 0;
_.predecesorBottom = 0;
_.predecessorHeightCenter = 0;
_.predecessorRight = 0;
_.predecessorTop = 0;
_.right_0 = 0;
_.thisBottom = 0;
_.thisCenter = 0;
_.top_0 = 0;
_.width_0 = 0;
var Lorg_tltv_gantt_client_ArrowPositionData_2_classLit = createForClass('org.tltv.gantt.client', 'ArrowPositionData', 93, Ljava_lang_Object_2_classLit);
defineClass(345, 1, {});
var Lorg_tltv_gantt_client_BgGridCssElement_2_classLit = createForClass('org.tltv.gantt.client', 'BgGridCssElement', 345, Ljava_lang_Object_2_classLit);
function $appendToContainer(this$static, svgElement){
  $insertFirst(this$static.content_0, svgElement);
}

function $init(this$static, content_0){
  var defs, rect, rect2;
  this$static.content_0 = content_0;
  this$static.svgElement_0 = $doc.createElementNS('http://www.w3.org/2000/svg', 'svg');
  this$static.svgElement_0.setAttributeNS(null, 'width', '110%');
  this$static.svgElement_0.setAttributeNS(null, 'height', '110%');
  this$static.svgElement_0.setAttributeNS(null, 'preserveAspectRatio', 'none');
  defs = $doc.createElementNS('http://www.w3.org/2000/svg', 'defs');
  $appendChild(this$static.svgElement_0, defs);
  this$static.pattern = $doc.createElementNS('http://www.w3.org/2000/svg', 'pattern');
  this$static.pattern.setAttributeNS(null, 'id', 'bggrid-pattern');
  this$static.pattern.setAttributeNS(null, 'patternUnits', 'userSpaceOnUse');
  this$static.pattern.setAttributeNS(null, 'x', '0');
  this$static.pattern.setAttributeNS(null, 'y', '0');
  $appendChild(defs, this$static.pattern);
  rect = $doc.createElementNS('http://www.w3.org/2000/svg', 'rect');
  rect.setAttributeNS(null, 'width', '100%');
  rect.setAttributeNS(null, 'height', '100%');
  rect.setAttributeNS(null, 'fill', '#ffffff');
  $appendChild(this$static.pattern, rect);
  this$static.path_0 = $doc.createElementNS('http://www.w3.org/2000/svg', 'path');
  this$static.path_0.setAttributeNS(null, 'shape-rendering', 'crispEdges');
  this$static.path_0.setAttributeNS(null, 'fill-opacity', '0');
  this$static.path_0.setAttributeNS(null, 'stroke-width', '1');
  this$static.path_0.setAttributeNS(null, 'stroke', '#cccccc');
  $appendChild(this$static.pattern, this$static.path_0);
  rect2 = $doc.createElementNS('http://www.w3.org/2000/svg', 'rect');
  rect2.setAttributeNS(null, 'width', '100%');
  rect2.setAttributeNS(null, 'height', '100%');
  rect2.setAttributeNS(null, 'fill', 'url(#bggrid-pattern)');
  $appendChild(this$static.svgElement_0, rect2);
  this$static.svgElement_0.setAttributeNS(null, 'class', 'bg-grid');
}

function $setBackgroundPosition(this$static, offsetXPx){
  var offX;
  offsetXPx == 0 || offsetXPx == this$static.gridBlockWidthPx?(offX = 0):(offX = -(this$static.gridBlockWidthPx - offsetXPx - 1));
  setAttributeNS(null, this$static.svgElement_0, 'style', 'margin-left: ' + offX + 'px;' + 'margin-top: ' + -this$static.gridBlockHeightPx + 'px;');
}

function $setBackgroundSize(this$static, gridBlockWidthPx, gridBlockHeightPx){
  var x_0, y_0;
  this$static.gridBlockWidthPx = gridBlockWidthPx;
  this$static.gridBlockHeightPx = gridBlockHeightPx;
  setAttributeNS(null, this$static.svgElement_0, 'width', (this$static.content_0.clientWidth | 0) + gridBlockWidthPx + 'px');
  setAttributeNS(null, this$static.svgElement_0, 'height', (this$static.content_0.clientHeight | 0) + gridBlockHeightPx + 'px');
  setAttributeNS(null, this$static.pattern, 'width', '' + gridBlockWidthPx);
  setAttributeNS(null, this$static.pattern, 'height', '' + gridBlockHeightPx);
  y_0 = gridBlockHeightPx - 1;
  y_0 < 0 && (y_0 = 0);
  x_0 = gridBlockWidthPx - 1;
  x_0 < 0 && (x_0 = 0);
  setAttributeNS(null, this$static.path_0, 'd', 'M0,' + y_0 + ' h' + x_0 + ' v0 h0 v-' + gridBlockHeightPx);
}

function $show(this$static){
  $appendToContainer(this$static, this$static.svgElement_0);
}

function BgGridSvgElement(){
}

defineClass(286, 345, {}, BgGridSvgElement);
_.gridBlockHeightPx = 0;
_.gridBlockWidthPx = 0;
var Lorg_tltv_gantt_client_BgGridSvgElement_2_classLit = createForClass('org.tltv.gantt.client', 'BgGridSvgElement', 286, Lorg_tltv_gantt_client_BgGridCssElement_2_classLit);
function $formatDate(this$static, date, formatStr, timeZone){
  var format;
  formatStr = $formatMonthNames(this$static, date, formatStr);
  formatStr = $formatDayNames(this$static, date, formatStr);
  format = ($clinit_DateTimeFormat_0() , getFormat_0(formatStr, $getDateTimeFormatInfo(($clinit_LocaleInfo() , $clinit_LocaleInfo() , instance_1))));
  return timeZone?$format(format, date, timeZone):$format(format, date, this$static.gmt);
}

function $formatDayNames(this$static, date, formatStr){
  var dayName;
  if (formatStr.indexOf('EEEE') != -1) {
    dayName = $getDay(this$static, date.jsdate.getDay());
    if (dayName != null) {
      formatStr = $replaceAll(formatStr, "'([E]{4,})'", dayName);
      formatStr = $replaceAll(formatStr, "([E]{4,})'", "'" + dayName);
      formatStr = $replaceAll(formatStr, "'([E]{4,})", dayName + "'");
      formatStr = $replaceAll(formatStr, '[E]{4,}', "'" + dayName + "'");
    }
  }
  if (formatStr.indexOf('EEE') != -1) {
    dayName = $getShortDay(this$static, date.jsdate.getDay());
    if (dayName != null) {
      formatStr = $replaceAll(formatStr, "'([E]{3,})'", dayName);
      formatStr = $replaceAll(formatStr, "([E]{3,})'", "'" + dayName);
      formatStr = $replaceAll(formatStr, "'([E]{3,})", dayName + "'");
      formatStr = $replaceAll(formatStr, '[E]{3,}', "'" + dayName + "'");
    }
  }
  return formatStr;
}

function $formatMonthNames(this$static, date, formatStr){
  var monthName;
  if (formatStr.indexOf('MMMM') != -1) {
    monthName = $getMonth(this$static, date.jsdate.getMonth());
    if (monthName != null) {
      formatStr = $replaceAll(formatStr, "'([M]{4,})'", monthName);
      formatStr = $replaceAll(formatStr, "([M]{4,})'", "'" + monthName);
      formatStr = $replaceAll(formatStr, "'([M]{4,})", monthName + "'");
      formatStr = $replaceAll(formatStr, '[M]{4,}', "'" + monthName + "'");
    }
  }
  if (formatStr.indexOf('MMM') != -1) {
    monthName = $getShortMonth(this$static, date.jsdate.getMonth());
    if (monthName != null) {
      formatStr = $replaceAll(formatStr, "'([M]{3,})'", monthName);
      formatStr = $replaceAll(formatStr, "([M]{3,})'", "'" + monthName);
      formatStr = $replaceAll(formatStr, "'([M]{3,})", monthName + "'");
      formatStr = $replaceAll(formatStr, '[M]{3,}', "'" + monthName + "'");
    }
  }
  return formatStr;
}

function $getDay(this$static, day){
  try {
    return this$static.localeProvider.localeDayNames[day];
  }
   catch ($e0) {
    $e0 = toJava($e0);
    if (!instanceOf($e0, 10))
      throw toJs($e0);
  }
}

function $getMonth(this$static, month){
  try {
    return this$static.localeProvider.localeMonthNames[month];
  }
   catch ($e0) {
    $e0 = toJava($e0);
    if (!instanceOf($e0, 10))
      throw toJs($e0);
  }
}

function $getShortDay(this$static, day){
  try {
    return this$static.localeProvider.localeShortDayNames[day];
  }
   catch ($e0) {
    $e0 = toJava($e0);
    if (!instanceOf($e0, 10))
      throw toJs($e0);
  }
}

function $getShortMonth(this$static, month){
  try {
    return this$static.localeProvider.localeShortMonthNames[month];
  }
   catch ($e0) {
    $e0 = toJava($e0);
    if (!instanceOf($e0, 10))
      throw toJs($e0);
  }
}

function GanttDateTimeService(localeProvider){
  this.gmt = createTimeZone(0);
  this.localeProvider = localeProvider;
}

defineClass(179, 1, {}, GanttDateTimeService);
var Lorg_tltv_gantt_client_GanttDateTimeService_2_classLit = createForClass('org.tltv.gantt.client', 'GanttDateTimeService', 179, Ljava_lang_Object_2_classLit);
function $createWidget(this$static, jselement){
  if (!this$static.ganttWidget) {
    this$static.ganttWidget = jselement?new GanttWidget_0(jselement):new GanttWidget;
    this$static.internalHandler = new StepHierarchyHandler(this$static.ganttWidget);
  }
  return this$static.ganttWidget;
}

function $doAddStep(this$static, element, s, index_0){
  var predecessor, stepWidget;
  stepWidget = castTo($get_2(this$static.stepsMap, s), 14);
  if (!stepWidget) {
    stepWidget = !element?new StepWidget:new StepWidget_0(element);
    $setGantt(stepWidget, this$static.ganttWidget, this$static.localeDataProvider);
    $put_0(this$static.stepsMap, s, stepWidget);
    $putStringValue(this$static.uidMap, s.uid, s);
  }
  $insertStep(this$static.ganttWidget, index_0, stepWidget);
  $setStep_0(this$static.ganttWidget, index_0, stepWidget, false);
  $setReadOnly_1(stepWidget, this$static.state.readOnly);
  predecessor = s.predecessor;
  predecessor?$setPredecessorStepWidget(stepWidget, castTo($get_2(this$static.stepsMap, predecessor), 14)):(stepWidget.predecessorStepWidget = null);
}

function $doAddSteps(this$static, steps, fromIndex){
  var index_0, s, s$iterator, s$iterator0;
  index_0 = $wnd.Math.max(0, fromIndex);
  for (s$iterator0 = new ArrayList$1(steps); s$iterator0.i < s$iterator0.this$01.array.length;) {
    s = castTo($next_0(s$iterator0), 8);
    $doAddStep(this$static, null, s, index_0++);
  }
  for (s$iterator = new ArrayList$1(steps); s$iterator.i < s$iterator.this$01.array.length;) {
    s = castTo($next_0(s$iterator), 8);
    $doUpdateStep(this$static, s);
  }
}

function $doInit(this$static){
  var info, lastArg;
  $doInit_0(this$static.ganttWidget);
  info = ($clinit_BrowserInfo() , !instance_3 && (instance_3 = new BrowserInfo) , $clinit_BrowserInfo() , instance_3);
  $setBrowserInfo(this$static.ganttWidget, info.browserDetails.isIE, info.browserDetails.isChrome, info.browserDetails.isSafari, info.browserDetails.isWebKit);
  $setAlwaysCalculatePixelWidths(this$static.ganttWidget, info.browserDetails.isSafari || info.browserDetails.isOpera);
  $setTouchSupported(this$static.ganttWidget, info.touchDevice);
  $initWidget(this$static.ganttWidget, this$static.ganttRpc, this$static.localeDataProvider);
  $notifyHeightChanged_0(this$static.ganttWidget, round_int(getBoundingClientRectHeight($getElement(this$static.ganttWidget))));
  $notifyWidthChanged_0((lastArg = this$static.ganttWidget , round_int(getBoundingClientRectWidth($getElement(this$static.ganttWidget))) , lastArg));
}

function $doRemoveSteps(this$static, steps){
  var s, s$iterator, stepWidget;
  for (s$iterator = new ArrayList$1(steps); s$iterator.i < s$iterator.this$01.array.length;) {
    s = castTo($next_0(s$iterator), 8);
    if ($containsKey(this$static.stepsMap, s)) {
      stepWidget = castTo($get_2(this$static.stepsMap, s), 14);
      $requestRemoveStep(this$static.internalHandler, stepWidget);
      $remove_2(this$static.stepsMap, castTo(stepWidget.step, 8));
      $removeStringValue(this$static.uidMap, s.uid);
    }
  }
}

function $doSetState(this$static, newState, newStepsJson){
  $ready_0(this$static.ganttWidget, makeLambdaFunction(GanttElement$3.prototype.call_0, GanttElement$3, [this$static, newState, newStepsJson]));
}

function $doSetSteps(this$static, newSteps){
  var l, remSteps, s, s$iterator;
  remSteps = new ArrayList;
  for (s$iterator = (l = $getStepsList($getGanttElement(this$static.ganttWidget)) , new ArrayList$1($toSteps(l))); s$iterator.i < s$iterator.this$01.array.length;) {
    s = castTo($next_0(s$iterator), 8);
    $indexOf_3(newSteps, s, 0) != -1 || (remSteps.array[remSteps.array.length] = s , true);
  }
  $doRemoveSteps(this$static, remSteps);
  $doAddSteps(this$static, newSteps, 0);
}

function $doSetSubStep(this$static, substep){
  var added, m, owner, w;
  if (!substep.owner) {
    return;
  }
  owner = $getStepWidget(this$static, substep.owner);
  if (!owner) {
    return;
  }
  w = $getSubStepWidget(this$static, substep);
  if (!w) {
    w = new SubStepWidget;
    $setGantt(w, this$static.ganttWidget, this$static.localeDataProvider);
    w.owner = owner;
    !!w.owner && !!w.owner.gantt_0 && $setGantt(w, w.owner.gantt_0, w.owner.localeDataProvider);
    m = castTo($get_2(this$static.substepsMap, substep.owner), 35);
    if (!m) {
      m = new HashMap;
      $put_0(this$static.substepsMap, substep.owner, m);
    }
    m.put(substep, w);
    $putStringValue(this$static.uidMap, substep.uid, substep);
    $add(owner, w, ($clinit_DOM() , owner.element).root);
    added = true;
  }
   else {
    added = false;
  }
  $ready(w, makeLambdaFunction(GanttElement$5.prototype.call_0, GanttElement$5, [this$static, substep, added]));
}

function $doSetSubSteps(this$static, step){
  var oldSubSteps, s, s$iterator, substep, substep$iterator, w;
  oldSubSteps = new ArrayList;
  $containsKey(this$static.substepsMap, step) && $addAll(oldSubSteps, castTo($get_2(this$static.substepsMap, step), 35).keySet());
  for (s$iterator = new ArrayList$1(oldSubSteps); s$iterator.i < s$iterator.this$01.array.length;) {
    s = castTo($next_0(s$iterator), 67);
    $clinit_Collections();
    if (!$contains_0(new Collections$UnmodifiableList(step.subSteps), s)) {
      w = $getSubStepWidget(this$static, s);
      if (w) {
        $remove(castTo($get_2(this$static.stepsMap, step), 14), w);
        castTo($get_2(this$static.substepsMap, step), 35).remove_3(s);
        $removeStringValue(this$static.uidMap, s.uid);
      }
    }
  }
  for (substep$iterator = ($clinit_Collections() , new Collections$UnmodifiableCollectionIterator($listIterator((new Collections$UnmodifiableList(step.subSteps)).coll, 0))); $hasNext(substep$iterator.it);) {
    substep = castTo($next_2(substep$iterator.it), 67);
    $doSetSubStep(this$static, substep);
  }
}

function $doUpdateStep(this$static, s){
  var l, l0, w, predecessor, oldPredecessor;
  w = castTo($get_2(this$static.stepsMap, s), 14);
  predecessor = s.predecessor;
  oldPredecessor = null;
  !!w.predecessorStepWidget && (oldPredecessor = castTo(w.predecessorStepWidget.step, 8));
  (!predecessor && !!oldPredecessor || !!predecessor && !$equals_2(predecessor, oldPredecessor)) && $setPredecessorStepWidget(w, castTo($get_2(this$static.stepsMap, predecessor), 14));
  $setAttribute(($clinit_DOM() , w.element), 'background-color', s.backgroundColor);
  $setAttribute(w.element, 'caption', s.caption);
  $setStep(w, s);
  if (!$getParentElement(w.element)) {
    $insertStep(this$static.ganttWidget, $wnd.Math.max(0, (l0 = $getStepsList($getGanttElement(this$static.ganttWidget)) , $indexOf_3($toSteps(l0), s, 0))), w);
    $setStep_0(this$static.ganttWidget, $wnd.Math.max(0, (l = $getStepsList($getGanttElement(this$static.ganttWidget)) , $indexOf_3($toSteps(l), s, 0))), w, true);
  }
  $setAttribute(w.element, 'start-date', '' + toString_12(s.startDate));
  $setAttribute(w.element, 'end-date', '' + toString_12(s.endDate));
  $doSetSubSteps(this$static, s);
  $updateWidth_0(w);
  $scheduleDeferred(($clinit_SchedulerImpl() , INSTANCE), new GanttElement$6(this$static, w, s));
}

function $doUpdateSubStep(this$static, substep, added){
  var w;
  w = $getSubStepWidget(this$static, substep);
  $setAttribute(($clinit_DOM() , w.element), 'background-color', substep.backgroundColor);
  $setAttribute(w.element, 'caption', substep.caption);
  $setStep(w, substep);
  $setReadOnly(w, this$static.state.readOnly);
  added && $updateStylesForSubSteps(w.owner);
  $setAttribute(w.element, 'start-date', '' + toString_12(substep.startDate));
  $setAttribute(w.element, 'end-date', '' + toString_12(substep.endDate));
  $updateWidth(w);
}

function $findStepWidgetByElement(this$static, target){
  var w, w$iterator;
  for (w$iterator = new ArrayList$1($getStepWidgets(this$static)); w$iterator.i < w$iterator.this$01.array.length;) {
    w = castTo($next_0(w$iterator), 14);
    if ($isOrHasChild(($clinit_DOM() , w.element), target)) {
      if (w) {
        return w;
      }
    }
  }
  return null;
}

function $getStepWidget(this$static, target){
  return castTo($get_2(this$static.stepsMap, target), 14);
}

function $getStepWidgets(this$static){
  var l, list, s, s$iterator;
  list = new ArrayList;
  for (s$iterator = (l = $getStepsList($getGanttElement(this$static.ganttWidget)) , new ArrayList$1($toSteps(l))); s$iterator.i < s$iterator.this$01.array.length;) {
    s = castTo($next_0(s$iterator), 8);
    $containsKey(this$static.stepsMap, s) && $add_2(list, castTo($get_2(this$static.stepsMap, s), 14));
  }
  return list;
}

function $getStepsList(elem){
  var arr;
  arr = elem.steps;
  if (arr == null || arr.length == 0) {
    return new ArrayList;
  }
  return new Arrays$ArrayList(arr);
}

function $getSubStepWidget(this$static, target){
  var m;
  m = castTo($get_2(this$static.substepsMap, target.owner), 35);
  if (m) {
    return castTo(m.get_0(target), 47);
  }
  return null;
}

function $init_0(this$static, jselement){
  $createWidget(this$static, jselement);
  $ready_0(this$static.ganttWidget, makeLambdaFunction(GanttElement$2.prototype.call_0, GanttElement$2, [this$static]));
}

function $notifyHeightChanged(this$static){
  var height;
  height = $getElement(this$static.ganttWidget).clientHeight | 0;
  if (this$static.previousHeight != height) {
    this$static.previousHeight = height;
    $scheduleDeferred(($clinit_SchedulerImpl() , INSTANCE), new GanttElement$9(this$static, height));
  }
}

function $notifySizeChanged(this$static){
  $notifyHeightChanged(this$static);
  $notifyWidthChanged(this$static);
}

function $notifyWidthChanged(this$static){
  var width_0;
  width_0 = $getElement(this$static.ganttWidget).clientWidth | 0;
  if (this$static.previousWidth != width_0) {
    this$static.previousWidth = width_0;
    $scheduleDeferred(($clinit_SchedulerImpl() , INSTANCE), new GanttElement$8(this$static));
  }
}

function $removeSteps(this$static, steps){
  var o, o$iterator, remSteps;
  remSteps = new ArrayList;
  for (o$iterator = steps.iterator(); o$iterator.hasNext_0();) {
    o = castToJso(o$iterator.next_1());
    $add_2(remSteps, toStep(o));
  }
  $doRemoveSteps(this$static, remSteps);
}

function $setLocale(this$static, locale){
  this$static.state.locale = locale;
  this$static.localeDataProvider.locale = locale;
  this$static.localeDataProvider.dateTimeService = null;
}

function $setState(this$static, newState, newStepsJson){
  $doSetState(this$static, toState(newState), newStepsJson);
}

function $setTimeZoneId(this$static, timezoneId){
  this$static.state.timeZoneId = timezoneId;
  this$static.localeDataProvider.timeZone = createTimeZone(0);
}

function $setTimeZoneId_0(this$static, timezoneId, timeZoneJson){
  var tzData;
  this$static.state.timeZoneId = timezoneId;
  this$static.state.timeZoneJson = timeZoneJson;
  this$static.localeDataProvider.timeZone = (tzData = safeEval(timeZoneJson) , createTimeZone_0(tzData));
}

function $toSteps(newStepObjects){
  var newSteps, o, o$iterator;
  newSteps = new ArrayList;
  for (o$iterator = newStepObjects.iterator(); o$iterator.hasNext_0();) {
    o = castToJso(o$iterator.next_1());
    $add_2(newSteps, toStep(o));
  }
  return newSteps;
}

function $updateAllStepsPredecessors(this$static){
  var s, s$iterator;
  for (s$iterator = new ArrayList$1($getStepWidgets(this$static)); s$iterator.i < s$iterator.this$01.array.length;) {
    s = castTo($next_0(s$iterator), 14);
    $ready(s, makeLambdaFunction(StepWidget$3.prototype.call_0, StepWidget$3, [s]));
  }
}

function GanttElement(){
  this.stepsMap = new HashMap;
  this.substepsMap = new HashMap;
  this.uidMap = new HashMap;
  this.localeDataProvider = new LocaleDataProviderImpl;
  this.ganttRpc = new GanttElement$1(this);
}

function constructor_0(){
  if (!instance_4) {
    instance_4 = new GanttElement;
    $init_0(instance_4, null);
  }
  return instance_4;
}

function constructor_1(jselement){
  if (!instance_4) {
    instance_4 = new GanttElement;
    $init_0(instance_4, jselement);
  }
  return instance_4;
}

function handleOnMoveEvent(elem, stepUid, newStepUid, startDate, endDate, details){
  elem.handleOnMove(stepUid, newStepUid, startDate, endDate, details);
}

function handleOnResizeEvent(elem, stepUid, startDate, endDate, details){
  elem.handleOnResize(stepUid, startDate, endDate, details);
}

function handlePredecessorChangeEvent(elem, newPredecessorStepUid, forTargetStepUid, clearPredecessorForStepUid){
  if (newPredecessorStepUid == forTargetStepUid) {
    return;
  }
  elem.handleOnPredecessorChange(newPredecessorStepUid, forTargetStepUid, clearPredecessorForStepUid);
}

function handleStepClickedEvent(elem, stepUid, details){
  elem.handleStepClicked(stepUid, details);
}

defineClass(103, 1, {301:1}, GanttElement);
_.addStep_0 = function addStep(step, index_0){
  var s;
  s = toStep(step);
  $doAddStep(this, null, s, index_0);
  $doUpdateStep(this, s);
}
;
_.addSteps_0 = function addSteps(steps, fromIndex){
  $doAddSteps(this, $toSteps(steps), fromIndex);
}
;
_.getGanttElement_0 = function getGanttElement(){
  return $getGanttElement(this.ganttWidget);
}
;
_.getStepByUid_0 = function getStepByUid(uid){
  if ($hasStringValue(this.uidMap, uid)) {
    return castTo($getStringValue(this.uidMap, uid), 46).toJson().jsObject;
  }
  return null;
}
;
_.getStepWidget_0 = function getStepWidget(target){
  return $getStepWidget(this, target);
}
;
_.getSubStepWidget_0 = function getSubStepWidget(target){
  return $getSubStepWidget(this, target);
}
;
_.getWidget_0 = function getWidget(){
  return this.ganttWidget;
}
;
_.notifyHeightChanged_0 = function notifyHeightChanged(){
  $notifyHeightChanged(this);
}
;
_.notifySizeChanged_0 = function notifySizeChanged(){
  $notifySizeChanged(this);
}
;
_.notifyWidthChanged_0 = function notifyWidthChanged(){
  $notifyWidthChanged(this);
}
;
_.registerStepElement_0 = function registerStepElement(stepElementObj, step){
  var l, s;
  if (!step) {
    return;
  }
  s = toStep(step);
  $doAddStep(this, stepElementObj, s, $wnd.Math.max(0, (l = $getStepsList($getGanttElement(this.ganttWidget)) , $indexOf_3($toSteps(l), s, 0))));
  $doUpdateStep(this, s);
}
;
_.removeStep_0 = function removeStep(step){
  $removeSteps(this, new Arrays$ArrayList(stampJavaTypeInfo(getClassLiteralForArray(Lcom_google_gwt_core_client_JavaScriptObject_2_classLit, 1), $intern_0, 0, 2, [step])));
}
;
_.removeSteps_0 = function removeSteps(steps){
  $removeSteps(this, steps);
}
;
_.setLocale_0 = function setLocale(locale){
  $setLocale(this, locale);
}
;
_.setState_0 = function setState(newState){
  $doSetState(this, toState(newState), null);
}
;
_.setSteps_0 = function setSteps(newSteps){
  $ready_0(this.ganttWidget, makeLambdaFunction(GanttElement$4.prototype.call_0, GanttElement$4, [this, newSteps]));
}
;
_.setTimeZoneId_0 = function setTimeZoneId(timezoneId){
  $setTimeZoneId(this, timezoneId);
}
;
_.setTimeZoneId_1 = function setTimeZoneId_0(timezoneId, timeZoneJson){
  $setTimeZoneId_0(this, timezoneId, timeZoneJson);
}
;
_.update_0 = function update(stepOrSubstep){
  var json;
  if (!stepOrSubstep) {
    return;
  }
  json = new JSONObject_0(stepOrSubstep);
  'substep' in json.jsObject && $get_0(json, 'substep').isBoolean().value_0?$doUpdateSubStep(this, toStep_0(stepOrSubstep), false):$doUpdateStep(this, toStep(stepOrSubstep));
}
;
_.updateAllStepsPredecessors_0 = function updateAllStepsPredecessors(){
  $updateAllStepsPredecessors(this);
}
;
_.updateStep_0 = function updateStep(step){
  $doUpdateStep(this, toStep(step));
}
;
_.updateSubStep_0 = function updateSubStep(substep){
  $doUpdateSubStep(this, toStep_0(substep), false);
}
;
_.previousHeight = -1;
_.previousWidth = -1;
_.state = null;
var instance_4;
var Lorg_tltv_gantt_client_GanttElement_2_classLit = createForClass('org.tltv.gantt.client', 'GanttElement', 103, Ljava_lang_Object_2_classLit);
function $onMove(this$static, stepUid, newStepUid, startDate, endDate, event_0, relativeToElement){
  var details;
  details = of(buildMouseEventDetails(event_0, relativeToElement));
  handleOnMoveEvent($getGanttElement(this$static.this$01.ganttWidget), stepUid, newStepUid, toDouble_0(startDate), toDouble_0(endDate), details);
}

function $onResize(this$static, stepUid, startDate, endDate, event_0, relativeToElement){
  var details;
  details = of(buildMouseEventDetails(event_0, relativeToElement));
  handleOnResizeEvent($getGanttElement(this$static.this$01.ganttWidget), stepUid, toDouble_0(startDate), toDouble_0(endDate), details);
}

function $onStepRelationSelected(this$static, source, startingPointChanged, newRelationStepElement){
  var sw, w;
  sw = $findStepWidgetByElement(this$static.this$01, newRelationStepElement);
  if (!sw) {
    return false;
  }
  if (startingPointChanged) {
    if ($equals_2(castTo(sw.step, 8), castTo(source.step, 8).predecessor)) {
      return false;
    }
     else if ($equals_2(castTo(sw.step, 8), castTo(source.step, 8))) {
      handlePredecessorChangeEvent($getElement(this$static.this$01.ganttWidget), null, castTo(source.step, 8).uid, castTo(source.step, 8).uid);
      return true;
    }
    handlePredecessorChangeEvent($getElement(this$static.this$01.ganttWidget), castTo(sw.step, 8).uid, castTo(source.step, 8).uid, null);
  }
   else {
    if ($equals_2(castTo(sw.step, 8), castTo(source.step, 8))) {
      return false;
    }
     else if ($equals_2(castTo(sw.step, 8), castTo(source.step, 8).predecessor)) {
      handlePredecessorChangeEvent($getElement(this$static.this$01.ganttWidget), null, castTo(source.step, 8).uid, castTo(source.step, 8).uid);
      return true;
    }
    if (castTo(source.step, 8).predecessor) {
      w = $getStepWidget(this$static.this$01, castTo(source.step, 8).predecessor);
      if (!!castTo(w.step, 8) && !!castTo(w.step, 8).predecessor && $equals_2(castTo(w.step, 8).predecessor, castTo(sw.step, 8))) {
        return false;
      }
    }
    handlePredecessorChangeEvent($getElement(this$static.this$01.ganttWidget), castTo(source.step, 8).predecessor.uid, castTo(sw.step, 8).uid, castTo(source.step, 8).uid);
  }
  return true;
}

function $stepClicked(this$static, stepUid, event_0, relativeToElement){
  var details;
  details = of(buildMouseEventDetails(event_0, relativeToElement));
  handleStepClickedEvent($getGanttElement(this$static.this$01.ganttWidget), stepUid, details);
}

function GanttElement$1(this$0){
  this.this$01 = this$0;
}

defineClass(149, 1, {}, GanttElement$1);
var Lorg_tltv_gantt_client_GanttElement$1_2_classLit = createForClass('org.tltv.gantt.client', 'GanttElement/1', 149, Ljava_lang_Object_2_classLit);
function GanttElement$2(this$0){
  this.this$01 = this$0;
}

defineClass(359, $wnd.Function, $intern_25, GanttElement$2);
_.call_0 = function call_6(args){
  $doInit(this.this$01);
  return null;
}
;
function GanttElement$3(this$0, val$newState, val$newStepsJson){
  this.this$01 = this$0;
  this.val$newState2 = val$newState;
  this.val$newStepsJson3 = val$newStepsJson;
}

defineClass(360, $wnd.Function, $intern_25, GanttElement$3);
_.call_0 = function call_7(args){
  var steps;
  this.this$01.state = this.val$newState2;
  this.this$01.localeDataProvider.localeProvider = this.this$01.state;
  steps = null;
  if (this.val$newStepsJson3 != null) {
    steps = new ArrayList;
    $addAll(steps, $toSteps(new Arrays$ArrayList(this.val$newStepsJson3)));
  }
  $setLocale(this.this$01, this.this$01.state.locale);
  this.this$01.state.timeZoneJson != null?$setTimeZoneId_0(this.this$01, this.this$01.state.timeZoneId, this.this$01.state.timeZoneJson):$setTimeZoneId(this.this$01, this.this$01.state.timeZoneId);
  $setAttribute($getElement(this.this$01.ganttWidget), 'width', this.val$newState2.width_0);
  $setAttribute($getElement(this.this$01.ganttWidget), 'height', this.val$newState2.height);
  $setResolution_0(this.this$01.ganttWidget, this.this$01.state.resolution);
  $setStartDate_0(this.this$01.ganttWidget, valueOf_1(this.this$01.state.startDate));
  $setEndDate_0(this.this$01.ganttWidget, valueOf_1(this.this$01.state.endDate));
  $setFirstDayOfRange_0(this.this$01.ganttWidget, this.this$01.state.firstDayOfRange);
  $setFirstHourOfRange_0(this.this$01.ganttWidget, this.this$01.state.firstHourOfRange);
  $setMovableSteps_0(this.this$01.ganttWidget, !this.this$01.state.readOnly && this.this$01.state.movableSteps);
  $setResizableSteps_0(this.this$01.ganttWidget, !this.this$01.state.readOnly && this.this$01.state.resizableSteps);
  $setMovableStepsBetweenRows_0(this.this$01.ganttWidget, this.this$01.state.movableStepsBetweenRows);
  $setMonthRowVisible_0(this.this$01.ganttWidget, this.this$01.state.monthRowVisible);
  $setYearRowVisible_0(this.this$01.ganttWidget, this.this$01.state.yearRowVisible);
  $setBackgroundGridEnabled_0(this.this$01.ganttWidget, this.this$01.state.backgroundGridEnabled);
  $setWeekFormat_0(this.this$01.ganttWidget, this.this$01.state.weekFormat);
  $setDayFormat_0(this.this$01.ganttWidget, this.this$01.state.dayFormat);
  $setMonthFormat_0(this.this$01.ganttWidget, this.this$01.state.monthFormat);
  $setYearFormat_0(this.this$01.ganttWidget, this.this$01.state.yearFormat);
  $setDefaultContextMenuEnabled_0(this.this$01.ganttWidget, this.this$01.state.defaultContextMenuEnabled);
  $setForceUpdateTimeline(this.this$01.ganttWidget);
  $resetListeners(this.this$01.ganttWidget);
  this.this$01.ganttWidget.attached || $onAttach(this.this$01.ganttWidget);
  !!steps && $doSetSteps(this.this$01, steps);
  $scheduleDeferred(($clinit_SchedulerImpl() , INSTANCE), new GanttElement$3$1(this));
  return null;
}
;
function GanttElement$3$1(this$1){
  this.this$11 = this$1;
}

defineClass(150, 1, {}, GanttElement$3$1);
_.execute_1 = function execute_7(){
  var lastArg;
  $requestUpdate(this.this$11.this$01.ganttWidget, $getStepWidgets(this.this$11.this$01));
  $notifyHeightChanged_0(this.this$11.this$01.ganttWidget, round_int(getBoundingClientRectHeight($getElement(this.this$11.this$01.ganttWidget))));
  $notifyWidthChanged_0((lastArg = this.this$11.this$01.ganttWidget , round_int(getBoundingClientRectWidth($getElement(this.this$11.this$01.ganttWidget))) , lastArg));
  $updateAllStepsPredecessors(this.this$11.this$01);
}
;
var Lorg_tltv_gantt_client_GanttElement$3$1_2_classLit = createForClass('org.tltv.gantt.client', 'GanttElement/3/1', 150, Ljava_lang_Object_2_classLit);
function GanttElement$4(this$0, val$newSteps){
  this.this$01 = this$0;
  this.val$newSteps2 = val$newSteps;
}

defineClass(361, $wnd.Function, $intern_25, GanttElement$4);
_.call_0 = function call_8(args){
  $doSetSteps(this.this$01, $toSteps(this.val$newSteps2));
  return null;
}
;
function GanttElement$5(this$0, val$substep, val$added){
  this.this$01 = this$0;
  this.val$substep2 = val$substep;
  this.val$added3 = val$added;
}

defineClass(362, $wnd.Function, $intern_25, GanttElement$5);
_.call_0 = function call_9(args){
  $doUpdateSubStep(this.this$01, this.val$substep2, this.val$added3);
  return null;
}
;
_.val$added3 = false;
function GanttElement$6(this$0, val$w, val$s){
  this.this$01 = this$0;
  this.val$w2 = val$w;
  this.val$s3 = val$s;
}

defineClass(151, 1, {}, GanttElement$6);
_.execute_1 = function execute_8(){
  $updatePredecessor(this.val$w2);
  $updateRelatedStepsPredecessors(this.val$s3, $getStepWidgets(this.this$01));
}
;
var Lorg_tltv_gantt_client_GanttElement$6_2_classLit = createForClass('org.tltv.gantt.client', 'GanttElement/6', 151, Ljava_lang_Object_2_classLit);
function GanttElement$8(this$0){
  this.this$01 = this$0;
}

defineClass(152, 1, {}, GanttElement$8);
_.execute_1 = function execute_9(){
  $notifyWidthChanged_0(this.this$01.ganttWidget);
  $updateAllStepsPredecessors(this.this$01);
}
;
var Lorg_tltv_gantt_client_GanttElement$8_2_classLit = createForClass('org.tltv.gantt.client', 'GanttElement/8', 152, Ljava_lang_Object_2_classLit);
function GanttElement$9(this$0, val$height){
  this.this$01 = this$0;
  this.val$height2 = val$height;
}

defineClass(153, 1, {}, GanttElement$9);
_.execute_1 = function execute_10(){
  $notifyHeightChanged_0(this.this$01.ganttWidget, this.val$height2);
}
;
_.val$height2 = 0;
var Lorg_tltv_gantt_client_GanttElement$9_2_classLit = createForClass('org.tltv.gantt.client', 'GanttElement/9', 153, Ljava_lang_Object_2_classLit);
function $export(){
  if (!exported) {
    exported = true;
    $export0();
  }
}

function $export0(){
  var pkg = ($clinit_ExporterUtil() , $declarePackage('gantt.GanttElement'));
  var __0;
  $wnd.gantt.GanttElement = $entry(function(){
    var g, j = this;
    isAssignableToInstance(Lorg_tltv_gantt_client_GanttElement_2_classLit, arguments)?(g = arguments[0]):arguments.length == 0?(g = constructor_0()):arguments.length == 1 && (g = constructor_1(arguments[0]));
    j.g = g;
    setWrapper(g, j);
    return j;
  }
  );
  __0 = $wnd.gantt.GanttElement.prototype = new Object;
  __0.addStep = $entry(function(a0, a1){
    this.g.addStep_0(a0, a1);
  }
  );
  __0.addSteps = $entry(function(a0, a1){
    this.g.addSteps_0(a0, a1);
  }
  );
  __0.getGanttElement = $entry(function(){
    return this.g.getGanttElement_0();
  }
  );
  __0.getStepByUid = $entry(function(a0){
    return this.g.getStepByUid_0(a0);
  }
  );
  __0.getStepWidget = $entry(function(a0){
    return this.g.getStepWidget_0(gwtInstance(a0));
  }
  );
  $wnd.gantt.GanttElement.getSteps = $entry(function(a0){
    return wrap_0(a0.steps);
  }
  );
  __0.getSubStepWidget = $entry(function(a0){
    return this.g.getSubStepWidget_0(gwtInstance(a0));
  }
  );
  __0.getWidget = $entry(function(){
    return this.g.getWidget_0();
  }
  );
  $wnd.gantt.GanttElement.handleOnMoveEvent = $entry(function(a0, a1, a2, a3, a4, a5){
    handleOnMoveEvent(a0, a1, a2, a3, a4, gwtInstance(a5));
  }
  );
  $wnd.gantt.GanttElement.handleOnResizeEvent = $entry(function(a0, a1, a2, a3, a4){
    handleOnResizeEvent(a0, a1, a2, a3, gwtInstance(a4));
  }
  );
  $wnd.gantt.GanttElement.handlePredecessorChangeEvent = $entry(function(a0, a1, a2, a3){
    handlePredecessorChangeEvent(a0, a1, a2, a3);
  }
  );
  $wnd.gantt.GanttElement.handleStepClickedEvent = $entry(function(a0, a1, a2){
    handleStepClickedEvent(a0, a1, gwtInstance(a2));
  }
  );
  __0.notifyHeightChanged = $entry(function(){
    this.g.notifyHeightChanged_0();
  }
  );
  __0.notifySizeChanged = $entry(function(){
    this.g.notifySizeChanged_0();
  }
  );
  __0.notifyWidthChanged = $entry(function(){
    this.g.notifyWidthChanged_0();
  }
  );
  __0.registerStepElement = $entry(function(a0, a1){
    this.g.registerStepElement_0(a0, a1);
  }
  );
  __0.removeStep = $entry(function(a0){
    this.g.removeStep_0(a0);
  }
  );
  __0.removeSteps = $entry(function(a0){
    this.g.removeSteps_0(a0);
  }
  );
  __0.setLocale = $entry(function(a0){
    this.g.setLocale_0(a0);
  }
  );
  __0.setState = $entry(function(a0, a1){
    runDispatch_0(this.g, Lorg_tltv_gantt_client_GanttElement_2_classLit, 1, arguments, false, false)[0];
  }
  );
  __0.setSteps = $entry(function(a0){
    this.g.setSteps_0(a0);
  }
  );
  __0.setTimeZoneId = $entry(function(a0, a1){
    runDispatch_0(this.g, Lorg_tltv_gantt_client_GanttElement_2_classLit, 0, arguments, false, false)[0];
  }
  );
  __0.update = $entry(function(a0){
    this.g.update_0(a0);
  }
  );
  __0.updateAllStepsPredecessors = $entry(function(){
    this.g.updateAllStepsPredecessors_0();
  }
  );
  __0.updateStep = $entry(function(a0){
    this.g.updateStep_0(a0);
  }
  );
  __0.updateSubStep = $entry(function(a0){
    this.g.updateSubStep_0(a0);
  }
  );
  registerDispatchMap(Lorg_tltv_gantt_client_GanttElement_2_classLit, {0:{1:[[function(){
    return this.setTimeZoneId_0.apply(this, arguments);
  }
  , null, undefined, 'string']], 2:[[function(){
    return this.setTimeZoneId_1.apply(this, arguments);
  }
  , null, undefined, 'string', 'string']]}, 1:{1:[[function(){
    return this.setState_0.apply(this, arguments);
  }
  , null, undefined, 'object']], 2:[[__static_wrapper_setState, null, unshift, 'object', 'array']]}}, false);
  addTypeMap(Lorg_tltv_gantt_client_GanttElement_2_classLit, $wnd.gantt.GanttElement);
  if (pkg)
    for (p in pkg)
      $wnd.gantt.GanttElement[p] === undefined && ($wnd.gantt.GanttElement[p] = pkg[p]);
}

function GanttElementExporterImpl(){
  $export();
}

function __static_wrapper_setState(instance, a0, a1){
  $setState(instance, a0, ($clinit_ExporterUtil() , $toArrObject(a1, initUnidimensionalArray(Lcom_google_gwt_core_client_JavaScriptObject_2_classLit, $intern_0, 0, a1.length, 2, 1))));
}

defineClass(140, 1, {}, GanttElementExporterImpl);
var exported = false;
var Lorg_tltv_gantt_client_GanttElementExporterImpl_2_classLit = createForClass('org.tltv.gantt.client', 'GanttElementExporterImpl', 140, Ljava_lang_Object_2_classLit);
function $setBackgroundGridEnabled(this$static, backgroundGridEnabled){
  this$static.backgroundGridEnabled = backgroundGridEnabled;
}

function $setDayFormat(this$static, dayFormat){
  this$static.dayFormat = dayFormat;
}

function $setDefaultContextMenuEnabled(this$static, defaultContextMenuEnabled){
  this$static.defaultContextMenuEnabled = defaultContextMenuEnabled;
}

function $setEndDate(this$static, endDate){
  this$static.endDate = endDate;
}

function $setFirstDayOfRange(this$static, firstDayOfRange){
  this$static.firstDayOfRange = firstDayOfRange;
}

function $setFirstHourOfRange(this$static, firstHourOfRange){
  this$static.firstHourOfRange = firstHourOfRange;
}

function $setHeight(this$static, height){
  this$static.height = height;
}

function $setLocale_0(this$static, locale){
  this$static.locale = locale;
}

function $setLocaleDayNames(this$static, localeDayNames){
  this$static.localeDayNames = localeDayNames;
}

function $setLocaleFirstDayOfWeek(this$static, localeFirstDayOfWeek){
  this$static.localeFirstDayOfWeek = localeFirstDayOfWeek;
}

function $setLocaleMonthNames(this$static, localeMonthNames){
  this$static.localeMonthNames = localeMonthNames;
}

function $setLocaleShortDayNames(this$static, localeShortDayNames){
  this$static.localeShortDayNames = localeShortDayNames;
}

function $setLocaleShortMonthNames(this$static, localeShortMonthNames){
  this$static.localeShortMonthNames = localeShortMonthNames;
}

function $setLocaleTwelveHourClock(this$static, localeTwelveHourClock){
  this$static.localeTwelveHourClock = localeTwelveHourClock;
}

function $setMonthFormat(this$static, monthFormat){
  this$static.monthFormat = monthFormat;
}

function $setMonthRowVisible(this$static, monthRowVisible){
  this$static.monthRowVisible = monthRowVisible;
}

function $setMovableSteps(this$static, movableSteps){
  this$static.movableSteps = movableSteps;
}

function $setMovableStepsBetweenRows(this$static, movableStepsBetweenRows){
  this$static.movableStepsBetweenRows = movableStepsBetweenRows;
}

function $setReadOnly_0(this$static, readOnly){
  this$static.readOnly = readOnly;
}

function $setResizableSteps(this$static, resizableSteps){
  this$static.resizableSteps = resizableSteps;
}

function $setResolution(this$static, resolution){
  this$static.resolution = resolution;
}

function $setStartDate(this$static, startDate){
  this$static.startDate = startDate;
}

function $setTimeZoneId_1(this$static, timeZoneId){
  this$static.timeZoneId = timeZoneId;
}

function $setTimeZoneJson(this$static, timeZoneJson){
  this$static.timeZoneJson = timeZoneJson;
}

function $setWeekFormat(this$static, weekFormat){
  this$static.weekFormat = weekFormat;
}

function $setWidth(this$static, width_0){
  this$static.width_0 = width_0;
}

function $setYearFormat(this$static, yearFormat){
  this$static.yearFormat = yearFormat;
}

function $setYearRowVisible(this$static, yearRowVisible){
  this$static.yearRowVisible = yearRowVisible;
}

function GanttElementState(){
  this.resolution = ($clinit_Resolution() , Day);
  this.localeDayNames = stampJavaTypeInfo(getClassLiteralForArray(Ljava_lang_String_2_classLit, 1), $intern_0, 2, 6, ['sunday', 'monday', 'tuesday', 'wednesday', 'thursday', 'friday', 'saturday']);
  this.localeShortDayNames = stampJavaTypeInfo(getClassLiteralForArray(Ljava_lang_String_2_classLit, 1), $intern_0, 2, 6, ['sun', 'mon', 'tue', 'wed', 'thu', 'fri', 'sat']);
  this.localeMonthNames = stampJavaTypeInfo(getClassLiteralForArray(Ljava_lang_String_2_classLit, 1), $intern_0, 2, 6, ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December']);
  this.localeShortMonthNames = stampJavaTypeInfo(getClassLiteralForArray(Ljava_lang_String_2_classLit, 1), $intern_0, 2, 6, ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']);
}

function toState(o){
  var json, s;
  json = new JSONObject_0(o);
  s = new GanttElementState;
  'width' in json.jsObject && $setWidth(s, $get_0(json, 'width').isString().value_0);
  'height' in json.jsObject && $setHeight(s, $get_0(json, 'height').isString().value_0);
  'startDate' in json.jsObject && $setStartDate(s, $longValue($get_0(json, 'startDate').isNumber().value_0));
  'endDate' in json.jsObject && $setEndDate(s, $longValue($get_0(json, 'endDate').isNumber().value_0));
  'locale' in json.jsObject && $setLocale_0(s, $get_0(json, 'locale').isString().value_0);
  'timeZoneJson' in json.jsObject && $setTimeZoneJson(s, $get_0(json, 'timeZoneJson').isString().value_0);
  'timeZoneId' in json.jsObject && $setTimeZoneId_1(s, $get_0(json, 'timeZoneId').isString().value_0);
  'resolution' in json.jsObject && $setResolution(s, valueOf_6($get_0(json, 'resolution').isString().value_0));
  'resizableSteps' in json.jsObject && $setResizableSteps(s, $get_0(json, 'resizableSteps').isBoolean().value_0);
  'movableSteps' in json.jsObject && $setMovableSteps(s, $get_0(json, 'movableSteps').isBoolean().value_0);
  'movableStepsBetweenRows' in json.jsObject && $setMovableStepsBetweenRows(s, $get_0(json, 'movableStepsBetweenRows').isBoolean().value_0);
  'monthRowVisible' in json.jsObject && $setMonthRowVisible(s, $get_0(json, 'monthRowVisible').isBoolean().value_0);
  'yearRowVisible' in json.jsObject && $setYearRowVisible(s, $get_0(json, 'yearRowVisible').isBoolean().value_0);
  'backgroundGridEnabled' in json.jsObject && $setBackgroundGridEnabled(s, $get_0(json, 'backgroundGridEnabled').isBoolean().value_0);
  'defaultContextMenuEnabled' in json.jsObject && $setDefaultContextMenuEnabled(s, $get_0(json, 'defaultContextMenuEnabled').isBoolean().value_0);
  'weekFormat' in json.jsObject && $setWeekFormat(s, $get_0(json, 'weekFormat').isString().value_0);
  'dayFormat' in json.jsObject && $setDayFormat(s, $get_0(json, 'dayFormat').isString().value_0);
  'monthFormat' in json.jsObject && $setMonthFormat(s, $get_0(json, 'monthFormat').isString().value_0);
  'yearFormat' in json.jsObject && $setYearFormat(s, $get_0(json, 'yearFormat').isString().value_0);
  'readOnly' in json.jsObject && $setReadOnly_0(s, $get_0(json, 'readOnly').isBoolean().value_0);
  'firstDayOfRange' in json.jsObject && $setFirstDayOfRange(s, $intValue($get_0(json, 'firstDayOfRange').isNumber().value_0));
  'firstHourOfRange' in json.jsObject && $setFirstHourOfRange(s, $intValue($get_0(json, 'firstHourOfRange').isNumber().value_0));
  'localeDayNames' in json.jsObject && $setLocaleDayNames(s, toStringArray($get_0(json, 'localeDayNames').isArray_0()));
  'localeShortDayNames' in json.jsObject && $setLocaleShortDayNames(s, toStringArray($get_0(json, 'localeShortDayNames').isArray_0()));
  'localeMonthNames' in json.jsObject && $setLocaleMonthNames(s, toStringArray($get_0(json, 'localeMonthNames').isArray_0()));
  'localeShortMonthNames' in json.jsObject && $setLocaleShortMonthNames(s, toStringArray($get_0(json, 'localeShortMonthNames').isArray_0()));
  'localeFirstDayOfWeek' in json.jsObject && $setLocaleFirstDayOfWeek(s, $intValue($get_0(json, 'localeFirstDayOfWeek').isNumber().value_0));
  'localeTwelveHourClock' in json.jsObject && $setLocaleTwelveHourClock(s, $get_0(json, 'localeTwelveHourClock').isBoolean().value_0);
  return s;
}

defineClass(104, 1, {301:1}, GanttElementState);
_.getDayFormat_0 = function getDayFormat(){
  return this.dayFormat;
}
;
_.getFirstDayOfRange_0 = function getFirstDayOfRange(){
  return this.firstDayOfRange;
}
;
_.getFirstHourOfRange_0 = function getFirstHourOfRange(){
  return this.firstHourOfRange;
}
;
_.getHeight_0 = function getHeight(){
  return this.height;
}
;
_.getLocale_0 = function getLocale(){
  return this.locale;
}
;
_.getLocaleDayNames_0 = function getLocaleDayNames(){
  return this.localeDayNames;
}
;
_.getLocaleFirstDayOfWeek_0 = function getLocaleFirstDayOfWeek(){
  return this.localeFirstDayOfWeek;
}
;
_.getLocaleMonthNames_0 = function getLocaleMonthNames(){
  return this.localeMonthNames;
}
;
_.getLocaleShortDayNames_0 = function getLocaleShortDayNames(){
  return this.localeShortDayNames;
}
;
_.getLocaleShortMonthNames_0 = function getLocaleShortMonthNames(){
  return this.localeShortMonthNames;
}
;
_.getMonthFormat_0 = function getMonthFormat(){
  return this.monthFormat;
}
;
_.getResolution_0 = function getResolution(){
  return this.resolution;
}
;
_.getTimeZoneId_0 = function getTimeZoneId(){
  return this.timeZoneId;
}
;
_.getTimeZoneJson_0 = function getTimeZoneJson(){
  return this.timeZoneJson;
}
;
_.getWeekFormat_0 = function getWeekFormat(){
  return this.weekFormat;
}
;
_.getWidth_0 = function getWidth(){
  return this.width_0;
}
;
_.getYearFormat_0 = function getYearFormat(){
  return this.yearFormat;
}
;
_.isBackgroundGridEnabled_0 = function isBackgroundGridEnabled(){
  return this.backgroundGridEnabled;
}
;
_.isDefaultContextMenuEnabled_0 = function isDefaultContextMenuEnabled(){
  return this.defaultContextMenuEnabled;
}
;
_.isLocaleTwelveHourClock_0 = function isLocaleTwelveHourClock(){
  return this.localeTwelveHourClock;
}
;
_.isMonthRowVisible_0 = function isMonthRowVisible(){
  return this.monthRowVisible;
}
;
_.isMovableSteps_0 = function isMovableSteps(){
  return this.movableSteps;
}
;
_.isMovableStepsBetweenRows_0 = function isMovableStepsBetweenRows(){
  return this.movableStepsBetweenRows;
}
;
_.isReadOnly_0 = function isReadOnly(){
  return this.readOnly;
}
;
_.isResizableSteps_0 = function isResizableSteps(){
  return this.resizableSteps;
}
;
_.isYearRowVisible_0 = function isYearRowVisible(){
  return this.yearRowVisible;
}
;
_.setBackgroundGridEnabled_0 = function setBackgroundGridEnabled(backgroundGridEnabled){
  $setBackgroundGridEnabled(this, backgroundGridEnabled);
}
;
_.setDayFormat_0 = function setDayFormat(dayFormat){
  $setDayFormat(this, dayFormat);
}
;
_.setDefaultContextMenuEnabled_0 = function setDefaultContextMenuEnabled(defaultContextMenuEnabled){
  $setDefaultContextMenuEnabled(this, defaultContextMenuEnabled);
}
;
_.setFirstDayOfRange_0 = function setFirstDayOfRange(firstDayOfRange){
  $setFirstDayOfRange(this, firstDayOfRange);
}
;
_.setFirstHourOfRange_0 = function setFirstHourOfRange(firstHourOfRange){
  $setFirstHourOfRange(this, firstHourOfRange);
}
;
_.setHeight_0 = function setHeight(height){
  $setHeight(this, height);
}
;
_.setLocale_0 = function setLocale_0(locale){
  $setLocale_0(this, locale);
}
;
_.setLocaleFirstDayOfWeek_0 = function setLocaleFirstDayOfWeek(localeFirstDayOfWeek){
  $setLocaleFirstDayOfWeek(this, localeFirstDayOfWeek);
}
;
_.setLocaleTwelveHourClock_0 = function setLocaleTwelveHourClock(localeTwelveHourClock){
  $setLocaleTwelveHourClock(this, localeTwelveHourClock);
}
;
_.setMonthFormat_0 = function setMonthFormat(monthFormat){
  $setMonthFormat(this, monthFormat);
}
;
_.setMonthRowVisible_0 = function setMonthRowVisible(monthRowVisible){
  $setMonthRowVisible(this, monthRowVisible);
}
;
_.setMovableSteps_0 = function setMovableSteps(movableSteps){
  $setMovableSteps(this, movableSteps);
}
;
_.setMovableStepsBetweenRows_0 = function setMovableStepsBetweenRows(movableStepsBetweenRows){
  $setMovableStepsBetweenRows(this, movableStepsBetweenRows);
}
;
_.setReadOnly_0 = function setReadOnly(readOnly){
  $setReadOnly_0(this, readOnly);
}
;
_.setResizableSteps_0 = function setResizableSteps(resizableSteps){
  $setResizableSteps(this, resizableSteps);
}
;
_.setResolution_0 = function setResolution(resolution){
  $setResolution(this, resolution);
}
;
_.setTimeZoneId_0 = function setTimeZoneId_1(timeZoneId){
  $setTimeZoneId_1(this, timeZoneId);
}
;
_.setTimeZoneJson_0 = function setTimeZoneJson(timeZoneJson){
  $setTimeZoneJson(this, timeZoneJson);
}
;
_.setWeekFormat_0 = function setWeekFormat(weekFormat){
  $setWeekFormat(this, weekFormat);
}
;
_.setWidth_0 = function setWidth(width_0){
  $setWidth(this, width_0);
}
;
_.setYearFormat_0 = function setYearFormat(yearFormat){
  $setYearFormat(this, yearFormat);
}
;
_.setYearRowVisible_0 = function setYearRowVisible(yearRowVisible){
  $setYearRowVisible(this, yearRowVisible);
}
;
_.backgroundGridEnabled = true;
_.defaultContextMenuEnabled = false;
_.endDate = 0;
_.firstDayOfRange = 0;
_.firstHourOfRange = 0;
_.height = '100%';
_.localeFirstDayOfWeek = 0;
_.localeTwelveHourClock = false;
_.monthRowVisible = true;
_.movableSteps = true;
_.movableStepsBetweenRows = false;
_.readOnly = false;
_.resizableSteps = true;
_.startDate = 0;
_.width_0 = '100%';
_.yearRowVisible = true;
var instance_5;
var Lorg_tltv_gantt_client_GanttElementState_2_classLit = createForClass('org.tltv.gantt.client', 'GanttElementState', 104, Ljava_lang_Object_2_classLit);
function $export_0(){
  if (!exported_0) {
    exported_0 = true;
    $export0_0();
  }
}

function $export0_0(){
  var pkg = ($clinit_ExporterUtil() , $declarePackage('gantt.GanttElementState'));
  var __0;
  $wnd.gantt.GanttElementState = $entry(function(){
    var g, j = this;
    isAssignableToInstance(Lorg_tltv_gantt_client_GanttElementState_2_classLit, arguments)?(g = arguments[0]):arguments.length == 0 && (g = (!instance_5 && (instance_5 = new GanttElementState) , instance_5));
    j.g = g;
    setWrapper(g, j);
    return j;
  }
  );
  __0 = $wnd.gantt.GanttElementState.prototype = new Object;
  __0.getDayFormat = $entry(function(){
    return this.g.getDayFormat_0();
  }
  );
  __0.getEndDate = $entry(function(){
    return __static_wrapper_getEndDate(this.g);
  }
  );
  __0.getFirstDayOfRange = $entry(function(){
    return this.g.getFirstDayOfRange_0();
  }
  );
  __0.getFirstHourOfRange = $entry(function(){
    return this.g.getFirstHourOfRange_0();
  }
  );
  __0.getHeight = $entry(function(){
    return this.g.getHeight_0();
  }
  );
  __0.getLocale = $entry(function(){
    return this.g.getLocale_0();
  }
  );
  __0.getLocaleDayNames = $entry(function(){
    return wrap_1(this.g.getLocaleDayNames_0());
  }
  );
  __0.getLocaleFirstDayOfWeek = $entry(function(){
    return this.g.getLocaleFirstDayOfWeek_0();
  }
  );
  __0.getLocaleMonthNames = $entry(function(){
    return wrap_1(this.g.getLocaleMonthNames_0());
  }
  );
  __0.getLocaleShortDayNames = $entry(function(){
    return wrap_1(this.g.getLocaleShortDayNames_0());
  }
  );
  __0.getLocaleShortMonthNames = $entry(function(){
    return wrap_1(this.g.getLocaleShortMonthNames_0());
  }
  );
  __0.getMonthFormat = $entry(function(){
    return this.g.getMonthFormat_0();
  }
  );
  __0.getResolution = $entry(function(){
    return this.g.getResolution_0();
  }
  );
  __0.getStartDate = $entry(function(){
    return __static_wrapper_getStartDate(this.g);
  }
  );
  __0.getTimeZoneId = $entry(function(){
    return this.g.getTimeZoneId_0();
  }
  );
  __0.getTimeZoneJson = $entry(function(){
    return this.g.getTimeZoneJson_0();
  }
  );
  __0.getWeekFormat = $entry(function(){
    return this.g.getWeekFormat_0();
  }
  );
  __0.getWidth = $entry(function(){
    return this.g.getWidth_0();
  }
  );
  __0.getYearFormat = $entry(function(){
    return this.g.getYearFormat_0();
  }
  );
  __0.isBackgroundGridEnabled = $entry(function(){
    return this.g.isBackgroundGridEnabled_0();
  }
  );
  __0.isDefaultContextMenuEnabled = $entry(function(){
    return this.g.isDefaultContextMenuEnabled_0();
  }
  );
  __0.isLocaleTwelveHourClock = $entry(function(){
    return this.g.isLocaleTwelveHourClock_0();
  }
  );
  __0.isMonthRowVisible = $entry(function(){
    return this.g.isMonthRowVisible_0();
  }
  );
  __0.isMovableSteps = $entry(function(){
    return this.g.isMovableSteps_0();
  }
  );
  __0.isMovableStepsBetweenRows = $entry(function(){
    return this.g.isMovableStepsBetweenRows_0();
  }
  );
  __0.isReadOnly = $entry(function(){
    return this.g.isReadOnly_0();
  }
  );
  __0.isResizableSteps = $entry(function(){
    return this.g.isResizableSteps_0();
  }
  );
  __0.isYearRowVisible = $entry(function(){
    return this.g.isYearRowVisible_0();
  }
  );
  __0.setBackgroundGridEnabled = $entry(function(a0){
    this.g.setBackgroundGridEnabled_0(a0);
  }
  );
  __0.setDayFormat = $entry(function(a0){
    this.g.setDayFormat_0(a0);
  }
  );
  __0.setDefaultContextMenuEnabled = $entry(function(a0){
    this.g.setDefaultContextMenuEnabled_0(a0);
  }
  );
  __0.setEndDate = $entry(function(a0){
    __static_wrapper_setEndDate(this.g, a0);
  }
  );
  __0.setFirstDayOfRange = $entry(function(a0){
    this.g.setFirstDayOfRange_0(a0);
  }
  );
  __0.setFirstHourOfRange = $entry(function(a0){
    this.g.setFirstHourOfRange_0(a0);
  }
  );
  __0.setHeight = $entry(function(a0){
    this.g.setHeight_0(a0);
  }
  );
  __0.setLocale = $entry(function(a0){
    this.g.setLocale_0(a0);
  }
  );
  __0.setLocaleDayNames = $entry(function(a0){
    __static_wrapper_setLocaleDayNames(this.g, a0);
  }
  );
  __0.setLocaleFirstDayOfWeek = $entry(function(a0){
    this.g.setLocaleFirstDayOfWeek_0(a0);
  }
  );
  __0.setLocaleMonthNames = $entry(function(a0){
    __static_wrapper_setLocaleMonthNames(this.g, a0);
  }
  );
  __0.setLocaleShortDayNames = $entry(function(a0){
    __static_wrapper_setLocaleShortDayNames(this.g, a0);
  }
  );
  __0.setLocaleShortMonthNames = $entry(function(a0){
    __static_wrapper_setLocaleShortMonthNames(this.g, a0);
  }
  );
  __0.setLocaleTwelveHourClock = $entry(function(a0){
    this.g.setLocaleTwelveHourClock_0(a0);
  }
  );
  __0.setMonthFormat = $entry(function(a0){
    this.g.setMonthFormat_0(a0);
  }
  );
  __0.setMonthRowVisible = $entry(function(a0){
    this.g.setMonthRowVisible_0(a0);
  }
  );
  __0.setMovableSteps = $entry(function(a0){
    this.g.setMovableSteps_0(a0);
  }
  );
  __0.setMovableStepsBetweenRows = $entry(function(a0){
    this.g.setMovableStepsBetweenRows_0(a0);
  }
  );
  __0.setReadOnly = $entry(function(a0){
    this.g.setReadOnly_0(a0);
  }
  );
  __0.setResizableSteps = $entry(function(a0){
    this.g.setResizableSteps_0(a0);
  }
  );
  __0.setResolution = $entry(function(a0){
    this.g.setResolution_0(gwtInstance(a0));
  }
  );
  __0.setStartDate = $entry(function(a0){
    __static_wrapper_setStartDate(this.g, a0);
  }
  );
  __0.setTimeZoneId = $entry(function(a0){
    this.g.setTimeZoneId_0(a0);
  }
  );
  __0.setTimeZoneJson = $entry(function(a0){
    this.g.setTimeZoneJson_0(a0);
  }
  );
  __0.setWeekFormat = $entry(function(a0){
    this.g.setWeekFormat_0(a0);
  }
  );
  __0.setWidth = $entry(function(a0){
    this.g.setWidth_0(a0);
  }
  );
  __0.setYearFormat = $entry(function(a0){
    this.g.setYearFormat_0(a0);
  }
  );
  __0.setYearRowVisible = $entry(function(a0){
    this.g.setYearRowVisible_0(a0);
  }
  );
  $wnd.gantt.GanttElementState.toState = $entry(function(a0){
    return wrap(toState(a0));
  }
  );
  addTypeMap(Lorg_tltv_gantt_client_GanttElementState_2_classLit, $wnd.gantt.GanttElementState);
  if (pkg)
    for (p in pkg)
      $wnd.gantt.GanttElementState[p] === undefined && ($wnd.gantt.GanttElementState[p] = pkg[p]);
}

function GanttElementStateExporterImpl(){
  $export_0();
}

function __static_wrapper_getEndDate(instance){
  return toDouble_0(instance.endDate);
}

function __static_wrapper_getStartDate(instance){
  return toDouble_0(instance.startDate);
}

function __static_wrapper_setEndDate(instance, a0){
  $setEndDate(instance, fromDouble_0(a0));
}

function __static_wrapper_setLocaleDayNames(instance, a0){
  $setLocaleDayNames(instance, ($clinit_ExporterUtil() , $toArrString(a0)));
}

function __static_wrapper_setLocaleMonthNames(instance, a0){
  $setLocaleMonthNames(instance, ($clinit_ExporterUtil() , $toArrString(a0)));
}

function __static_wrapper_setLocaleShortDayNames(instance, a0){
  $setLocaleShortDayNames(instance, ($clinit_ExporterUtil() , $toArrString(a0)));
}

function __static_wrapper_setLocaleShortMonthNames(instance, a0){
  $setLocaleShortMonthNames(instance, ($clinit_ExporterUtil() , $toArrString(a0)));
}

function __static_wrapper_setStartDate(instance, a0){
  $setStartDate(instance, fromDouble_0(a0));
}

defineClass(141, 1, {}, GanttElementStateExporterImpl);
var exported_0 = false;
var Lorg_tltv_gantt_client_GanttElementStateExporterImpl_2_classLit = createForClass('org.tltv.gantt.client', 'GanttElementStateExporterImpl', 141, Ljava_lang_Object_2_classLit);
function GanttMouseEventDetails(){
  MouseEventDetails.call(this);
}

function of(details){
  $clinit_MouseEventDetails();
  var instance;
  instance = new GanttMouseEventDetails;
  $setButton(instance, details.button_0);
  $setClientX(instance, details.clientX_0);
  $setClientY(instance, details.clientY_0);
  $setAltKey(instance, details.altKey_0);
  $setCtrlKey(instance, details.ctrlKey_0);
  $setMetaKey(instance, details.metaKey_0);
  $setShiftKey(instance, details.shiftKey_0);
  $setType(instance, details.type_0);
  $setRelativeX(instance, details.relativeX);
  $setRelativeY(instance, details.relativeY);
  return instance;
}

defineClass(177, 111, $intern_9, GanttMouseEventDetails);
var Lorg_tltv_gantt_client_GanttMouseEventDetails_2_classLit = createForClass('org.tltv.gantt.client', 'GanttMouseEventDetails', 177, Lcom_vaadin_shared_MouseEventDetails_2_classLit);
function $setState_0(this$static, state){
  var size_0;
  size_0 = round_int($wnd.Math.round(100 * state));
  this$static.indicator.style['width'] = size_0 + ($clinit_Style$Unit() , '%');
}

function GanttProgressBar(){
  this.wrapper = ($clinit_DOM() , $doc.createElement('div'));
  this.indicator = $doc.createElement('div');
  $setElement_0(this, $doc.createElement('div'));
  $appendChild(this.element, this.wrapper);
  $appendChild(this.wrapper, this.indicator);
  setStylePrimaryName(this.element, 'v-progressbar');
  $setClassName(this.indicator, getStylePrimaryName(this.element) + '-indicator');
  $setClassName(this.wrapper, getStylePrimaryName(this.element) + '-wrapper');
}

defineClass(285, 20, $intern_23, GanttProgressBar);
var Lorg_tltv_gantt_client_GanttProgressBar_2_classLit = createForClass('org.tltv.gantt.client', 'GanttProgressBar', 285, Lcom_google_gwt_user_client_ui_Widget_2_classLit);
function $$init_0(this$static){
  new WidgetCollection(this$static);
  this$static.enabled = true;
  this$static.touchSupported = false;
  this$static.defaultContextMenuEnabled = false;
  this$static.pendingUpdate = false;
  this$static.wasTimelineOverflowingHorizontally = false;
  this$static.wasContentOverflowingVertically = false;
  this$static.extraContentElements = new HashSet;
  this$static.clickOnNextMouseUp = true;
  this$static.secondaryClickOnNextMouseUp = true;
  this$static.insideDoubleClickDetectionInterval = false;
  this$static.numberOfMouseClicksDetected = 0;
  this$static.resizing = false;
  this$static.resizingFromLeft = false;
  this$static.resizingInProgress = false;
  this$static.moveInProgress = false;
  this$static.containerScrollStartPosY = -1;
  this$static.containerScrollStartPosX = -1;
  this$static.moveElement = ($clinit_DOM() , $doc.createElement('div'));
  this$static.disallowClickTimer = new GanttWidget$1(this$static);
  this$static.doubleClickDetectionMaxTimer = new GanttWidget$2(this$static);
  this$static.pointerTouchStartedTimer = new GanttWidget$3(this$static);
  this$static.previousContainerScrollLeft = 0;
  this$static.previousContainerScrollTop = 0;
  this$static.doubleClickHandler = new GanttWidget$4(this$static);
  this$static.mouseDownHandler = new GanttWidget$5(this$static);
  this$static.mouseUpHandler = new GanttWidget$6(this$static);
  this$static.contextMenuHandler = new GanttWidget$7(this$static);
  this$static.mouseMoveHandler = new GanttWidget$8(this$static);
  this$static.msPointerDownHandler = new GanttWidget$9(this$static);
  this$static.msPointerUpHandler = new GanttWidget$10(this$static);
  this$static.msPointerMoveHandler = new GanttWidget$11(this$static);
  this$static.msPointerCancelHandler = new GanttWidget$12(this$static);
  this$static.touchStartHandler = new GanttWidget$13(this$static);
  this$static.touchEndHandler = new GanttWidget$14(this$static);
  this$static.touchMoveHandler = new GanttWidget$15(this$static);
  this$static.touchCancelHandler = new GanttWidget$16(this$static);
}

function $addMovingStyles(this$static, bar){
  if (!bar) {
    return;
  }
  $addClassName(bar, 'moving');
  $updateMoveElementFor(this$static, bar);
}

function $areStepsReady(steps){
  var s, s$iterator;
  for (s$iterator = new ArrayList$1(steps); s$iterator.i < s$iterator.this$01.array.length;) {
    s = castTo($next_0(s$iterator), 14);
    if (s.calculatedHeight == 0) {
      return false;
    }
  }
  return true;
}

function $cancelDoubleClickDetection(this$static){
  this$static.insideDoubleClickDetectionInterval = false;
  this$static.numberOfMouseClicksDetected = 0;
  this$static.previousMouseUpEvent = null;
  this$static.previousMouseUpBarElement = null;
}

function $deferredUpdateContainerStyle(this$static, sw){
  deferred(makeLambdaFunction(GanttWidget$21.prototype.call_0, GanttWidget$21, [this$static]), makeLambdaFunction(GanttWidget$22.prototype.call_0, GanttWidget$22, [sw]));
}

function $doInit_0(this$static){
  if (this$static.container) {
    return;
  }
  $insertAfter(($clinit_DOM() , this$static.element).root, $getElement(this$static.timeline), this$static.element.root.firstChild);
  $setParentElement(this$static.timeline, this$static.element);
  this$static.container = this$static.element.$.ganttContainer;
  this$static.content_0 = this$static.element.$.ganttContent;
  this$static.scrollbarSpacer = $getNextSiblingElement(this$static.element.$.ganttContainer);
  this$static.moveElement = $getFirstChildElement(this$static.element.$.ganttContent);
  this$static.scrollbarSpacer.style['height'] = (maybeRecalculateNativeScrollbarSize() , nativeHeight + ($clinit_Style$Unit() , 'px'));
}

function $findStepElement(this$static, startFromBar, startTopY, startBottomY, newY, deltay){
  var barCanditate, i_0, startIndex, subStep;
  subStep = $isSubBar(startFromBar);
  subStep && (startFromBar = getHost(startFromBar.parentNode));
  if (newY >= startTopY && newY <= startBottomY) {
    return startFromBar;
  }
  startIndex = $getChildIndex_0(this$static.content_0, startFromBar);
  i_0 = startIndex;
  if (deltay > 0) {
    ++i_0;
    for (; i_0 < this$static.content_0.childNodes.length; i_0++) {
      barCanditate = $getChild(this$static.content_0, i_0);
      if ($isBetween(newY, $getAbsoluteTop(barCanditate), $getAbsoluteTop(barCanditate) + ((barCanditate.offsetHeight || 0) | 0))) {
        if (!subStep && i_0 == startIndex + 1) {
          return startFromBar;
        }
        return barCanditate;
      }
    }
  }
   else if (deltay < 0) {
    --i_0;
    for (; i_0 >= ($getParentElement(this$static.moveElement)?1:0) + (!!this$static.bgGrid && !!$getParentElement(this$static.bgGrid.svgElement_0)?1:0) + $size(this$static.extraContentElements.map_0); i_0--) {
      barCanditate = $getChild(this$static.content_0, i_0);
      if ($isBetween(newY, $getAbsoluteTop(barCanditate), $getAbsoluteTop(barCanditate) + ((barCanditate.offsetHeight || 0) | 0))) {
        return barCanditate;
      }
    }
  }
  return startFromBar;
}

function $fireClickRpc(this$static, bar, event_0){
  this$static.enabled && $stepClicked(this$static.ganttRpc, $getStepUid(this$static, bar), event_0, bar);
}

function $getAbstractStepWidget(this$static, stepElement){
  if ($isSubBar(stepElement)) {
    return $getSubStepWidget_0(this$static, stepElement);
  }
  return $getStepWidget_0(this$static, stepElement);
}

function $getBar_0(this$static, event_0){
  var element, parent_0;
  element = getEventTarget(event_0);
  if (!element || $isSvg(element)) {
    return null;
  }
  parent_0 = element;
  while (parent_0 != ($clinit_DOM() , this$static.element) && !!$getParentNode(parent_0) && $getParentNode(parent_0) != this$static.element && $getParentNode(parent_0) != this$static.content_0 && (!(!!parent_0 && parent_0.nodeType == 1) || !$isBar(parent_0))) {
    parent_0 = $getParentNode(parent_0);
  }
  if (!!parent_0 && parent_0.nodeType == 1 && $isBar(parent_0)) {
    return parent_0;
  }
  return null;
}

function $getBgGridCellHeight(this$static){
  var firstBar, firstStepIndex, gridBlockHeightPx;
  gridBlockHeightPx = 0;
  firstStepIndex = ($getParentElement(this$static.moveElement)?1:0) + (!!this$static.bgGrid && !!$getParentElement(this$static.bgGrid.svgElement_0)?1:0) + $size(this$static.extraContentElements.map_0);
  if (firstStepIndex < this$static.content_0.childNodes.length) {
    firstBar = $getChild(this$static.content_0, firstStepIndex);
    gridBlockHeightPx = $getElementHeightWithMargin(firstBar);
    this$static.contentHeight % gridBlockHeightPx != 0 && (gridBlockHeightPx = 0);
  }
  return gridBlockHeightPx;
}

function $getChildIndex_0(parent_0, child){
  return $clinit_DOM() , $getChildIndex(parent_0, child);
}

function $getElementHeightWithMargin(div){
  var height, marginHeight, cs;
  height = div.clientHeight | 0;
  marginHeight = (cs = div.ownerDocument.defaultView.getComputedStyle(div) , cs?(size = parseInt(cs.getPropertyValue('margin-top')) + parseInt(cs.getPropertyValue('margin-bottom'))):(size = 0) , size);
  return height + toInt_0(fromDouble_0($wnd.Math.round(marginHeight)));
}

function $getGanttElement(this$static){
  return $clinit_DOM() , this$static.element;
}

function $getHorizontalScrollbarSpacerHeight(this$static){
  if (this$static.scrollbarSpacer.style['display'].length == 0) {
    return this$static.scrollbarSpacer.clientHeight | 0;
  }
  return 0;
}

function $getLeftPositionPercentageStringForDate(this$static, startDate){
  return $getLeftPositionPercentageStringForDate_0(this$static.timeline, startDate, getBoundingClientRectWidth(this$static.content_0));
}

function $getParentNode(p_0){
  if (!(!!p_0 && p_0.nodeType == 1)) {
    return getHost(p_0);
  }
  return p_0.parentNode;
}

function $getStepIndex(this$static, stepWidget){
  var widgetIndex;
  if (!!stepWidget && !!$getParentElement(($clinit_DOM() , stepWidget.element))) {
    widgetIndex = $indexOf(this$static.children_0, stepWidget);
    return widgetIndex - $size(this$static.extraContentElements.map_0);
  }
  return -1;
}

function $getStepUid(this$static, stepElement){
  var widget;
  if ($isSubBar(stepElement)) {
    return $getSubStepUid(this$static, stepElement);
  }
  widget = $getStepWidget_0(this$static, stepElement);
  if (widget) {
    return castTo(widget.step, 8).uid;
  }
  return null;
}

function $getStepWidget_0(this$static, stepElement){
  var widget;
  widget = $getWidget(this$static, $getChildIndex_0(this$static.content_0, stepElement) - (($getParentElement(this$static.moveElement)?1:0) + (!!this$static.bgGrid && !!$getParentElement(this$static.bgGrid.svgElement_0)?1:0)));
  if (instanceOf(widget, 14)) {
    return castTo(widget, 14);
  }
  return null;
}

function $getSubStepUid(this$static, subStepElement){
  var stepElement, widget;
  stepElement = getHost(subStepElement.parentNode);
  widget = $getStepWidget_0(this$static, stepElement);
  if (widget) {
    return $getStepUidBySubStepElement(widget, subStepElement);
  }
  return null;
}

function $getSubStepWidget_0(this$static, subStepElement){
  var stepElement, widget;
  stepElement = getHost(subStepElement.parentNode);
  widget = $getStepWidget_0(this$static, stepElement);
  if (widget) {
    return $getSubStepWidgetByElement(widget, subStepElement);
  }
  return null;
}

function $getSubstepLeftPositionPercentageStringForDate(this$static, startDate, ownerStartDate, ownerEndDate, bar){
  var ownerStepWidth;
  ownerStepWidth = getBoundingClientRectWidth(getHost(bar.parentNode));
  return $getLeftPositionPercentageStringForDate_1(this$static.timeline, startDate, ownerStepWidth, ownerStartDate, ownerEndDate);
}

function $getSubstepWidthPercentageStringForDateInterval(this$static, startDate, endDate, ownerStartDate, ownerEndDate){
  var range;
  range = toDouble_0(sub_1(ownerEndDate, ownerStartDate));
  return $getWidthPercentageStringForDateInterval_1(this$static.timeline, sub_1(endDate, startDate), range);
}

function $getTimelineHeight(this$static){
  if (this$static.timeline) {
    return $getElement(this$static.timeline).clientHeight | 0;
  }
  return 0;
}

function $getWidthPercentageStringForDateInterval(this$static, startDate, endDate){
  return $getWidthPercentageStringForDateInterval_0(this$static.timeline, sub_1(endDate, startDate));
}

function $hasSubBars(element){
  if ($isSvg(element)) {
    return false;
  }
  return $hasClassName(element, 'has-sub-steps');
}

function $initWidget(this$static, ganttRpc, localeDataProvider){
  this$static.ganttRpc = ganttRpc;
  this$static.localeDataProvider = localeDataProvider;
  $resetListeners(this$static);
}

function $insert_0(this$static, beforeIndex, w){
  $insert_1(this$static, w, this$static.content_0, beforeIndex);
}

function $insert_1(this$static, child, container, beforeIndex){
  var adjustedBeforeStepIndex;
  'Count content elements: ' + this$static.content_0.childNodes.length + ' (' + (($getParentElement(this$static.moveElement)?1:0) + (!!this$static.bgGrid && !!$getParentElement(this$static.bgGrid.svgElement_0)?1:0)) + ' non-widget non-step elements, ' + (($getParentElement(this$static.moveElement)?1:0) + (!!this$static.bgGrid && !!$getParentElement(this$static.bgGrid.svgElement_0)?1:0) + $size(this$static.extraContentElements.map_0) - (($getParentElement(this$static.moveElement)?1:0) + (!!this$static.bgGrid && !!$getParentElement(this$static.bgGrid.svgElement_0)?1:0))) + ' non-step widgets.)';
  adjustedBeforeStepIndex = $adjustIndex(this$static, child, beforeIndex - (($getParentElement(this$static.moveElement)?1:0) + (!!this$static.bgGrid && !!$getParentElement(this$static.bgGrid.svgElement_0)?1:0))) - $size(this$static.extraContentElements.map_0);
  $removeFromParent_0(child);
  $insert(this$static.children_0, child, adjustedBeforeStepIndex + $size(this$static.extraContentElements.map_0));
  insertChild(container, ($clinit_DOM() , child.element), adjustedBeforeStepIndex + (($getParentElement(this$static.moveElement)?1:0) + (!!this$static.bgGrid && !!$getParentElement(this$static.bgGrid.svgElement_0)?1:0) + $size(this$static.extraContentElements.map_0)));
  $setParent(child, this$static);
}

function $insertStep(this$static, stepIndex, stepWidget){
  var insertDOM, moving, newStep;
  newStep = !$getParentElement(($clinit_DOM() , stepWidget.element));
  moving = !newStep && $getStepIndex(this$static, stepWidget) != stepIndex;
  insertDOM = newStep || moving;
  insertDOM && $insert_0(this$static, stepIndex + (($getParentElement(this$static.moveElement)?1:0) + (!!this$static.bgGrid && !!$getParentElement(this$static.bgGrid.svgElement_0)?1:0) + $size(this$static.extraContentElements.map_0)), stepWidget);
}

function $internalHandleWidthChange(this$static){
  $updateWidths(this$static.timeline);
  $updateContainerStyle(this$static);
  this$static.timeline.calcPixels && (this$static.content_0.style['width'] = $getResolutionWidth(this$static.timeline) + ($clinit_Style$Unit() , 'px') , undefined);
}

function $internalMoveOrResizeCompleted(this$static, bar, newPosition, move, event_0){
  var endDate, left, newStepUid, ownerEndDate, ownerLeft, ownerStartDate, startDate, stepUid, subBar, sLeft, ownerStepWidth, sWidth, range, sLeft_0, sWidth_0;
  stepUid = $getStepUid(this$static, bar);
  newStepUid = stepUid;
  !!newPosition && bar != newPosition && (newStepUid = $getStepUid(this$static, newPosition));
  subBar = $isSubBar(bar);
  ownerStartDate = 0;
  ownerEndDate = 0;
  left = $parseSize(bar.style['left']);
  if (subBar) {
    ownerLeft = (getHost(bar.parentNode).offsetLeft || 0) | 0;
    left += ownerLeft;
    ownerStartDate = $getDateForLeftPosition(this$static.timeline, ownerLeft);
    ownerLeft += getBoundingClientRectWidth(getHost(bar.parentNode));
    ownerEndDate = $getDateForLeftPosition(this$static.timeline, ownerLeft);
  }
  startDate = $getDateForLeftPosition(this$static.timeline, left);
  left += getBoundingClientRectWidth(bar);
  endDate = $getDateForLeftPosition(this$static.timeline, left);
  subBar?(sLeft = (ownerStepWidth = getBoundingClientRectWidth(getHost(bar.parentNode)) , $getLeftPositionPercentageStringForDate_1(this$static.timeline, startDate, ownerStepWidth, ownerStartDate, ownerEndDate)) , sWidth = (range = toDouble_0(sub_1(ownerEndDate, ownerStartDate)) , $getWidthPercentageStringForDateInterval_1(this$static.timeline, sub_1(endDate, startDate), range)) , updateStepCustomStyles(bar, sLeft, sWidth) , undefined):(sLeft_0 = $getLeftPositionPercentageStringForDate_0(this$static.timeline, startDate, getBoundingClientRectWidth(this$static.content_0)) , sWidth_0 = $getWidthPercentageStringForDateInterval_0(this$static.timeline, sub_1(endDate, startDate)) , updateStepCustomStyles(bar, sLeft_0, sWidth_0) , undefined);
  if (move) {
    this$static.enabled && this$static.movableSteps && this$static.movableStepsBetweenRows && stepUid == newStepUid && (bar.style['top'] = this$static.capturePointTopPx + ($clinit_Style$Unit() , 'px') , undefined);
    $onMove(this$static.ganttRpc, stepUid, newStepUid, startDate, endDate, event_0, bar);
  }
   else {
    $onResize(this$static.ganttRpc, stepUid, startDate, endDate, event_0, bar);
  }
}

function $isBar(element){
  if ($isSvg(element)) {
    return false;
  }
  return $equalsIgnoreCase(element.tagName, 'gantt-step');
}

function $isBetween(v, min_0, max_0){
  return v >= min_0 && v <= max_0;
}

function $isContentOverflowingHorizontally(this$static){
  if (!this$static.content_0 || !this$static.container || !this$static.timeline) {
    return false;
  }
  return this$static.timeline.timelineOverflowingHorizontally;
}

function $isContentOverflowingVertically(this$static){
  if (!this$static.content_0 || !this$static.container) {
    return false;
  }
  return (this$static.content_0.clientHeight | 0) > (this$static.container.clientHeight | 0);
}

function $isMovableStep(this$static, bar){
  var step;
  if (!(this$static.enabled && this$static.movableSteps)) {
    return false;
  }
  step = $getAbstractStepWidget(this$static, bar);
  return !!step && !!step.getStep() && step.getStep().movable;
}

function $isMovableSteps(this$static){
  return this$static.enabled && this$static.movableSteps;
}

function $isMoveOrResizingInProgress(this$static){
  return this$static.moveInProgress || this$static.resizingInProgress;
}

function $isResizableStep(this$static, bar){
  var step;
  if (!(this$static.enabled && this$static.resizableSteps)) {
    return false;
  }
  step = $getAbstractStepWidget(this$static, bar);
  return !!step && !!step.getStep() && step.getStep().resizable;
}

function $isResizingLeft(this$static, bar){
  if (this$static.movePoint.x_0 <= $getAbsoluteLeft(bar) + 10) {
    return true;
  }
  return false;
}

function $isResizingRight(this$static, bar){
  if (this$static.movePoint.x_0 >= $getAbsoluteLeft(bar) + ((bar.offsetWidth || 0) | 0) + -10) {
    return true;
  }
  return false;
}

function $isSubBar(element){
  if ($isSvg(element)) {
    return false;
  }
  return $hasClassName(element, 'sub-bar');
}

function $isSvg(element){
  if (!isPartOfSvg(element)) {
    return $equalsIgnoreCase('svg', element.tagName) || $equalsIgnoreCase('svg-arrow', element.tagName);
  }
  return true;
}

function $moveCompleted(this$static, bar, y_0, event_0){
  var deltay, newPosition;
  deltay = y_0 - this$static.capturePoint.y_0;
  newPosition = $findStepElement(this$static, bar, round_int(this$static.capturePointAbsTopPx), round_int(this$static.capturePointAbsTopPx + $getElementHeightWithMargin(bar)), y_0, deltay);
  $internalMoveOrResizeCompleted(this$static, bar, newPosition, true, event_0);
}

function $notifyHeightChanged_0(this$static, height){
  var overflow;
  if (!!this$static.container && !!this$static.timeline) {
    $equals_0('', ($clinit_DOM() , this$static.element).style['height'])?(this$static.container.style['height'] = ($clinit_Style$Unit() , '-1.0px') , undefined):(this$static.container.style['height'] = height - $getTimelineHeight(this$static) - $getHorizontalScrollbarSpacerHeight(this$static) + ($clinit_Style$Unit() , 'px') , undefined);
    overflow = $isContentOverflowingVertically(this$static);
    if (this$static.wasContentOverflowingVertically != overflow) {
      this$static.wasContentOverflowingVertically = overflow;
      $setNoticeVerticalScrollbarWidth(this$static.timeline, overflow);
      $internalHandleWidthChange(this$static);
    }
  }
}

function $notifyWidthChanged_0(this$static){
  var overflow;
  if (this$static.timeline) {
    overflow = $checkTimelineOverflowingHorizontally(this$static.timeline);
    if (this$static.timeline.calcPixels || this$static.wasTimelineOverflowingHorizontally != overflow) {
      this$static.wasTimelineOverflowingHorizontally = overflow;
      this$static.wasTimelineOverflowingHorizontally || $setScrollLeft_0(this$static.timeline, 0);
    }
    $internalHandleWidthChange(this$static);
  }
}

function $onCancelTouch(this$static, event_0){
  if (!this$static.targetBarElement) {
    return;
  }
  $resetBarPosition(this$static, this$static.targetBarElement);
  $stopDrag(this$static, event_0);
}

function $onContainerScroll(this$static, event_0){
  var element;
  element = getEventTarget(event_0);
  if (element != this$static.container) {
    return;
  }
  (!instance_0 && (instance_0 = !!$wnd.requestAnimationFrame && !!$wnd.cancelAnimationFrame?new AnimationSchedulerImplStandard:new AnimationSchedulerImplTimer) , instance_0).requestAnimationFrame_0(new GanttWidget$17(this$static), null);
}

function $onTouchOrMouseDown(this$static, event_0){
  var bar;
  if (!!this$static.targetBarElement && (this$static.moveInProgress || this$static.resizingInProgress)) {
    $resetBarPosition(this$static, this$static.targetBarElement);
    $stopDrag(this$static, event_0);
    return;
  }
  bar = $getBar_0(this$static, event_0);
  if (!bar) {
    return;
  }
  this$static.targetBarElement = bar;
  this$static.capturePoint = new Point(event_0.type.indexOf('touch') != -1?(event_0.changedTouches[0].clientX || 0) | 0:(event_0.clientX || 0) | 0, event_0.type.indexOf('touch') != -1?(event_0.changedTouches[0].clientY || 0) | 0:(event_0.clientY || 0) | 0);
  this$static.movePoint = new Point(event_0.type.indexOf('touch') != -1?(event_0.changedTouches[0].clientX || 0) | 0:(event_0.clientX || 0) | 0, event_0.type.indexOf('touch') != -1?(event_0.changedTouches[0].clientY || 0) | 0:(event_0.clientY || 0) | 0);
  this$static.capturePointLeftPercentage = bar.style['left'];
  this$static.capturePointWidthPercentage = bar.style['width'];
  this$static.capturePointLeftPx = (bar.offsetLeft || 0) | 0;
  this$static.capturePointTopPx = (bar.offsetTop || 0) | 0;
  this$static.capturePointAbsTopPx = $getAbsoluteTop(bar);
  this$static.capturePointWidthPx = bar.clientWidth | 0;
  this$static.capturePointBgColor = bar.style['backgroundColor'];
  if ($isResizableStep(this$static, bar) && !$hasSubBars(bar) && ($isResizingLeft(this$static, bar) || $isResizingRight(this$static, bar))) {
    this$static.resizing = true;
    this$static.resizingFromLeft = $isResizingLeft(this$static, bar);
  }
   else {
    this$static.resizing = false;
  }
  $schedule(this$static.disallowClickTimer, 250);
  this$static.insideDoubleClickDetectionInterval = true;
  $schedule(this$static.doubleClickDetectionMaxTimer, 250);
  event_0.stopPropagation();
}

function $onTouchOrMouseMove(this$static, event_0){
  var bar, deltax, deltay;
  bar = $getBar_0(this$static, event_0);
  if (bar) {
    this$static.movePoint = new Point(event_0.type.indexOf('touch') != -1?(event_0.changedTouches[0].clientX || 0) | 0:(event_0.clientX || 0) | 0, event_0.type.indexOf('touch') != -1?(event_0.changedTouches[0].clientY || 0) | 0:(event_0.clientY || 0) | 0);
    $showResizingPointer(bar, $isResizableStep(this$static, bar) && !$hasSubBars(bar) && ($isResizingLeft(this$static, bar) || $isResizingRight(this$static, bar)));
  }
  if (!this$static.targetBarElement) {
    return false;
  }
  bar = this$static.targetBarElement;
  $cancel(this$static.doubleClickDetectionMaxTimer);
  $cancel(this$static.disallowClickTimer);
  this$static.clickOnNextMouseUp = false;
  $cancelDoubleClickDetection(this$static);
  deltax = (event_0.type.indexOf('touch') != -1?(event_0.changedTouches[0].clientX || 0) | 0:(event_0.clientX || 0) | 0) - this$static.capturePoint.x_0;
  deltay = (event_0.type.indexOf('touch') != -1?(event_0.changedTouches[0].clientY || 0) | 0:(event_0.clientY || 0) | 0) - this$static.capturePoint.y_0;
  if (this$static.resizing) {
    this$static.resizingInProgress = deltax != 0;
    this$static.resizingFromLeft?$updateBarResizingLeft(this$static, bar, deltax):$updateBarResizingRight(this$static, bar, deltax);
    $addClassName(bar, 'resizing');
    $updateMoveElementFor(this$static, bar);
    bar.style['backgroundColor'] = '';
  }
   else if ($isMovableStep(this$static, bar)) {
    this$static.moveInProgress = deltax != 0 || this$static.enabled && this$static.movableSteps && this$static.movableStepsBetweenRows && $wnd.Math.abs(deltay) > 3;
    bar.style['left'] = this$static.capturePointLeftPx + deltax + ($clinit_Style$Unit() , 'px');
    $addMovingStyles(this$static, bar);
    bar.style['backgroundColor'] = '';
    this$static.enabled && this$static.movableSteps && this$static.movableStepsBetweenRows && $updateBarYPosition(this$static, bar, deltay);
  }
  return true;
}

function $onTouchOrMouseUp(this$static, event_0){
  var bar;
  if (!this$static.targetBarElement) {
    return;
  }
  bar = $getBar_0(this$static, event_0);
  $cancel(this$static.disallowClickTimer);
  if (bar == this$static.targetBarElement && this$static.clickOnNextMouseUp) {
    this$static.clickOnNextMouseUp = true;
    if (this$static.insideDoubleClickDetectionInterval) {
      ++this$static.numberOfMouseClicksDetected;
      this$static.previousMouseUpEvent = event_0;
      this$static.previousMouseUpBarElement = bar;
    }
     else {
      this$static.enabled && $stepClicked(this$static.ganttRpc, $getStepUid(this$static, bar), event_0, bar);
    }
  }
   else {
    this$static.clickOnNextMouseUp = true;
    bar = this$static.targetBarElement;
    if (this$static.resizing) {
      $removeClassName(bar, 'resizing');
      this$static.resizingInProgress?$internalMoveOrResizeCompleted(this$static, bar, null, false, event_0):$resetBarPosition(this$static, bar);
    }
     else if ($isMovableStep(this$static, bar)) {
      $removeClassName(bar, 'moving');
      this$static.moveInProgress?$moveCompleted(this$static, bar, event_0.type.indexOf('touch') != -1?(event_0.changedTouches[0].clientY || 0) | 0:(event_0.clientY || 0) | 0, event_0):$resetBarPosition(this$static, bar);
    }
    bar.style['backgroundColor'] = this$static.capturePointBgColor;
  }
  $stopDrag(this$static, event_0);
}

function $parseSize(size_0){
  if (size_0 == null || size_0.length == 0 || $equals_0('0', size_0) || $equals_0('0.0', size_0)) {
    return 0;
  }
  return __parseAndValidateDouble($substring_0(size_0, 0, size_0.length - 'px'.length));
}

function $ready_0(this$static, f){
  whenReadyAndConnected(f, ($clinit_DOM() , this$static.element));
}

function $registerContentElement(this$static, widget){
  $add_3(this$static.extraContentElements, widget) && ($removeFromParent_0(widget) , $insert(this$static.children_0, widget, 0) , $insertFirst(this$static.content_0, ($clinit_DOM() , widget.element)) , $setParent(widget, this$static) , undefined);
}

function $remove_12(this$static, w){
  var height, startIndex;
  if (instanceOf(w, 14)) {
    $indexOf(this$static.children_0, w);
    height = $getElementHeightWithMargin($getBar(castTo(w, 14)));
    this$static.contentHeight -= height;
    castTo(w, 14).calculatedHeight = 0;
    if ((startIndex = $removeAndReturnIndex(this$static, w)) >= 0) {
      $updateTopForAllStepsBelow(this$static, startIndex, -height);
      this$static.content_0.style['height'] = this$static.contentHeight + ($clinit_Style$Unit() , 'px');
      return true;
    }
    return false;
  }
   else {
    return $remove(this$static, w);
  }
}

function $removeAndReturnIndex(this$static, w){
  var elem, index_0;
  if (w.parent_0 != this$static) {
    return -1;
  }
  try {
    $setParent(w, null);
  }
   finally {
    index_0 = $indexOf(this$static.children_0, w);
    elem = ($clinit_DOM() , w.element);
    $removeChild(this$static.content_0, elem);
    $remove_1(this$static.children_0, w);
  }
  return index_0;
}

function $requestUpdate(this$static, steps){
  if ($areStepsReady(steps)) {
    $update(this$static, steps);
    return;
  }
  this$static.pendingUpdateSteps = makeLambdaFunction(GanttWidget$20.prototype.call_0, GanttWidget$20, [steps]);
  this$static.pendingUpdate = true;
}

function $resetBarPosition(this$static, bar){
  bar.style['backgroundColor'] = this$static.capturePointBgColor;
  bar.style['left'] = this$static.capturePointLeftPercentage;
  bar.style['width'] = this$static.capturePointWidthPercentage;
  bar.style['top'] = this$static.capturePointTopPx + ($clinit_Style$Unit() , 'px');
}

function $resetListeners(this$static){
  sinkEvents_0(this$static.container);
  !this$static.contextMenuHandlerRegistration && (this$static.contextMenuHandlerRegistration = $addDomHandler(this$static, this$static.contextMenuHandler, ($clinit_ContextMenuEvent() , $clinit_ContextMenuEvent() , TYPE)));
  registerContainerScrollHandler(($clinit_DOM() , this$static.element), makeLambdaFunction(GanttWidget$23.prototype.call_0, GanttWidget$23, [this$static]));
  if (navigator.maxTouchPoints > 0) {
    !this$static.pointerDownHandlerRegistration && (this$static.pointerDownHandlerRegistration = $addDomHandler(this$static, this$static.msPointerDownHandler, ($clinit_PointerDownEvent() , $clinit_PointerDownEvent() , TYPE_11)));
    !this$static.pointerUpHandlerRegistration && (this$static.pointerUpHandlerRegistration = $addDomHandler(this$static, this$static.msPointerUpHandler, ($clinit_PointerUpEvent() , $clinit_PointerUpEvent() , TYPE_13)));
    !this$static.pointerMoveHandlerRegistration && (this$static.pointerMoveHandlerRegistration = $addDomHandler(this$static, this$static.msPointerMoveHandler, ($clinit_PointerMoveEvent() , $clinit_PointerMoveEvent() , TYPE_12)));
    !this$static.pointerCancelHandlerRegistration && (this$static.pointerCancelHandlerRegistration = $addHandler_0(this$static, this$static.msPointerCancelHandler, ($clinit_PointerCancelEvent() , $clinit_PointerCancelEvent() , TYPE_10)));
  }
   else if (this$static.touchSupported) {
    !this$static.touchStartHandlerRegistration && (this$static.touchStartHandlerRegistration = $addDomHandler(this$static, this$static.touchStartHandler, ($clinit_TouchStartEvent() , $clinit_TouchStartEvent() , TYPE_7)));
    !this$static.touchEndHandlerRegistration && (this$static.touchEndHandlerRegistration = $addDomHandler(this$static, this$static.touchEndHandler, ($clinit_TouchEndEvent() , $clinit_TouchEndEvent() , TYPE_5)));
    !this$static.touchMoveHandlerRegistration && (this$static.touchMoveHandlerRegistration = $addDomHandler(this$static, this$static.touchMoveHandler, ($clinit_TouchMoveEvent() , $clinit_TouchMoveEvent() , TYPE_6)));
    !this$static.touchCancelHandlerRegistration && (this$static.touchCancelHandlerRegistration = $addHandler_0(this$static, this$static.touchCancelHandler, ($clinit_TouchCancelEvent() , $clinit_TouchCancelEvent() , TYPE_4)));
  }
   else {
    !this$static.mouseDblClickHandlerRegistration && (this$static.mouseDblClickHandlerRegistration = $addDomHandler(this$static, this$static.doubleClickHandler, ($clinit_DoubleClickEvent() , $clinit_DoubleClickEvent() , TYPE_0)));
    !this$static.mouseDownHandlerRegistration && (this$static.mouseDownHandlerRegistration = $addDomHandler(this$static, this$static.mouseDownHandler, ($clinit_MouseDownEvent() , $clinit_MouseDownEvent() , TYPE_1)));
    !this$static.mouseUpHandlerRegistration && (this$static.mouseUpHandlerRegistration = $addDomHandler(this$static, this$static.mouseUpHandler, ($clinit_MouseUpEvent() , $clinit_MouseUpEvent() , TYPE_3)));
    if (this$static.enabled && this$static.movableSteps || this$static.enabled && this$static.resizableSteps) {
      !this$static.mouseMoveHandlerRegistration && (this$static.mouseMoveHandlerRegistration = $addDomHandler(this$static, this$static.mouseMoveHandler, ($clinit_MouseMoveEvent() , $clinit_MouseMoveEvent() , TYPE_2)));
    }
     else if (this$static.mouseMoveHandlerRegistration) {
      $removeHandler(this$static.mouseMoveHandlerRegistration.real);
      this$static.mouseMoveHandlerRegistration = null;
    }
  }
}

function $setAlwaysCalculatePixelWidths(this$static, calcPx){
  $setAlwaysCalculatePixelWidths_0(this$static.timeline, calcPx);
}

function $setBackgroundGridEnabled_0(this$static, backgroundGridEnabled){
  this$static.backgroundGridEnabled = backgroundGridEnabled;
}

function $setBrowserInfo(this$static, ie, chrome_0, safari, webkit){
  this$static.ie = ie;
  this$static.chrome_0 = chrome_0;
  this$static.safari = safari;
  this$static.webkit = webkit;
  $setBrowserInfo_0(this$static.timeline, ie);
}

function $setContentMinWidth(this$static, minWidth){
  this$static.minWidth = minWidth;
  this$static.content_0.style['minWidth'] = this$static.minWidth + 'px';
}

function $setDayFormat_0(this$static, dayFormat){
  $setDayFormat_1(this$static.timeline, dayFormat);
}

function $setDefaultContextMenuEnabled_0(this$static, defaultContextMenuEnabled){
  this$static.defaultContextMenuEnabled = defaultContextMenuEnabled;
}

function $setEndDate_0(this$static, endDate){
  this$static.endDate = endDate?endDate.value_0:0;
}

function $setFirstDayOfRange_0(this$static, firstDayOfRange){
  this$static.firstDayOfRange = firstDayOfRange;
}

function $setFirstHourOfRange_0(this$static, firstHourOfRange){
  this$static.firstHourOfRange = firstHourOfRange;
}

function $setForceUpdateTimeline(this$static){
  if (!this$static.timeline) {
    return;
  }
  this$static.timeline.forceUpdateFlag = true;
}

function $setMonthFormat_0(this$static, monthFormat){
  $setMonthFormat_1(this$static.timeline, monthFormat);
}

function $setMonthRowVisible_0(this$static, monthRowVisible){
  $setMonthRowVisible_1(this$static.timeline, monthRowVisible);
}

function $setMovableSteps_0(this$static, movableSteps){
  this$static.movableSteps = movableSteps;
}

function $setMovableStepsBetweenRows_0(this$static, movableStepsBetweenRows){
  this$static.movableStepsBetweenRows = movableStepsBetweenRows;
}

function $setResizableSteps_0(this$static, resizableSteps){
  this$static.resizableSteps = resizableSteps;
}

function $setResolution_0(this$static, resolution){
  this$static.resolution = resolution;
}

function $setStartDate_0(this$static, startDate){
  this$static.startDate = startDate?startDate.value_0:0;
}

function $setStep_0(this$static, stepIndex, stepWidget, updateAffectedSteps){
  var bar, newStep;
  newStep = !$getParentElement(($clinit_DOM() , stepWidget.element));
  !newStep && $getStepIndex(this$static, stepWidget) != stepIndex;
  bar = stepWidget.element;
  $scheduleDeferred(($clinit_SchedulerImpl() , INSTANCE), new GanttWidget$19(this$static, stepIndex, updateAffectedSteps, bar, stepWidget));
}

function $setTouchSupported(this$static, touchSupported){
  this$static.touchSupported = touchSupported;
}

function $setWeekFormat_0(this$static, weekFormat){
  $setWeekFormat_1(this$static.timeline, weekFormat);
}

function $setYearFormat_0(this$static, yearFormat){
  $setYearFormat_1(this$static.timeline, yearFormat);
}

function $setYearRowVisible_0(this$static, yearRowVisible){
  $setYearRowVisible_1(this$static.timeline, yearRowVisible);
}

function $showResizingPointer(bar, showPointer){
  showPointer?(bar.style['cursor'] = ($clinit_Style$Cursor() , 'e-resize') , undefined):(bar.style['cursor'] = '' , undefined);
}

function $stopDrag(this$static, event_0){
  this$static.moveElement.style['display'] = ($clinit_Style$Display() , 'none');
  this$static.targetBarElement = null;
  this$static.capturePoint = null;
  this$static.resizing = false;
  this$static.resizingInProgress = false;
  this$static.moveInProgress = false;
  event_0.stopPropagation();
}

function $unregisterContentElement(this$static, widget){
  if (widget) {
    $remove_7(this$static.extraContentElements, widget);
    $removeFromParent_0(widget);
  }
}

function $update(this$static, steps){
  if (lt(this$static.startDate, 0) || lt(this$static.endDate, 0) || gte_0(this$static.startDate, this$static.endDate)) {
    return;
  }
  this$static.content_0.style['height'] = this$static.contentHeight + ($clinit_Style$Unit() , 'px');
  $setNoticeVerticalScrollbarWidth(this$static.timeline, $isContentOverflowingVertically(this$static));
  $update_0(this$static.timeline, this$static.resolution, this$static.startDate, this$static.endDate, this$static.firstDayOfRange, this$static.firstHourOfRange, this$static.localeDataProvider);
  $setContentMinWidth(this$static, this$static.timeline.minWidth);
  $updateContainerHeight(this$static, $getPropertyInt(($clinit_DOM() , this$static.element), 'offsetHeight'));
  steps.array.length > 0?$deferredUpdateContainerStyle(this$static, (checkCriticalElementIndex(0, steps.array.length) , castTo(steps.array[0], 14))):$updateContainerStyle(this$static);
  this$static.timeline.calcPixels && (this$static.content_0.style['width'] = $getResolutionWidth(this$static.timeline) + 'px' , undefined);
  $updateStepWidths(steps);
  this$static.wasTimelineOverflowingHorizontally = this$static.timeline.timelineOverflowingHorizontally;
}

function $updateBarResizingLeft(this$static, bar, deltax){
  var newLeft, newWidth;
  newLeft = this$static.capturePointLeftPx + deltax;
  newWidth = this$static.capturePointWidthPx - deltax;
  if (newWidth >= 10) {
    bar.style['left'] = newLeft + ($clinit_Style$Unit() , 'px');
    bar.style['width'] = newWidth + 'px';
  }
}

function $updateBarResizingRight(this$static, bar, deltax){
  var newWidth;
  newWidth = this$static.capturePointWidthPx + deltax;
  if (newWidth >= 10) {
    bar.style['left'] = this$static.capturePointLeftPx + ($clinit_Style$Unit() , 'px');
    bar.style['width'] = newWidth + 'px';
  }
}

function $updateBarYPosition(this$static, bar, deltay){
  var barHeight, barTop, deltaTop, maxDeltaDown, maxDeltaUp, movementFromTop, offsetY, stepElement;
  barHeight = $getElementHeightWithMargin(bar);
  offsetY = 0;
  if ($isSubBar(bar)) {
    stepElement = getHost(bar.parentNode);
    offsetY = $parseSize(stepElement.style['top']);
  }
  barTop = $parseSize(bar.style['top']) + offsetY;
  movementFromTop = this$static.capturePointTopPx + offsetY + deltay;
  deltaTop = movementFromTop - barTop;
  maxDeltaUp = this$static.capturePoint.y_0 - this$static.capturePointAbsTopPx;
  maxDeltaDown = barHeight - maxDeltaUp;
  deltaTop <= -maxDeltaUp?barTop - barHeight >= 0 && (bar.style['top'] = barTop - barHeight - offsetY + ($clinit_Style$Unit() , 'px') , undefined):deltaTop >= maxDeltaDown && (bar.style['top'] = barTop + barHeight - offsetY + ($clinit_Style$Unit() , 'px') , undefined);
}

function $updateContainerBackgroundPosition(this$static, firstResolutionBlockWidth, contentOverflowingHorizontally, gridBlockWidthPx, adjustBgPosition){
  var bgPosX, realBgPosXPx, relativeBgAreaWidth, timelineWidth;
  if (adjustBgPosition) {
    realBgPosXPx = firstResolutionBlockWidth - 1;
    if (this$static.ie || this$static.chrome_0 || this$static.safari || this$static.webkit || contentOverflowingHorizontally) {
      $setBackgroundPosition(this$static.bgGrid, ($toCssCalcOrNumberString(this$static.timeline, realBgPosXPx, 'px') , realBgPosXPx));
    }
     else {
      timelineWidth = $calculateTimelineWidth(this$static.timeline);
      relativeBgAreaWidth = timelineWidth - gridBlockWidthPx;
      bgPosX = 100 / relativeBgAreaWidth * realBgPosXPx;
      $setBackgroundPosition(this$static.bgGrid, ($toCssCalcOrNumberString(this$static.timeline, bgPosX, '%') , realBgPosXPx));
    }
  }
   else {
    $setBackgroundPosition(this$static.bgGrid, -1);
  }
}

function $updateContainerBackgroundSize(this$static, contentOverflowingHorizontally, gridBlockWidthPx){
  var contentWidth, gridBlockHeightPx;
  if (contentOverflowingHorizontally || this$static.ie || this$static.chrome_0 || this$static.safari || this$static.webkit) {
    $toCssCalcOrNumberString(this$static.timeline, gridBlockWidthPx, 'px');
  }
   else {
    contentWidth = getBoundingClientRectWidth(this$static.content_0);
    $toCssCalcOrNumberString(this$static.timeline, 100 / contentWidth * gridBlockWidthPx, '%');
  }
  gridBlockHeightPx = $getBgGridCellHeight(this$static);
  $setBackgroundSize(this$static.bgGrid, gridBlockWidthPx, gridBlockHeightPx);
}

function $updateContainerHeight(this$static, height){
  $equals_0('', ($clinit_DOM() , this$static.element).style['height'])?(this$static.container.style['height'] = ($clinit_Style$Unit() , '-1.0px') , undefined):(this$static.container.style['height'] = height - $getTimelineHeight(this$static) - $getHorizontalScrollbarSpacerHeight(this$static) + ($clinit_Style$Unit() , 'px') , undefined);
}

function $updateContainerStyle(this$static){
  var adjustBgPosition, contentOverflowingHorizontally, firstResolutionBlockWidth, gridBlockWidthPx, resDivElementCount, secondResolutionBlock, secondResolutionBlockWidth, grid;
  if (this$static.pendingUpdate) {
    return;
  }
  !this$static.bgGrid && (this$static.bgGrid = (grid = new BgGridSvgElement , $init(grid, this$static.content_0) , grid));
  if (this$static.backgroundGridEnabled) {
    $show(this$static.bgGrid);
  }
   else {
    $removeFromParent(this$static.bgGrid.svgElement_0);
    return;
  }
  resDivElementCount = this$static.timeline.resolutionDiv.childNodes.length;
  if (resDivElementCount == 0) {
    return;
  }
  firstResolutionBlockWidth = $getFirstResolutionElementWidth(this$static.timeline);
  if (firstResolutionBlockWidth == null) {
    return;
  }
  secondResolutionBlockWidth = null;
  if (resDivElementCount > 2) {
    secondResolutionBlock = this$static.timeline.resolutionDiv.childNodes[1];
    secondResolutionBlockWidth = getBoundingClientRectWidth(secondResolutionBlock);
  }
  contentOverflowingHorizontally = $isContentOverflowingHorizontally(this$static);
  adjustBgPosition = secondResolutionBlockWidth != null && (checkCriticalNotNull(firstResolutionBlockWidth) , firstResolutionBlockWidth != secondResolutionBlockWidth);
  adjustBgPosition?(gridBlockWidthPx = (checkCriticalNotNull(secondResolutionBlockWidth) , secondResolutionBlockWidth)):(gridBlockWidthPx = (checkCriticalNotNull(firstResolutionBlockWidth) , firstResolutionBlockWidth));
  $updateContainerBackgroundSize(this$static, contentOverflowingHorizontally, gridBlockWidthPx);
  $updateContainerBackgroundPosition(this$static, (checkCriticalNotNull(firstResolutionBlockWidth) , firstResolutionBlockWidth), contentOverflowingHorizontally, gridBlockWidthPx, adjustBgPosition);
}

function $updateIfReady(this$static){
  if (!this$static.pendingUpdate) {
    return;
  }
  if (!this$static.pendingUpdateSteps) {
    return;
  }
  if (!$areStepsReady(this$static.pendingUpdateSteps.val$steps2)) {
    return;
  }
  this$static.pendingUpdate = false;
  $update(this$static, this$static.pendingUpdateSteps.val$steps2);
  $notifySizeChanged(this$static.ganttRpc.this$01);
}

function $updateMoveElementFor(this$static, target){
  var left, styleLeft, suffixlength;
  !target && (this$static.moveElement.style['display'] = ($clinit_Style$Display() , 'none') , undefined);
  this$static.moveElement.style['display'] = '';
  styleLeft = target.style['left'];
  left = this$static.capturePointLeftPx;
  styleLeft != null && styleLeft.length > 2 && (suffixlength = 'px'.length , $equals_0(styleLeft.substr(styleLeft.length - suffixlength, suffixlength), 'px')) && (left = $parseSize(styleLeft));
  $isSubBar(target) && (left += (getHost(target.parentNode).offsetLeft || 0) | 0);
  this$static.moveElement.style['left'] = left + 'px';
  this$static.moveElement.style['width'] = (target.clientWidth | 0) + 'px';
}

function $updateStepTop(this$static, stepIndex, updateAffectedSteps, bar, stepWidget){
  var indexInWidgetContainer, newHeight, prevWidgetIndex, sWidget, stepsInContainer, top_0, w;
  stepsInContainer = this$static.children_0.size_0 - $size(this$static.extraContentElements.map_0);
  indexInWidgetContainer = stepIndex + $size(this$static.extraContentElements.map_0);
  newHeight = $getElementHeightWithMargin(bar);
  if (stepIndex == 0) {
    bar.style['top'] = ($clinit_Style$Unit() , '0.0px');
    updateAffectedSteps && $updateTopForAllStepsBelow(this$static, indexInWidgetContainer + 1, newHeight);
  }
   else if (stepIndex < stepsInContainer) {
    prevWidgetIndex = indexInWidgetContainer - 1;
    w = $get_1(this$static.children_0, prevWidgetIndex);
    if (instanceOf(w, 14)) {
      sWidget = castTo(w, 14);
      top_0 = $parseSize(($clinit_DOM() , sWidget.element).style['top']);
      top_0 += $getElementHeightWithMargin(sWidget.element);
      bar.style['top'] = top_0 + ($clinit_Style$Unit() , 'px');
      updateAffectedSteps && $updateTopForAllStepsBelow(this$static, indexInWidgetContainer + 1, newHeight);
    }
  }
  this$static.contentHeight = this$static.contentHeight - stepWidget.calculatedHeight + newHeight;
  stepWidget.calculatedHeight = newHeight;
  $updateIfReady(this$static);
}

function $updateStepWidths(steps){
  var step, step$iterator;
  for (step$iterator = new ArrayList$1(steps); step$iterator.i < step$iterator.this$01.array.length;) {
    step = castTo($next_0(step$iterator), 14);
    $updateWidth_0(step);
  }
}

function $updateTopForAllStepsBelow(this$static, startIndex, delta){
  var elementBelow, i_0, top_0, w;
  for (i_0 = startIndex; i_0 < this$static.children_0.size_0; i_0++) {
    w = $get_1(this$static.children_0, i_0);
    instanceOf(w, 14)?(elementBelow = $getBar(castTo(w, 14))):(elementBelow = $getElement($get_1(this$static.children_0, i_0)));
    top_0 = $parseSize(elementBelow.style['top']);
    elementBelow.style['top'] = top_0 + delta + ($clinit_Style$Unit() , 'px');
  }
}

function GanttWidget(){
  CustomHTMLPanel.call(this, 'gantt-widget');
  ensureCustomElement(($clinit_DOM() , this.element), stampJavaTypeInfo(getClassLiteralForArray(Ljava_lang_String_2_classLit, 1), $intern_0, 2, 6, ['../gantt-widget.html']));
  $$init_0(this);
  this.timeline = new TimelineWidget;
  $ready_0(this, makeLambdaFunction(GanttWidget$18.prototype.call_0, GanttWidget$18, [this]));
}

function GanttWidget_0(jselement){
  PolymerWidget.call(this, jselement);
  $$init_0(this);
  this.timeline = new TimelineWidget;
  $ready_0(this, makeLambdaFunction(GanttWidget$18.prototype.call_0, GanttWidget$18, [this]));
}

function isPartOfSvg(element){
  if (element.ownerSVGElement) {
    return true;
  }
  return false;
}

function registerContainerScrollHandler(elem, f){
  return elem.registerContainerScrollHandler(f);
}

function updateStepCustomStyles(e, left, width_0){
  e.updateStyles({'--gantt-step-left':left, '--gantt-step-width':width_0});
}

defineClass(110, 65, $intern_23, GanttWidget, GanttWidget_0);
_.onAttach = function onAttach_0(){
  $onAttach(this);
}
;
_.remove_0 = function remove_28(w){
  return $remove_12(this, w);
}
;
_.backgroundGridEnabled = false;
_.capturePointAbsTopPx = 0;
_.capturePointLeftPx = 0;
_.capturePointTopPx = 0;
_.capturePointWidthPx = 0;
_.chrome_0 = false;
_.clickOnNextMouseUp = false;
_.containerScrollStartPosX = 0;
_.containerScrollStartPosY = 0;
_.contentHeight = 0;
_.currentPointerEventId = 0;
_.defaultContextMenuEnabled = false;
_.enabled = false;
_.endDate = 0;
_.firstDayOfRange = 0;
_.firstHourOfRange = 0;
_.ie = false;
_.insideDoubleClickDetectionInterval = false;
_.minWidth = 0;
_.movableSteps = false;
_.movableStepsBetweenRows = false;
_.moveInProgress = false;
_.numberOfMouseClicksDetected = 0;
_.pendingUpdate = false;
_.previousContainerScrollLeft = 0;
_.previousContainerScrollTop = 0;
_.resizableSteps = false;
_.resizing = false;
_.resizingFromLeft = false;
_.resizingInProgress = false;
_.safari = false;
_.secondaryClickOnNextMouseUp = false;
_.startDate = 0;
_.touchSupported = false;
_.wasContentOverflowingVertically = false;
_.wasTimelineOverflowingHorizontally = false;
_.webkit = false;
var Lorg_tltv_gantt_client_GanttWidget_2_classLit = createForClass('org.tltv.gantt.client', 'GanttWidget', 110, Lorg_tltv_gantt_client_PolymerWidget_2_classLit);
function GanttWidget$1(this$0){
  this.this$01 = this$0;
  Timer.call(this);
}

defineClass(158, 48, {}, GanttWidget$1);
_.run = function run_1(){
  this.this$01.clickOnNextMouseUp = false;
  $isMovableSteps(this.this$01) && !this.this$01.resizingInProgress && $addMovingStyles(this.this$01, this.this$01.targetBarElement);
}
;
var Lorg_tltv_gantt_client_GanttWidget$1_2_classLit = createForClass('org.tltv.gantt.client', 'GanttWidget/1', 158, Lcom_google_gwt_user_client_Timer_2_classLit);
function GanttWidget$10(this$0){
  this.this$01 = this$0;
}

defineClass(168, 1, $intern_30, GanttWidget$10);
_.onPointerUp = function onPointerUp(event_0){
  this.this$01.currentPointerEventId = -1;
  $cancel(this.this$01.pointerTouchStartedTimer);
  this.this$01.pendingPointerDownEvent = null;
  $onTouchOrMouseUp(this.this$01, event_0.nativeEvent);
  !!event_0.nativeEvent && (event_0.nativeEvent.preventDefault() , undefined);
}
;
var Lorg_tltv_gantt_client_GanttWidget$10_2_classLit = createForClass('org.tltv.gantt.client', 'GanttWidget/10', 168, Ljava_lang_Object_2_classLit);
function GanttWidget$11(this$0){
  this.this$01 = this$0;
}

defineClass(169, 1, $intern_31, GanttWidget$11);
_.onPointerMove = function onPointerMove(event_0){
  if (!this.this$01.capturePoint) {
    return;
  }
  this.this$01.movePoint = new Point(getTouchOrMouseClientX(event_0.nativeEvent), getTouchOrMouseClientY(event_0.nativeEvent));
  this.this$01.capturePoint.x_0 == this.this$01.movePoint.x_0 && this.this$01.capturePoint.y_0 == this.this$01.movePoint.y_0 || $onTouchOrMouseMove(this.this$01, event_0.nativeEvent);
}
;
var Lorg_tltv_gantt_client_GanttWidget$11_2_classLit = createForClass('org.tltv.gantt.client', 'GanttWidget/11', 169, Ljava_lang_Object_2_classLit);
function GanttWidget$12(this$0){
  this.this$01 = this$0;
}

defineClass(170, 1, $intern_32, GanttWidget$12);
_.onPointerCancel = function onPointerCancel(event_0){
  this.this$01.currentPointerEventId = -1;
  $cancel(this.this$01.pointerTouchStartedTimer);
  this.this$01.pendingPointerDownEvent = null;
  $onCancelTouch(this.this$01, event_0.nativeEvent);
}
;
var Lorg_tltv_gantt_client_GanttWidget$12_2_classLit = createForClass('org.tltv.gantt.client', 'GanttWidget/12', 170, Ljava_lang_Object_2_classLit);
function GanttWidget$13(this$0){
  this.this$01 = this$0;
}

defineClass(171, 1, $intern_33, GanttWidget$13);
_.onTouchStart = function onTouchStart(event_0){
  var preventDefaultAndReturn, target, target_0;
  if (event_0.nativeEvent.targetTouches.length == 1) {
    target = (target_0 = event_0.nativeEvent.target , target_0 && target_0.nodeType == 3 && (target_0 = target_0.parentNode) , target_0);
    this.this$01.containerScrollStartPosY = -1;
    this.this$01.containerScrollStartPosX = -1;
    if (target == this.this$01.container || target == this.this$01.content_0 || !$isMovableSteps(this.this$01)) {
      preventDefaultAndReturn = false;
      if ($isContentOverflowingVertically(this.this$01)) {
        this.this$01.containerScrollStartPosY = ((this.this$01.container.scrollTop || 0) | 0) + ((event_0.nativeEvent.touches[0].pageY || 0) | 0);
        preventDefaultAndReturn = true;
      }
      if ($isContentOverflowingHorizontally(this.this$01)) {
        this.this$01.containerScrollStartPosX = $getScrollLeft_0(this.this$01.container) + ((event_0.nativeEvent.touches[0].pageX || 0) | 0);
        preventDefaultAndReturn = true;
      }
      if (preventDefaultAndReturn) {
        !!event_0.nativeEvent && (event_0.nativeEvent.preventDefault() , undefined);
        return;
      }
    }
    $onTouchOrMouseDown(this.this$01, event_0.nativeEvent);
  }
  !!event_0.nativeEvent && (event_0.nativeEvent.preventDefault() , undefined);
}
;
var Lorg_tltv_gantt_client_GanttWidget$13_2_classLit = createForClass('org.tltv.gantt.client', 'GanttWidget/13', 171, Ljava_lang_Object_2_classLit);
function GanttWidget$14(this$0){
  this.this$01 = this$0;
}

defineClass(172, 1, {354:1, 15:1}, GanttWidget$14);
var Lorg_tltv_gantt_client_GanttWidget$14_2_classLit = createForClass('org.tltv.gantt.client', 'GanttWidget/14', 172, Ljava_lang_Object_2_classLit);
function GanttWidget$15(this$0){
  this.this$01 = this$0;
}

defineClass(173, 1, $intern_34, GanttWidget$15);
_.onTouchMove = function onTouchMove(event_0){
  var preventDefaultAndReturn;
  if (event_0.nativeEvent.changedTouches.length == 1) {
    preventDefaultAndReturn = false;
    if (this.this$01.containerScrollStartPosY != -1) {
      $setScrollTop(this.this$01.container, this.this$01.containerScrollStartPosY - ((event_0.nativeEvent.changedTouches[0].pageY || 0) | 0));
      preventDefaultAndReturn = true;
    }
    if (this.this$01.containerScrollStartPosX != -1) {
      $setScrollLeft(this.this$01.container, this.this$01.containerScrollStartPosX - ((event_0.nativeEvent.changedTouches[0].pageX || 0) | 0));
      preventDefaultAndReturn = true;
    }
    if (preventDefaultAndReturn) {
      !!event_0.nativeEvent && (event_0.nativeEvent.preventDefault() , undefined);
      return;
    }
    $onTouchOrMouseMove(this.this$01, event_0.nativeEvent) && !!event_0.nativeEvent && (event_0.nativeEvent.preventDefault() , undefined);
  }
}
;
var Lorg_tltv_gantt_client_GanttWidget$15_2_classLit = createForClass('org.tltv.gantt.client', 'GanttWidget/15', 173, Ljava_lang_Object_2_classLit);
function GanttWidget$16(this$0){
  this.this$01 = this$0;
}

defineClass(174, 1, $intern_35, GanttWidget$16);
_.onTouchCancel = function onTouchCancel(event_0){
  this.this$01.containerScrollStartPosY = -1;
  this.this$01.containerScrollStartPosX = -1;
  $onCancelTouch(this.this$01, event_0.nativeEvent);
}
;
var Lorg_tltv_gantt_client_GanttWidget$16_2_classLit = createForClass('org.tltv.gantt.client', 'GanttWidget/16', 174, Ljava_lang_Object_2_classLit);
function $execute(this$static){
  var sl, st;
  sl = $getScrollLeft_0(this$static.this$01.container);
  st = (this$static.this$01.container.scrollTop || 0) | 0;
  if (sl != this$static.this$01.previousContainerScrollLeft) {
    $setScrollLeft_0(this$static.this$01.timeline, sl);
    this$static.this$01.previousContainerScrollLeft = sl;
  }
  st != this$static.this$01.previousContainerScrollTop && (this$static.this$01.previousContainerScrollTop = st);
}

function GanttWidget$17(this$0){
  this.this$01 = this$0;
}

defineClass(175, 1, {}, GanttWidget$17);
_.execute = function execute_11(timestamp){
  $execute(this);
}
;
var Lorg_tltv_gantt_client_GanttWidget$17_2_classLit = createForClass('org.tltv.gantt.client', 'GanttWidget/17', 175, Ljava_lang_Object_2_classLit);
function GanttWidget$18(this$0){
  this.this$01 = this$0;
}

defineClass(350, $wnd.Function, $intern_25, GanttWidget$18);
_.call_0 = function call_10(args){
  $doInit_0(this.this$01);
  return null;
}
;
function GanttWidget$19(this$0, val$stepIndex, val$updateAffectedSteps, val$bar, val$stepWidget){
  this.this$01 = this$0;
  this.val$stepIndex2 = val$stepIndex;
  this.val$updateAffectedSteps3 = val$updateAffectedSteps;
  this.val$bar4 = val$bar;
  this.val$stepWidget5 = val$stepWidget;
}

defineClass(176, 1, {}, GanttWidget$19);
_.execute_1 = function execute_12(){
  $updateStepTop(this.this$01, this.val$stepIndex2, this.val$updateAffectedSteps3, this.val$bar4, this.val$stepWidget5);
}
;
_.val$stepIndex2 = 0;
_.val$updateAffectedSteps3 = false;
var Lorg_tltv_gantt_client_GanttWidget$19_2_classLit = createForClass('org.tltv.gantt.client', 'GanttWidget/19', 176, Ljava_lang_Object_2_classLit);
function GanttWidget$2(this$0){
  this.this$01 = this$0;
  Timer.call(this);
}

defineClass(159, 48, {}, GanttWidget$2);
_.run = function run_2(){
  var doFireClick, targetElement, targetEvent;
  doFireClick = this.this$01.numberOfMouseClicksDetected > 0 && !!this.this$01.previousMouseUpEvent && !!this.this$01.previousMouseUpBarElement && !$isMoveOrResizingInProgress(this.this$01);
  targetEvent = this.this$01.previousMouseUpEvent;
  targetElement = this.this$01.previousMouseUpBarElement;
  $cancelDoubleClickDetection(this.this$01);
  doFireClick && $fireClickRpc(this.this$01, targetElement, targetEvent);
}
;
var Lorg_tltv_gantt_client_GanttWidget$2_2_classLit = createForClass('org.tltv.gantt.client', 'GanttWidget/2', 159, Lcom_google_gwt_user_client_Timer_2_classLit);
function GanttWidget$20(val$steps){
  this.val$steps2 = val$steps;
}

defineClass(372, $wnd.Function, $intern_25, GanttWidget$20);
_.call_0 = function call_11(arg){
  return this.val$steps2;
}
;
function GanttWidget$21(this$0){
  this.this$01 = this$0;
}

defineClass(373, $wnd.Function, $intern_25, GanttWidget$21);
_.call_0 = function call_12(args){
  $updateContainerStyle(this.this$01);
  return null;
}
;
function GanttWidget$22(val$sw){
  this.val$sw2 = val$sw;
}

defineClass(374, $wnd.Function, $intern_25, GanttWidget$22);
_.call_0 = function call_13(args){
  return $clinit_Boolean() , castTo(this.val$sw2.step, 8)?true:false;
}
;
function $call_1(this$static, event_0){
  $onContainerScroll(this$static.this$01, event_0);
  return null;
}

function GanttWidget$23(this$0){
  this.this$01 = this$0;
}

defineClass(375, $wnd.Function, $intern_25, GanttWidget$23);
_.call_0 = function call_14(event_0){
  return $call_1(this, castToJso(event_0));
}
;
function GanttWidget$3(this$0){
  this.this$01 = this$0;
  Timer.call(this);
}

defineClass(160, 48, {}, GanttWidget$3);
_.run = function run_3(){
  $onTouchOrMouseDown(this.this$01, this.this$01.pendingPointerDownEvent);
  this.this$01.pendingPointerDownEvent = null;
}
;
var Lorg_tltv_gantt_client_GanttWidget$3_2_classLit = createForClass('org.tltv.gantt.client', 'GanttWidget/3', 160, Lcom_google_gwt_user_client_Timer_2_classLit);
function $onDoubleClick(this$static, event_0){
  var bar;
  if ($eventGetButton(event_0.nativeEvent) == 1) {
    $cancel(this$static.this$01.doubleClickDetectionMaxTimer);
    if (!this$static.this$01.insideDoubleClickDetectionInterval && this$static.this$01.numberOfMouseClicksDetected < 2) {
      return;
    }
    if (this$static.this$01.targetBarElement) {
      this$static.this$01.clickOnNextMouseUp = false;
      this$static.this$01.targetBarElement = null;
    }
    bar = $getBar_0(this$static.this$01, event_0.nativeEvent);
    !!bar && this$static.this$01.numberOfMouseClicksDetected > 1 && $fireClickRpc(this$static.this$01, bar, event_0.nativeEvent);
    $cancelDoubleClickDetection(this$static.this$01);
  }
}

function GanttWidget$4(this$0){
  this.this$01 = this$0;
}

defineClass(161, 1, {351:1, 15:1}, GanttWidget$4);
var Lorg_tltv_gantt_client_GanttWidget$4_2_classLit = createForClass('org.tltv.gantt.client', 'GanttWidget/4', 161, Ljava_lang_Object_2_classLit);
function GanttWidget$5(this$0){
  this.this$01 = this$0;
}

defineClass(162, 1, $intern_36, GanttWidget$5);
_.onMouseDown = function onMouseDown(event_0){
  if ($eventGetButton(event_0.nativeEvent) == 1) {
    $onTouchOrMouseDown(this.this$01, event_0.nativeEvent);
  }
   else {
    this.this$01.secondaryClickOnNextMouseUp = true;
    $schedule(new GanttWidget$5$1(this), 250);
    event_0.nativeEvent.stopPropagation();
  }
}
;
var Lorg_tltv_gantt_client_GanttWidget$5_2_classLit = createForClass('org.tltv.gantt.client', 'GanttWidget/5', 162, Ljava_lang_Object_2_classLit);
function GanttWidget$5$1(this$1){
  this.this$11 = this$1;
  Timer.call(this);
}

defineClass(163, 48, {}, GanttWidget$5$1);
_.run = function run_4(){
  this.this$11.this$01.secondaryClickOnNextMouseUp = false;
}
;
var Lorg_tltv_gantt_client_GanttWidget$5$1_2_classLit = createForClass('org.tltv.gantt.client', 'GanttWidget/5/1', 163, Lcom_google_gwt_user_client_Timer_2_classLit);
function $onMouseUp(this$static, event_0){
  var bar;
  if ($eventGetButton(event_0.nativeEvent) == 1) {
    $onTouchOrMouseUp(this$static.this$01, event_0.nativeEvent);
  }
   else {
    if (this$static.this$01.secondaryClickOnNextMouseUp) {
      bar = $getBar_0(this$static.this$01, event_0.nativeEvent);
      !!bar && this$static.this$01.enabled && $stepClicked(this$static.this$01.ganttRpc, $getStepUid(this$static.this$01, bar), event_0.nativeEvent, bar);
    }
    this$static.this$01.secondaryClickOnNextMouseUp = true;
  }
}

function GanttWidget$6(this$0){
  this.this$01 = this$0;
}

defineClass(164, 1, {352:1, 15:1}, GanttWidget$6);
var Lorg_tltv_gantt_client_GanttWidget$6_2_classLit = createForClass('org.tltv.gantt.client', 'GanttWidget/6', 164, Ljava_lang_Object_2_classLit);
function GanttWidget$7(this$0){
  this.this$01 = this$0;
}

defineClass(165, 1, {353:1, 15:1}, GanttWidget$7);
var Lorg_tltv_gantt_client_GanttWidget$7_2_classLit = createForClass('org.tltv.gantt.client', 'GanttWidget/7', 165, Ljava_lang_Object_2_classLit);
function GanttWidget$8(this$0){
  this.this$01 = this$0;
}

defineClass(166, 1, $intern_37, GanttWidget$8);
_.onMouseMove = function onMouseMove(event_0){
  $onTouchOrMouseMove(this.this$01, event_0.nativeEvent);
  !!event_0.nativeEvent && (event_0.nativeEvent.preventDefault() , undefined);
}
;
var Lorg_tltv_gantt_client_GanttWidget$8_2_classLit = createForClass('org.tltv.gantt.client', 'GanttWidget/8', 166, Ljava_lang_Object_2_classLit);
function GanttWidget$9(this$0){
  this.this$01 = this$0;
}

defineClass(167, 1, $intern_38, GanttWidget$9);
_.onPointerDown = function onPointerDown(event_0){
  if (this.this$01.currentPointerEventId == -1) {
    this.this$01.currentPointerEventId = event_0.nativeEvent.pointerId;
  }
   else {
    !!event_0.nativeEvent && (event_0.nativeEvent.preventDefault() , undefined);
    return;
  }
  this.this$01.pendingPointerDownEvent = event_0.nativeEvent;
  this.this$01.capturePoint = new Point(getTouchOrMouseClientX(event_0.nativeEvent), getTouchOrMouseClientY(event_0.nativeEvent));
  $schedule(this.this$01.pointerTouchStartedTimer, 100);
  !!event_0.nativeEvent && (event_0.nativeEvent.preventDefault() , undefined);
}
;
var Lorg_tltv_gantt_client_GanttWidget$9_2_classLit = createForClass('org.tltv.gantt.client', 'GanttWidget/9', 167, Ljava_lang_Object_2_classLit);
function $formatDate_0(this$static, zonedDate, formatter){
  return $format(formatter, zonedDate, this$static.timeZone);
}

function $formatDate_1(this$static, zonedDate, formatStr){
  !this$static.dateTimeService && (this$static.dateTimeService = new GanttDateTimeService(this$static.localeProvider));
  return $formatDate(this$static.dateTimeService, zonedDate, formatStr, this$static.timeZone);
}

function $getDaylightAdjustment_0(this$static, zonedDate){
  return $getDaylightAdjustment(this$static.timeZone, zonedDate) * 60000;
}

function $getFirstDayOfWeek(this$static){
  try {
    return this$static.localeProvider.localeFirstDayOfWeek + 1;
  }
   catch ($e0) {
    $e0 = toJava($e0);
    if (!instanceOf($e0, 10))
      throw toJs($e0);
  }
}

function $getMonthNames(this$static){
  try {
    return this$static.localeProvider.localeMonthNames;
  }
   catch ($e0) {
    $e0 = toJava($e0);
    if (!instanceOf($e0, 10))
      throw toJs($e0);
  }
}

function $getTimeZoneOffset(this$static, zonedDate){
  var offset;
  offset = -$getOffset(this$static.timeZone, zonedDate) * 60000;
  return offset;
}

function $getWeekdayNames(this$static){
  try {
    return this$static.localeProvider.localeDayNames;
  }
   catch ($e0) {
    $e0 = toJava($e0);
    if (!instanceOf($e0, 10))
      throw toJs($e0);
  }
}

function $isTwelveHourClock(this$static){
  try {
    return this$static.localeProvider.localeTwelveHourClock;
  }
   catch ($e0) {
    $e0 = toJava($e0);
    if (!instanceOf($e0, 10))
      throw toJs($e0);
  }
}

function LocaleDataProviderImpl(){
}

defineClass(178, 1, {}, LocaleDataProviderImpl);
var Lorg_tltv_gantt_client_LocaleDataProviderImpl_2_classLit = createForClass('org.tltv.gantt.client', 'LocaleDataProviderImpl', 178, Ljava_lang_Object_2_classLit);
function $init_1(this$static, progressValue){
  this$static.element = new GanttProgressBar;
  $addStyleName(this$static.element, 'bar-progress');
  $setState_0(this$static.element, progressValue / 100);
}

function $setProgress(this$static, progress){
  $setState_0(this$static.element, progress / 100);
}

function ProgressBarElement(){
}

defineClass(255, 1, {}, ProgressBarElement);
var Lorg_tltv_gantt_client_ProgressBarElement_2_classLit = createForClass('org.tltv.gantt.client', 'ProgressBarElement', 255, Ljava_lang_Object_2_classLit);
function $requestRemoveStep(this$static, stepWidget){
  $ready(stepWidget, makeLambdaFunction(StepHierarchyHandler$1.prototype.call_0, StepHierarchyHandler$1, [this$static, stepWidget]));
}

function $updateRelatedStepsPredecessors(targetStep, stepWidgets){
  var stepWidget, stepWidget$iterator;
  for (stepWidget$iterator = new ArrayList$1(stepWidgets); stepWidget$iterator.i < stepWidget$iterator.this$01.array.length;) {
    stepWidget = castTo($next_0(stepWidget$iterator), 14);
    $requestUpdatePredecessor(stepWidget, targetStep);
  }
}

function StepHierarchyHandler(widget){
  this.widget = widget;
}

defineClass(187, 1, {}, StepHierarchyHandler);
var Lorg_tltv_gantt_client_StepHierarchyHandler_2_classLit = createForClass('org.tltv.gantt.client', 'StepHierarchyHandler', 187, Ljava_lang_Object_2_classLit);
function StepHierarchyHandler$1(this$0, val$stepWidget){
  this.this$01 = this$0;
  this.val$stepWidget2 = val$stepWidget;
}

defineClass(376, $wnd.Function, $intern_25, StepHierarchyHandler$1);
_.call_0 = function call_15(args){
  $remove_12(this.this$01.widget, this.val$stepWidget2);
  return null;
}
;
function $$init_1(this$static){
  this$static.arrowChangeHandler = new StepWidget$1(this$static);
}

function $createPredecessorElements(this$static){
  var a;
  if (!this$static.predecessorStepWidget) {
    !!this$static.predecessorArrow && $unregisterContentElement(this$static.gantt_0, this$static.predecessorArrow);
  }
   else {
    if (!this$static.predecessorArrow) {
      this$static.predecessorArrow = (a = new SvgArrowWidget , $ready_1(a, makeLambdaFunction(StepWidget$4.prototype.call_0, StepWidget$4, [this$static, a])) , a);
      $whenReady(this$static.predecessorArrow, makeLambdaFunction(StepWidget$5.prototype.call_0, StepWidget$5, [this$static]));
    }
    $registerContentElement(this$static.gantt_0, this$static.predecessorArrow);
  }
}

function $getStepUidBySubStepElement(this$static, element){
  var w;
  w = $getSubStepWidgetByElement(this$static, element);
  if (w) {
    return w.step.uid;
  }
  return null;
}

function $getSubStepWidgetByElement(this$static, element){
  var w;
  w = $getWidget(this$static, ($clinit_DOM() , $getChildIndex(this$static.element.root, element) - (1 + (!!this$static.element.$.barLabel && !!$getParentElement(this$static.element.$.barLabel)?1:0) + (!!this$static.progressElement && !!$getParentElement($getElement(this$static.progressElement.element))?1:0)) - 1));
  if (instanceOf(w, 47)) {
    return castTo(w, 47);
  }
  return null;
}

function $getSubSteps(this$static){
  var iterator, list, widget;
  list = new ArrayList;
  iterator = new WidgetCollection$WidgetIterator(this$static.children_0);
  while (iterator.index_0 < iterator.this$01.size_0) {
    widget = $next(iterator);
    instanceOf(widget, 47) && $add_2(list, castTo(widget, 47));
  }
  return list;
}

function $requestUpdatePredecessor(this$static, sourceRelatedStep){
  if (castTo(this$static.step, 8)) {
    $equals_2(sourceRelatedStep, castTo(this$static.step, 8).predecessor) && $ready(this$static, makeLambdaFunction(StepWidget$3.prototype.call_0, StepWidget$3, [this$static]));
    return;
  }
  $ready(this$static, makeLambdaFunction(StepWidget$2.prototype.call_0, StepWidget$2, [this$static, sourceRelatedStep]));
}

function $setPredecessorStepWidget(this$static, predecessorStepWidget){
  this$static.predecessorStepWidget = predecessorStepWidget;
}

function $setReadOnly_1(this$static, readOnly){
  this$static.readOnly = readOnly;
  !!this$static.predecessorArrow && $setReadOnly_2(this$static.predecessorArrow, readOnly);
}

function $updatePredecessor(this$static){
  $ready(this$static, makeLambdaFunction(StepWidget$3.prototype.call_0, StepWidget$3, [this$static]));
}

function $updateStylesForSubSteps(this$static){
  var subSteps;
  subSteps = $getSubSteps(this$static);
  $updateStylesForSubSteps_0(this$static, subSteps.array.length != 0);
}

function $updateStylesForSubSteps_0(this$static, hasSubSteps){
  $ready(this$static, makeLambdaFunction(StepWidget$6.prototype.call_0, StepWidget$6, [this$static, hasSubSteps]));
}

function $updateWidth_0(this$static){
  var subStep, subStep$iterator, subSteps;
  $updateWidth(this$static);
  subSteps = $getSubSteps(this$static);
  $updateStylesForSubSteps_0(this$static, subSteps.array.length != 0);
  for (subStep$iterator = new ArrayList$1(subSteps); subStep$iterator.i < subStep$iterator.this$01.array.length;) {
    subStep = castTo($next_0(subStep$iterator), 47);
    $updateWidth(subStep);
  }
}

function StepWidget(){
  AbstractStepWidget.call(this);
  $$init_1(this);
}

function StepWidget_0(jselement){
  PolymerWidget.call(this, jselement);
  $$init(this);
  this.doInit();
  $$init_1(this);
}

defineClass(14, 87, {32:1, 25:1, 31:1, 30:1, 33:1, 22:1, 20:1, 14:1}, StepWidget, StepWidget_0);
_.getStep = function getStep_0(){
  return castTo(this.step, 8);
}
;
_.onDetach = function onDetach_1(){
  !!this.gantt_0 && !!this.predecessorArrow && $unregisterContentElement(this.gantt_0, this.predecessorArrow);
  $onDetach(this);
}
;
var Lorg_tltv_gantt_client_StepWidget_2_classLit = createForClass('org.tltv.gantt.client', 'StepWidget', 14, Lorg_tltv_gantt_client_AbstractStepWidget_2_classLit);
function $onArrowChanged(this$static, startingPointChanged, event_0){
  var target;
  target = getElementFromPoint($getElement(this$static.this$01.gantt_0).root, event_0.type.indexOf('touch') != -1?(event_0.changedTouches[0].clientX || 0) | 0:(event_0.clientX || 0) | 0, event_0.type.indexOf('touch') != -1?(event_0.changedTouches[0].clientY || 0) | 0:(event_0.clientY || 0) | 0);
  if (target) {
    return $onStepRelationSelected(this$static.this$01.gantt_0.ganttRpc, this$static.this$01, startingPointChanged, target);
  }
  return false;
}

function StepWidget$1(this$0){
  this.this$01 = this$0;
}

defineClass(154, 1, {}, StepWidget$1);
var Lorg_tltv_gantt_client_StepWidget$1_2_classLit = createForClass('org.tltv.gantt.client', 'StepWidget/1', 154, Ljava_lang_Object_2_classLit);
function StepWidget$2(this$0, val$sourceRelatedStep){
  this.this$01 = this$0;
  this.val$sourceRelatedStep2 = val$sourceRelatedStep;
}

defineClass(363, $wnd.Function, $intern_25, StepWidget$2);
_.call_0 = function call_16(args){
  !!castTo(this.this$01.step, 8) && $equals_2(this.val$sourceRelatedStep2, castTo(this.this$01.step, 8).predecessor) && $updatePredecessor(this.this$01);
  return null;
}
;
function StepWidget$3(this$0){
  this.this$01 = this$0;
}

defineClass(302, $wnd.Function, $intern_25, StepWidget$3);
_.call_0 = function call_17(args){
  $createPredecessorElements(this.this$01);
  if (!this.this$01.predecessorStepWidget) {
    return null;
  }
  $whenReady(this.this$01.predecessorArrow, makeLambdaFunction(StepWidget$3$1.prototype.call_0, StepWidget$3$1, [this]));
  return null;
}
;
function StepWidget$3$1(this$1){
  this.this$11 = this$1;
}

defineClass(367, $wnd.Function, $intern_25, StepWidget$3$1);
_.call_0 = function call_18(arg){
  var data_0;
  data_0 = new ArrowPositionData($getElement(this.this$11.this$01.predecessorStepWidget), $getElement(this.this$11.this$01));
  $setWidth_0(this.this$11.this$01.predecessorArrow, data_0.width_0);
  $setHeight_0(this.this$11.this$01.predecessorArrow, data_0.height);
  $setTop(this.this$11.this$01.predecessorArrow, round_int(data_0.top_0));
  $setLeft(this.this$11.this$01.predecessorArrow, round_int(data_0.left_0));
  $draw(this.this$11.this$01.predecessorArrow, data_0);
  return null;
}
;
function StepWidget$4(this$0, val$a){
  this.this$01 = this$0;
  this.val$a2 = val$a;
}

defineClass(364, $wnd.Function, $intern_25, StepWidget$4);
_.call_0 = function call_19(args){
  $setReadOnly_2(this.val$a2, this.this$01.readOnly);
  return null;
}
;
function StepWidget$5(this$0){
  this.this$01 = this$0;
}

defineClass(365, $wnd.Function, $intern_25, StepWidget$5);
_.call_0 = function call_20(arg){
  $setUpEventHandlers(this.this$01.predecessorArrow, this.this$01.gantt_0.touchSupported, !!(navigator.maxTouchPoints > 0));
  $setArrowChangeHandler(this.this$01.predecessorArrow, this.this$01.arrowChangeHandler);
  return null;
}
;
function StepWidget$6(this$0, val$hasSubSteps){
  this.this$01 = this$0;
  this.val$hasSubSteps2 = val$hasSubSteps;
}

defineClass(366, $wnd.Function, $intern_25, StepWidget$6);
_.call_0 = function call_21(args){
  this.val$hasSubSteps2?$addClassName($getBar(this.this$01), 'has-sub-steps'):$removeClassName($getBar(this.this$01), 'has-sub-steps');
  return null;
}
;
_.val$hasSubSteps2 = false;
function SubStepWidget(){
  AbstractStepWidget.call(this);
}

defineClass(47, 87, {32:1, 25:1, 31:1, 30:1, 33:1, 22:1, 20:1, 47:1}, SubStepWidget);
_.doInit = function doInit_0(){
  $ready(this, makeLambdaFunction(SubStepWidget$1.prototype.call_0, SubStepWidget$1, [this]));
}
;
_.getLeftPositionPercentageStringForDate = function getLeftPositionPercentageStringForDate_0(start_0, end){
  return $getSubstepLeftPositionPercentageStringForDate(this.gantt_0, start_0.value_0, castTo(this.owner.step, 8).startDate, castTo(this.owner.step, 8).endDate, ($clinit_DOM() , this.element));
}
;
_.getWidthPercentageStringForDateInterval = function getWidthPercentageStringForDateInterval_0(start_0, end){
  return $getSubstepWidthPercentageStringForDateInterval(this.gantt_0, start_0.value_0, end.value_0, castTo(this.owner.step, 8).startDate, castTo(this.owner.step, 8).endDate);
}
;
var Lorg_tltv_gantt_client_SubStepWidget_2_classLit = createForClass('org.tltv.gantt.client', 'SubStepWidget', 47, Lorg_tltv_gantt_client_AbstractStepWidget_2_classLit);
function SubStepWidget$1(this$0){
  this.this$01 = this$0;
}

defineClass(371, $wnd.Function, $intern_25, SubStepWidget$1);
_.call_0 = function call_22(arg){
  $addClassName($getBar(this.this$01), 'sub-bar');
  return null;
}
;
function $addMoveHandler(this$static){
  if (this$static.msTouchSupported) {
    this$static.moveRegisteration = $addDomHandler(this$static, this$static.pointerMoveHandler, ($clinit_PointerMoveEvent() , $clinit_PointerMoveEvent() , TYPE_12));
    this$static.touchCancelRegisteration = $addDomHandler(this$static, this$static.pointerCancelHandler, ($clinit_PointerCancelEvent() , $clinit_PointerCancelEvent() , TYPE_10));
  }
   else if (this$static.touchSupported) {
    this$static.moveRegisteration = $addDomHandler(this$static, this$static.touchMoveHandler, ($clinit_TouchMoveEvent() , $clinit_TouchMoveEvent() , TYPE_6));
    this$static.touchCancelRegisteration = $addDomHandler(this$static, this$static.touchCancelHandler, ($clinit_TouchCancelEvent() , $clinit_TouchCancelEvent() , TYPE_4));
  }
   else {
    this$static.moveRegisteration = $addDomHandler(this$static, this$static.mouseMoveHandler, ($clinit_MouseMoveEvent() , $clinit_MouseMoveEvent() , TYPE_2));
  }
}

function $cancelMove(this$static, forceReset, event_0){
  !!this$static.captureElement && releaseCapture(this$static.captureElement);
  $removeFromParent(this$static.movePointElement);
  $removeClassName($getParentElement(($clinit_DOM() , this$static.element)), 'select-target-step');
  $removeHandler(this$static.moveRegisteration.real);
  !!this$static.touchCancelRegisteration && $removeHandler(this$static.touchCancelRegisteration.real);
  this$static.captureElement = null;
  (forceReset || !!this$static.handler && !$onArrowChanged(this$static.handler, this$static.selectPredecessorMode, event_0)) && ($setWidth_0(this$static, this$static.originalData.width_0) , $setHeight_0(this$static, this$static.originalData.height) , $setTop(this$static, round_int(this$static.originalData.top_0)) , $setLeft(this$static, round_int(this$static.originalData.left_0)) , $draw(this$static, this$static.originalData) , this$static.element.$.startPoint.style['visibility'] = ($clinit_Style$Visibility() , 'visible') , undefined , this$static.element.$.endPoint.style['visibility'] = 'visible' , undefined , undefined);
  this$static.selectPredecessorMode = false;
  this$static.currentPointerEventId = -1;
  this$static.pendingPointerDownEvent = null;
}

function $createSnapshotElement(this$static, point){
  var deltaX, deltaY, originalLeftPoint, originalTopPoint, scrollDeltaX, scrollDeltaY;
  deltaX = round_int(point.x_0 - this$static.capturePoint.x_0);
  deltaY = round_int(point.y_0 - this$static.capturePoint.y_0);
  scrollDeltaY = (($getParentElement($getParentElement(($clinit_DOM() , this$static.element))).scrollTop || 0) | 0) - this$static.capturePointScrollTop;
  scrollDeltaX = $getScrollLeft_0($getParentElement($getParentElement(this$static.element))) - this$static.capturePointScrollLeft;
  originalTopPoint = this$static.selectPredecessorMode?$calcStartPointY(this$static.originalData):$calcEndPointY(this$static.originalData);
  originalLeftPoint = this$static.selectPredecessorMode?$calcStartPointX(this$static.originalData):$calcEndPointX(this$static.originalData);
  this$static.movePointElement.style['visibility'] = ($clinit_Style$Visibility() , 'hidden');
  this$static.movePointElement.style['position'] = ($clinit_Style$Position() , 'absolute');
  this$static.movePointElement.style['top'] = $wnd.Math.max(0, this$static.originalData.top_0 + originalTopPoint + deltaY + scrollDeltaY) + ($clinit_Style$Unit() , 'px');
  this$static.movePointElement.style['left'] = $wnd.Math.max(0, this$static.originalData.left_0 + originalLeftPoint + deltaX + scrollDeltaX) + 'px';
  this$static.movePointElement.style['width'] = '2.0px';
  this$static.movePointElement.style['height'] = '2.0px';
  return this$static.movePointElement;
}

function $draw(this$static, d){
  var startingPoint, endPointX, endPointY, endingPoint;
  this$static.originalData = d;
  ($clinit_DOM() , this$static.element).$.startPoint.style['visibility'] = ($clinit_Style$Visibility() , 'visible');
  this$static.element.$.endPoint.style['visibility'] = 'visible';
  $internalDrawCurve(this$static, d);
  startingPoint = this$static.element.$.startPoint;
  setAttributeNS(null, startingPoint, 'cx', '' + ((d.fromLeft?0:round_int(d.width_0)) + this$static.margin));
  setAttributeNS(null, startingPoint, 'cy', '' + ((d.fromTop?d.predecessorHeightCenter:round_int(d.height) - d.predecessorHeightCenter) + this$static.margin));
  endPointX = d.fromLeft?round_int(d.width_0):0;
  endPointY = d.fromTop?round_int(d.height) - d.predecessorHeightCenter:d.predecessorHeightCenter;
  endingPoint = this$static.element.$.endPoint;
  setAttributeNS(null, endingPoint, 'cx', '' + (endPointX + this$static.margin));
  setAttributeNS(null, endingPoint, 'cy', '' + (endPointY + this$static.margin));
}

function $handleDownEvent(this$static, event_0){
  var element;
  if (this$static.readOnly) {
    return;
  }
  if (this$static.captureElement) {
    $cancelMove(this$static, false, event_0);
    return;
  }
  element = getEventTarget(event_0);
  !!element && ($equals(element, ($clinit_DOM() , this$static.element).$.startPoint) || $equals(element, this$static.element.$.endPoint)) && $startMoving(this$static, event_0, element);
}

function $handleMove(this$static, event_0){
  var movePoint;
  movePoint = new Point(event_0.type.indexOf('touch') != -1?(event_0.changedTouches[0].clientX || 0) | 0:(event_0.clientX || 0) | 0, event_0.type.indexOf('touch') != -1?(event_0.changedTouches[0].clientY || 0) | 0:(event_0.clientY || 0) | 0);
  this$static.selectPredecessorMode?(this$static.movingData = new ArrowPositionData($createSnapshotElement(this$static, movePoint), this$static.originalData.to)):(this$static.movingData = new ArrowPositionData(this$static.originalData.from, $createSnapshotElement(this$static, movePoint)));
  $setWidth_0(this$static, this$static.movingData.width_0);
  $setHeight_0(this$static, this$static.movingData.height);
  $setTop(this$static, round_int(this$static.movingData.top_0));
  $setLeft(this$static, round_int(this$static.movingData.left_0));
  ($clinit_DOM() , this$static.element).$.startPoint.style['visibility'] = ($clinit_Style$Visibility() , 'hidden');
  this$static.element.$.endPoint.style['visibility'] = 'hidden';
  $internalDrawCurve(this$static, this$static.movingData);
  event_0.stopPropagation();
}

function $internalDrawCurve(this$static, d){
  var s, y1, y2;
  y1 = d.fromTop?d.predecessorHeightCenter:this$static.height - d.predecessorHeightCenter;
  y2 = d.fromTop?this$static.height - d.thisCenter:d.thisCenter;
  s = new StringBuilder_0('M');
  $append((s.string += ' ' , s), d.fromLeft?this$static.margin:this$static.width_0 + this$static.margin);
  $append((s.string += ', ' , s), y1 + this$static.margin);
  s.string += ' C';
  $append((s.string += ' ' , s), d.halfWidth + (this$static.margin / 2 | 0));
  $append((s.string += ', ' , s), y1 + this$static.margin);
  $append((s.string += ', ' , s), d.halfWidth + (this$static.margin / 2 | 0));
  $append((s.string += ', ' , s), y2 + this$static.margin);
  $append((s.string += ', ' , s), d.fromLeft?this$static.width_0 + this$static.margin:this$static.margin);
  $append((s.string += ', ' , s), y2 + this$static.margin);
  setAttributeNS(null, ($clinit_DOM() , this$static.element).$.curveLine, 'd', s.string);
}

function $ready_1(this$static, f){
  whenReadyAndConnected(f, ($clinit_DOM() , this$static.element));
}

function $registerMouseDownAndTouchDownEventListener(element){
  $clinit_DOM();
  $maybeInitializeEventSystem();
  $sinkEventsImpl(element, 1048580);
}

function $setArrowChangeHandler(this$static, handler){
  this$static.handler = handler;
}

function $setHeight_0(this$static, height){
  this$static.height = round_int(height);
  $setAttributes(this$static, 'height:' + (this$static.height + 2 * this$static.margin));
}

function $setLeft(this$static, left){
  ($clinit_DOM() , this$static.element).$.svgElement.style['left'] = left - this$static.margin + ($clinit_Style$Unit() , 'px');
}

function $setReadOnly_2(this$static, readOnly){
  this$static.readOnly = readOnly;
}

function $setTop(this$static, top_0){
  ($clinit_DOM() , this$static.element).$.svgElement.style['top'] = top_0 - this$static.margin + ($clinit_Style$Unit() , 'px');
}

function $setUpEventHandlers(this$static, touchSupported, msTouchSupported){
  this$static.touchSupported = touchSupported;
  this$static.msTouchSupported = msTouchSupported;
  if (msTouchSupported) {
    $addDomHandler(this$static, this$static.pointerDownHandler, ($clinit_PointerDownEvent() , $clinit_PointerDownEvent() , TYPE_11));
    $addDomHandler(this$static, this$static.pointerUpHandler, ($clinit_PointerUpEvent() , $clinit_PointerUpEvent() , TYPE_13));
  }
   else 
    touchSupported?$addDomHandler(this$static, this$static.touchStartHandler, ($clinit_TouchStartEvent() , $clinit_TouchStartEvent() , TYPE_7)):$addDomHandler(this$static, this$static.mouseDownHandler, ($clinit_MouseDownEvent() , $clinit_MouseDownEvent() , TYPE_1));
  $registerMouseDownAndTouchDownEventListener(($clinit_DOM() , this$static.element).$.startPoint);
  $registerMouseDownAndTouchDownEventListener(this$static.element.$.endPoint);
}

function $setWidth_0(this$static, width_0){
  this$static.width_0 = round_int(width_0);
  $setAttributes(this$static, 'width:' + (this$static.width_0 + 2 * this$static.margin));
}

function $startMoving(this$static, event_0, element){
  var endingPoint, startingPoint;
  startingPoint = ($clinit_DOM() , this$static.element).$.startPoint;
  endingPoint = this$static.element.$.endPoint;
  if (!!element && !!element.equals?element.equals(startingPoint):element == startingPoint) {
    this$static.selectPredecessorMode = true;
    startingPoint.style['visibility'] = ($clinit_Style$Visibility() , 'hidden');
  }
   else 
    (!!element && !!element.equals?element.equals(endingPoint):element == endingPoint) && (endingPoint.style['visibility'] = ($clinit_Style$Visibility() , 'hidden') , undefined);
  this$static.capturePointScrollTop = ($getParentElement($getParentElement(this$static.element)).scrollTop || 0) | 0;
  this$static.capturePointScrollLeft = $getScrollLeft_0($getParentElement($getParentElement(this$static.element)));
  $appendChild($getElement(this$static.parent_0).root, this$static.movePointElement);
  $addClassName($getParentElement(this$static.element), 'select-target-step');
  this$static.captureElement = this$static.element;
  setCapture(this$static.element);
  event_0.stopPropagation();
  $addMoveHandler(this$static);
  this$static.capturePoint = new Point(event_0.type.indexOf('touch') != -1?(event_0.changedTouches[0].clientX || 0) | 0:(event_0.clientX || 0) | 0, event_0.type.indexOf('touch') != -1?(event_0.changedTouches[0].clientY || 0) | 0:(event_0.clientY || 0) | 0);
}

function $whenReady(this$static, op){
  whenReadyAndConnected(op, ($clinit_DOM() , this$static.element));
}

function SvgArrowWidget(){
  PolymerWidget_0.call(this, 'svg-arrow');
  this.movePointElement = ($clinit_DOM() , $doc.createElement('div'));
  this.width_0 = 0;
  this.height = 0;
  this.margin = 8;
  this.capturePointScrollTop = 0;
  this.capturePointScrollLeft = 0;
  this.selectPredecessorMode = false;
  this.captureElement = null;
  this.currentPointerEventId = -1;
  this.pointerTouchStartedTimer = new SvgArrowWidget$1(this);
  this.pointerDownHandler = new SvgArrowWidget$2(this);
  this.pointerUpHandler = new SvgArrowWidget$3(this);
  this.touchStartHandler = new SvgArrowWidget$4(this);
  this.mouseDownHandler = new SvgArrowWidget$5(this);
  this.mouseMoveHandler = new SvgArrowWidget$6(this);
  this.pointerMoveHandler = new SvgArrowWidget$7(this);
  this.pointerCancelHandler = new SvgArrowWidget$8(this);
  this.touchMoveHandler = new SvgArrowWidget$9(this);
  this.touchCancelHandler = new SvgArrowWidget$10(this);
}

defineClass(261, 65, $intern_23, SvgArrowWidget);
_.onDetach = function onDetach_2(){
  this.attached && $onDetach(this);
}
;
_.capturePointScrollLeft = 0;
_.capturePointScrollTop = 0;
_.currentPointerEventId = 0;
_.height = 0;
_.margin = 0;
_.msTouchSupported = false;
_.readOnly = false;
_.selectPredecessorMode = false;
_.touchSupported = false;
_.width_0 = 0;
var Lorg_tltv_gantt_client_SvgArrowWidget_2_classLit = createForClass('org.tltv.gantt.client', 'SvgArrowWidget', 261, Lorg_tltv_gantt_client_PolymerWidget_2_classLit);
function SvgArrowWidget$1(this$0){
  this.this$01 = this$0;
  Timer.call(this);
}

defineClass(262, 48, {}, SvgArrowWidget$1);
_.run = function run_5(){
  $handleDownEvent(this.this$01, this.this$01.pendingPointerDownEvent);
  this.this$01.pendingPointerDownEvent = null;
}
;
var Lorg_tltv_gantt_client_SvgArrowWidget$1_2_classLit = createForClass('org.tltv.gantt.client', 'SvgArrowWidget/1', 262, Lcom_google_gwt_user_client_Timer_2_classLit);
function SvgArrowWidget$10(this$0){
  this.this$01 = this$0;
}

defineClass(271, 1, $intern_35, SvgArrowWidget$10);
_.onTouchCancel = function onTouchCancel_0(event_0){
  $cancelMove(this.this$01, true, null);
}
;
var Lorg_tltv_gantt_client_SvgArrowWidget$10_2_classLit = createForClass('org.tltv.gantt.client', 'SvgArrowWidget/10', 271, Ljava_lang_Object_2_classLit);
function SvgArrowWidget$2(this$0){
  this.this$01 = this$0;
}

defineClass(263, 1, $intern_38, SvgArrowWidget$2);
_.onPointerDown = function onPointerDown_0(event_0){
  if (this.this$01.currentPointerEventId == -1) {
    this.this$01.currentPointerEventId = event_0.nativeEvent.pointerId;
  }
   else {
    !!event_0.nativeEvent && (event_0.nativeEvent.preventDefault() , undefined);
    return;
  }
  this.this$01.pointerDownPoint = new Point(getTouchOrMouseClientX(event_0.nativeEvent), getTouchOrMouseClientY(event_0.nativeEvent));
  this.this$01.pendingPointerDownEvent = event_0.nativeEvent;
  $schedule(this.this$01.pointerTouchStartedTimer, 100);
}
;
var Lorg_tltv_gantt_client_SvgArrowWidget$2_2_classLit = createForClass('org.tltv.gantt.client', 'SvgArrowWidget/2', 263, Ljava_lang_Object_2_classLit);
function SvgArrowWidget$3(this$0){
  this.this$01 = this$0;
}

defineClass(264, 1, $intern_30, SvgArrowWidget$3);
_.onPointerUp = function onPointerUp_0(event_0){
  $cancel(this.this$01.pointerTouchStartedTimer);
  this.this$01.currentPointerEventId = -1;
  !!event_0.nativeEvent && (event_0.nativeEvent.preventDefault() , undefined);
}
;
var Lorg_tltv_gantt_client_SvgArrowWidget$3_2_classLit = createForClass('org.tltv.gantt.client', 'SvgArrowWidget/3', 264, Ljava_lang_Object_2_classLit);
function SvgArrowWidget$4(this$0){
  this.this$01 = this$0;
}

defineClass(265, 1, $intern_33, SvgArrowWidget$4);
_.onTouchStart = function onTouchStart_0(event_0){
  $handleDownEvent(this.this$01, event_0.nativeEvent);
}
;
var Lorg_tltv_gantt_client_SvgArrowWidget$4_2_classLit = createForClass('org.tltv.gantt.client', 'SvgArrowWidget/4', 265, Ljava_lang_Object_2_classLit);
function SvgArrowWidget$5(this$0){
  this.this$01 = this$0;
}

defineClass(266, 1, $intern_36, SvgArrowWidget$5);
_.onMouseDown = function onMouseDown_0(event_0){
  $eventGetButton(event_0.nativeEvent) == 1 && $handleDownEvent(this.this$01, event_0.nativeEvent);
}
;
var Lorg_tltv_gantt_client_SvgArrowWidget$5_2_classLit = createForClass('org.tltv.gantt.client', 'SvgArrowWidget/5', 266, Ljava_lang_Object_2_classLit);
function SvgArrowWidget$6(this$0){
  this.this$01 = this$0;
}

defineClass(267, 1, $intern_37, SvgArrowWidget$6);
_.onMouseMove = function onMouseMove_0(event_0){
  $handleMove(this.this$01, event_0.nativeEvent);
}
;
var Lorg_tltv_gantt_client_SvgArrowWidget$6_2_classLit = createForClass('org.tltv.gantt.client', 'SvgArrowWidget/6', 267, Ljava_lang_Object_2_classLit);
function SvgArrowWidget$7(this$0){
  this.this$01 = this$0;
}

defineClass(268, 1, $intern_31, SvgArrowWidget$7);
_.onPointerMove = function onPointerMove_0(event_0){
  if (!this.this$01.pointerDownPoint) {
    return;
  }
  if (!(this.this$01.pointerDownPoint.x_0 == getTouchOrMouseClientX(event_0.nativeEvent) && this.this$01.pointerDownPoint.y_0 == getTouchOrMouseClientY(event_0.nativeEvent))) {
    $cancel(this.this$01.pointerTouchStartedTimer);
    $handleMove(this.this$01, event_0.nativeEvent);
  }
}
;
var Lorg_tltv_gantt_client_SvgArrowWidget$7_2_classLit = createForClass('org.tltv.gantt.client', 'SvgArrowWidget/7', 268, Ljava_lang_Object_2_classLit);
function SvgArrowWidget$8(this$0){
  this.this$01 = this$0;
}

defineClass(269, 1, $intern_32, SvgArrowWidget$8);
_.onPointerCancel = function onPointerCancel_0(event_0){
  $cancel(this.this$01.pointerTouchStartedTimer);
  $cancelMove(this.this$01, true, null);
}
;
var Lorg_tltv_gantt_client_SvgArrowWidget$8_2_classLit = createForClass('org.tltv.gantt.client', 'SvgArrowWidget/8', 269, Ljava_lang_Object_2_classLit);
function SvgArrowWidget$9(this$0){
  this.this$01 = this$0;
}

defineClass(270, 1, $intern_34, SvgArrowWidget$9);
_.onTouchMove = function onTouchMove_0(event_0){
  if (event_0.nativeEvent.changedTouches.length == 1) {
    $handleMove(this.this$01, event_0.nativeEvent);
    !!event_0.nativeEvent && (event_0.nativeEvent.preventDefault() , undefined);
  }
}
;
var Lorg_tltv_gantt_client_SvgArrowWidget$9_2_classLit = createForClass('org.tltv.gantt.client', 'SvgArrowWidget/9', 270, Ljava_lang_Object_2_classLit);
function setAttributeNS(uri_0, elem, attr, value_0){
  elem.setAttributeNS(uri_0, attr, value_0);
}

function $addBlock(current, target, date, rowData, operation){
  var key;
  if ($equals_0(target, current)) {
    key = '' + current + '_' + $size(rowData.blocks.map_0);
    $setBlockLength(rowData, key, valueOf_0(castTo($get_6(rowData.blockLength, key), 43).value_0 + 1));
  }
   else {
    current = target;
    key = '' + target + '_' + ($size(rowData.blocks.map_0) + 1);
    operation.run_0(target, key, date);
  }
  return current;
}

function $addDayBlock(this$static, key, text_0){
  $createTimelineBlock(key, text_0, 'day', this$static.dayRowData);
}

function $addMonthBlock(this$static, key, text_0){
  $createTimelineBlock(key, text_0, 'month', this$static.monthRowData);
}

function $addYearBlock(this$static, key, text_0){
  $createTimelineBlock(key, text_0, 'year', this$static.yearRowData);
}

function $adjustDateRangeByDST(this$static, range){
  var dstEnd, dstStart;
  dstStart = $getDaylightAdjustment_0(this$static.localeDataProvider, new Date_1(this$static.startDate));
  dstEnd = $getDaylightAdjustment_0(this$static.localeDataProvider, new Date_1(this$static.endDate));
  compare_0(dstStart, dstEnd) > 0?(range -= toDouble_0(lt(sub_1(dstStart, dstEnd), 0)?neg_0(sub_1(dstStart, dstEnd)):sub_1(dstStart, dstEnd))):compare_0(dstEnd, dstStart) > 0 && (range += toDouble_0(lt(sub_1(dstEnd, dstStart), 0)?neg_0(sub_1(dstEnd, dstStart)):sub_1(dstEnd, dstStart)));
  return range;
}

function $adjustToMiddleOfDay(this$static, zonedDate){
  var addHours, h, hourFormat, hourStr;
  hourFormat = ($clinit_DateTimeFormat() , getFormat('HH', $getDateTimeFormatInfo(($clinit_LocaleInfo() , $clinit_LocaleInfo() , instance_1))));
  hourStr = $format(hourFormat, new Date_1(zonedDate), this$static.localeDataProvider.timeZone);
  h = __parseAndValidateInt(hourStr);
  addHours = 12 - h;
  return add_1(zonedDate, mul_0(addHours, $intern_29));
}

function $appendTimelineBlocks(this$static, rowData){
  var entry, entry$iterator, block;
  for (entry$iterator = new LinkedHashMap$EntrySet$EntryIterator(new LinkedHashMap$EntrySet(rowData.blocks)); entry$iterator.next_0 != entry$iterator.this$11.this$01.head_0;) {
    entry = $next_1(entry$iterator);
    $appendChild(($clinit_DOM() , this$static.element), castToJso(entry.value_0));
  }
  this$static.calcPixels && $appendChild(($clinit_DOM() , this$static.element), (block = $doc.createElement('div') , block.className = 'row year' , undefined , $addClassName(block, 'spacer') , block.textContent = ' ' , undefined , block.style['display'] = ($clinit_Style$Display() , 'none') , undefined , $add_3(this$static.spacerBlocks, block) , block));
}

function $calculateActualResolutionBlockWidthPx(this$static, dayOrHourBlockWidthPx){
  if (this$static.resolution == ($clinit_Resolution() , Week)) {
    return 7 * dayOrHourBlockWidthPx;
  }
  return dayOrHourBlockWidthPx;
}

function $calculateDayOrHourResolutionBlockWidthPx(this$static, blockCount){
  var dayOrHourWidthPx;
  dayOrHourWidthPx = round_int($wnd.Math.round((this$static.resolutionDiv.clientWidth | 0) / blockCount | 0));
  while ((this$static.resolutionDiv.clientWidth | 0) % (blockCount * dayOrHourWidthPx) >= blockCount) {
    ++dayOrHourWidthPx;
  }
  return dayOrHourWidthPx;
}

function $calculateMinimumResolutionBlockWidth(this$static){
  if (this$static.resolution == ($clinit_Resolution() , Week)) {
    return 7 * this$static.minResolutionWidth;
  }
  return this$static.minResolutionWidth;
}

function $calculateResolutionMinWidth(this$static){
  var parent_0, removeResolutionDiv, resBlockMeasure, width_0;
  removeResolutionDiv = false;
  if (!$getParentElement(this$static.resolutionDiv)) {
    removeResolutionDiv = true;
    $appendChild(($clinit_DOM() , this$static.element), this$static.resolutionDiv);
  }
  resBlockMeasure = ($clinit_DOM() , $doc.createElement('div'));
  if (this$static.resolution == ($clinit_Resolution() , Week)) {
    resBlockMeasure.className = 'col w measure';
  }
   else {
    resBlockMeasure.textContent = 'MM';
    resBlockMeasure.className = 'col measure';
  }
  $appendChild(this$static.resolutionDiv, resBlockMeasure);
  width_0 = resBlockMeasure.clientWidth | 0;
  this$static.resolution == Week && (width_0 = width_0 / 7 | 0);
  width_0 = width_0 < this$static.resolutionWeekDayblockWidth?this$static.resolutionWeekDayblockWidth:width_0;
  parent_0 = $getParentElement(resBlockMeasure);
  !!parent_0 && parent_0.removeChild(resBlockMeasure);
  removeResolutionDiv && $removeFromParent(this$static.resolutionDiv);
  return width_0;
}

function $calculateTimelineWidth(this$static){
  var l, last, r, timelineRealWidth;
  last = $getLastResolutionElement(this$static);
  if (!last) {
    return 0;
  }
  r = getBoundingClientRectRight(last);
  l = getBoundingClientRectLeft($getFirstResolutionElement(this$static));
  timelineRealWidth = r - l;
  return timelineRealWidth;
}

function $checkTimelineOverflowingHorizontally(this$static){
  this$static.timelineOverflowingHorizontally = getBoundingClientRectWidth(this$static.resolutionDiv) > getBoundingClientRectWidth(this$static.parentElement);
  return this$static.timelineOverflowingHorizontally;
}

function $clear_0(this$static){
  while (($clinit_DOM() , this$static.element).hasChildNodes()) {
    $removeFromParent(this$static.element.lastChild);
  }
  $reset(this$static.spacerBlocks.map_0);
  $clear_1(this$static.yearRowData);
  $clear_1(this$static.monthRowData);
  $clear_1(this$static.dayRowData);
}

function $convertRelativeLeftPosition(this$static, left, contentWidthToConvertFor){
  var relativePosition, timelineLeft, width_0;
  width_0 = $getResolutionWidth(this$static);
  if (width_0 <= 0 || contentWidthToConvertFor <= 0) {
    return 0;
  }
  relativePosition = 1 / contentWidthToConvertFor * left;
  timelineLeft = relativePosition * width_0;
  return timelineLeft;
}

function $createCalcCssValue(this$static, v, multiplier){
  var percents;
  if (this$static.ie) {
    percents = 100 / v * multiplier;
    return 'calc(' + percents + '%)';
  }
  return null;
}

function $createCalcCssValue_0(this$static, resolutionBlockCount){
  if (this$static.ie) {
    return 'calc(100% / ' + resolutionBlockCount + ')';
  }
  return null;
}

function $createResolutionBlock(){
  var resBlock;
  resBlock = ($clinit_DOM() , $doc.createElement('div'));
  resBlock.className = 'col';
  return resBlock;
}

function $createTimelineBlock(key, text_0, styleSuffix, rowData){
  var div;
  div = ($clinit_DOM() , $doc.createElement('div'));
  div.className = 'row ' + styleSuffix || '';
  div.textContent = text_0 || '';
  $setBlockLength(rowData, key, valueOf_0(1));
  $put_3(rowData.blocks, key, div);
  return div;
}

function $createTimelineElementsOnVisibleArea(this$static){
  var blocks, element, i_0, resBlock, resBlock_0, resBlock_1;
  blocks = this$static.resolutionBlockCount;
  if (this$static.timelineOverflowingHorizontally) {
    blocks = round_int(getBoundingClientRectWidth(this$static.parentElement) / $calculateMinimumResolutionBlockWidth(this$static));
    this$static.resolutionBlockCount < blocks?(blocks = this$static.resolutionBlockCount):(blocks += 2);
  }
  element = null;
  for (i_0 = 0; i_0 < blocks; i_0++) {
    switch (this$static.resolution.ordinal) {
      case 2:
        element = (resBlock = $createResolutionBlock() , $addClassName(resBlock, 'h') , $addClassName(resBlock, 'c-col') , resBlock);
        break;
      case 0:
        element = (resBlock_0 = $createResolutionBlock() , $addClassName(resBlock_0, 'c-col') , resBlock_0);
        break;
      case 1:
        element = (resBlock_1 = $createResolutionBlock() , $addClassName(resBlock_1, 'w') , $addClassName(resBlock_1, 'c-col') , resBlock_1);
    }
    $appendChild(this$static.resolutionDiv, element);
  }
  $ensureNamesAreInitialized(Lorg_tltv_gantt_client_TimelineWidget_2_classLit);
  Lorg_tltv_gantt_client_TimelineWidget_2_classLit.simpleName + ' Added ' + blocks + ' visible timeline elements for resolution .' + valueOf_2(this$static.resolution);
}

function $fillDayResolutionBlock(this$static, resBlock, date, weekend, left){
  $setInnerText(resBlock, $formatDate_0(this$static.localeDataProvider, date, (!this$static.dayDateTimeFormat && (this$static.dayDateTimeFormat = ($clinit_DateTimeFormat() , getFormat('d', $getDateTimeFormatInfo(($clinit_LocaleInfo() , $clinit_LocaleInfo() , instance_1))))) , this$static.dayDateTimeFormat)));
  weekend?$addClassName(resBlock, 'weekend'):$removeClassName(resBlock, 'weekend');
  if (!this$static.styleElementForLeft && this$static.timelineOverflowingHorizontally) {
    resBlock.style['position'] = ($clinit_Style$Position() , 'relative');
    resBlock.style['left'] = left + ($clinit_Style$Unit() , 'px');
  }
}

function $fillHourResolutionBlock(this$static, resBlock, date, index_0, hourCounter, lastBlock, left, even){
  $isTwelveHourClock(this$static.localeDataProvider)?$setInnerText(resBlock, $formatDate_0(this$static.localeDataProvider, date, (!this$static.hour12DateTimeFormat && (this$static.hour12DateTimeFormat = ($clinit_DateTimeFormat() , getFormat('h', $getDateTimeFormatInfo(($clinit_LocaleInfo() , $clinit_LocaleInfo() , instance_1))))) , this$static.hour12DateTimeFormat))):$setInnerText(resBlock, $formatDate_0(this$static.localeDataProvider, date, (!this$static.hour24DateTimeFormat && (this$static.hour24DateTimeFormat = ($clinit_DateTimeFormat() , getFormat('HH', $getDateTimeFormatInfo(($clinit_LocaleInfo() , $clinit_LocaleInfo() , instance_1))))) , this$static.hour24DateTimeFormat)));
  even?$addClassName(resBlock, 'even'):$removeClassName(resBlock, 'even');
  if (this$static.firstDay && (hourCounter == 24 || lastBlock)) {
    this$static.firstDay = false;
    this$static.firstResBlockCount = index_0 + 1;
  }
   else 
    lastBlock && (this$static.lastResBlockCount = (index_0 + 1 - this$static.firstResBlockCount) % 24);
  if (!this$static.styleElementForLeft && this$static.timelineOverflowingHorizontally) {
    resBlock.style['position'] = ($clinit_Style$Position() , 'relative');
    resBlock.style['left'] = left + ($clinit_Style$Unit() , 'px');
  }
}

function $fillTimelineForHourResolution(startDate, endDate, resBlockFiller){
  var date, index_0, lastTimelineBlock, nextHour, pos;
  pos = startDate;
  index_0 = 0;
  while (compare_0(pos, endDate) <= 0) {
    date = new Date_1(pos);
    nextHour = new Date_1(add_1(pos, $intern_29));
    lastTimelineBlock = gt(fromDouble_0(nextHour.jsdate.getTime()), endDate);
    $fillResolutionBlock(resBlockFiller, index_0, date, lastTimelineBlock);
    pos = fromDouble_0(nextHour.jsdate.getTime());
    ++index_0;
  }
}

function $fillTimelineForResolution(this$static, startDate, endDate, left){
  if (this$static.resolution == ($clinit_Resolution() , Day) || this$static.resolution == Week) {
    $fillTimelineForResolution_0(this$static, startDate, endDate, new TimelineWidget$5(this$static, startDate, left));
  }
   else if (this$static.resolution == Hour) {
    this$static.firstDay = true;
    $fillTimelineForHourResolution(startDate, endDate, new TimelineWidget$4(this$static, startDate, left));
  }
   else {
    $ensureNamesAreInitialized(Lorg_tltv_gantt_client_TimelineWidget_2_classLit);
    Lorg_tltv_gantt_client_TimelineWidget_2_classLit.simpleName + ' resolution ' + (this$static.resolution?$name(this$static.resolution):'null') + ' is not supported';
    return;
  }
  $ensureNamesAreInitialized(Lorg_tltv_gantt_client_TimelineWidget_2_classLit);
}

function $fillTimelineForResolution_0(this$static, startDate, endDate, resBlockFiller){
  var date, dstAdjusted, index_0, isDST, lastTimelineBlock, pos, previousIsDST;
  pos = $adjustToMiddleOfDay(this$static, startDate);
  index_0 = 0;
  lastTimelineBlock = false;
  previousIsDST = $getDaylightAdjustment(this$static.localeDataProvider.timeZone, new Date_1(startDate)) > 0;
  while (!lastTimelineBlock) {
    dstAdjusted = $getDSTAdjustedDate(this$static, previousIsDST, pos);
    date = new Date_1(dstAdjusted);
    pos = dstAdjusted;
    isDST = $getDaylightAdjustment(this$static.localeDataProvider.timeZone, date) > 0;
    lastTimelineBlock = gt($getDSTAdjustedDate(this$static, isDST, add_1(fromDouble_0(date.jsdate.getTime()), $intern_10)), endDate);
    $fillResolutionBlock_0(resBlockFiller, index_0, date, lastTimelineBlock);
    previousIsDST = isDST;
    pos = add_1(pos, $intern_10);
    ++index_0;
  }
}

function $fillVisibleTimeline(this$static){
  this$static.timelineOverflowingHorizontally?$showResolutionBlocksOnView(this$static):$showAllResolutionBlocks(this$static);
}

function $fillWeekResolutionBlock(this$static, resBlock, date, weekDay, firstWeek, lastBlock, left, even){
  var firstEl, lastEl;
  if (resBlock) {
    $setInnerText(resBlock, $formatWeekCaption(this$static, date));
    even?$addClassName(resBlock, 'even'):$removeClassName(resBlock, 'even');
    if (!this$static.styleElementForLeft && this$static.timelineOverflowingHorizontally) {
      resBlock.style['position'] = ($clinit_Style$Position() , 'relative');
      resBlock.style['left'] = left + ($clinit_Style$Unit() , 'px');
    }
    $removeClassName(resBlock, 'f-col');
    $removeClassName(resBlock, 'l-col');
  }
  if (firstWeek && (weekDay == ($clinit_TimelineWidget$Weekday() , Last) || lastBlock)) {
    firstEl = $getFirstChildElement(this$static.resolutionDiv);
    $hasClassName(firstEl, 'f-col') || $addClassName(firstEl, 'f-col');
  }
   else if (lastBlock) {
    lastEl = this$static.resolutionDiv.lastChild;
    $hasClassName(lastEl, 'l-col') || $addClassName(lastEl, 'l-col');
  }
}

function $formatDayCaption(this$static, day, date){
  if (this$static.dayFormat == null || this$static.dayFormat.length == 0) {
    return day;
  }
  return $formatDate_1(this$static.localeDataProvider, date, this$static.dayFormat);
}

function $formatMonthCaption(this$static, month, date){
  if (this$static.monthFormat == null || this$static.monthFormat.length == 0) {
    return this$static.monthNames[month];
  }
  return $formatDate_1(this$static.localeDataProvider, date, this$static.monthFormat);
}

function $formatWeekCaption(this$static, date){
  if (this$static.weekFormat == null || this$static.weekFormat.length == 0) {
    return '' + getWeekNumber(date, ($getTimeZoneOffset(this$static.localeDataProvider, date) , $getFirstDayOfWeek(this$static.localeDataProvider)));
  }
  return $formatDate_1(this$static.localeDataProvider, date, this$static.weekFormat);
}

function $formatYearCaption(this$static, year, date){
  if (this$static.yearFormat == null || this$static.yearFormat.length == 0) {
    return year;
  }
  return $formatDate_1(this$static.localeDataProvider, date, this$static.yearFormat);
}

function $getCssPercentageWidth(nValue, pct){
  return pct != null?pct:nValue + ($clinit_Style$Unit() , '%');
}

function $getCssPercentageWidth_0(this$static, daysInRange, width_0, position){
  var pct;
  pct = $createCalcCssValue(this$static, daysInRange, position);
  return pct != null?pct:position * width_0 + ($clinit_Style$Unit() , '%');
}

function $getDSTAdjustedDate(this$static, previousIsDST, zonedDate){
  var dstAdjustment, isDST;
  dstAdjustment = $getDaylightAdjustment_0(this$static.localeDataProvider, new Date_1(zonedDate));
  isDST = compare_0(dstAdjustment, 0) > 0;
  if (previousIsDST && !isDST) {
    return add_1(zonedDate, dstAdjustment);
  }
   else if (!previousIsDST && isDST) {
    return sub_1(zonedDate, dstAdjustment);
  }
  return zonedDate;
}

function $getDateForLeftPosition(this$static, left){
  return $getDateForLeftPosition_0(this$static, left, this$static.resolution == ($clinit_Resolution() , Hour));
}

function $getDateForLeftPosition_0(this$static, left, noticeDST){
  var date, offset, p_0, range, width_0;
  width_0 = $getResolutionWidth(this$static);
  if (width_0 <= 0) {
    return 0;
  }
  range = toDouble_0(sub_1(this$static.normalEndDate, this$static.normalStartDate));
  noticeDST && (range = $adjustDateRangeByDST(this$static, range));
  p_0 = range / width_0;
  offset = p_0 * left;
  date = add_1(this$static.startDate, fromDouble_0(offset));
  'Zoned: ' + $formatDate_1(this$static.localeDataProvider, new Date_1(date), 'dd. HH:mm') + '  DST: ' + toString_12(div_0($getDaylightAdjustment_0(this$static.localeDataProvider, new Date_1(date)), 60000));
  return date;
}

function $getFirstResolutionElement(this$static){
  if (this$static.resolutionDiv.hasChildNodes()) {
    return $getFirstChildElement(this$static.resolutionDiv);
  }
  return null;
}

function $getFirstResolutionElementWidth(this$static){
  return this$static.firstResBlockCount > 0 && this$static.resolution == ($clinit_Resolution() , Week) && this$static.firstResBlockCount < 7?this$static.timelineOverflowingHorizontally?this$static.firstResBlockCount * this$static.minResolutionWidth:getBoundingClientRectWidth($getFirstResolutionElement(this$static)):this$static.timelineOverflowingHorizontally?this$static.resBlockMinWidthPx:getBoundingClientRectWidth($getFirstResolutionElement(this$static));
}

function $getLastResolutionElement(this$static){
  var blockCount, div, index_0, nodeList;
  div = this$static.resolutionDiv;
  if (!div) {
    return null;
  }
  nodeList = div.childNodes;
  if (!nodeList) {
    return null;
  }
  blockCount = nodeList.length;
  if (blockCount < 1) {
    return null;
  }
  if (!!this$static.resSpacerDiv && !!$getParentElement(this$static.resSpacerDiv) && $equals($getParentElement(this$static.resSpacerDiv), this$static.resolutionDiv)) {
    index_0 = blockCount - 2;
    if (blockCount > 1 && index_0 >= 0) {
      return $getChild(this$static.resolutionDiv, index_0);
    }
    return null;
  }
  return this$static.resolutionDiv.lastChild;
}

function $getLeftPositionForDate(date, rangeWidth, rangeStartDate, rangeEndDate){
  var left, offset, p_0, range;
  range = toDouble_0(sub_1(rangeEndDate, rangeStartDate));
  if (range <= 0) {
    return 0;
  }
  p_0 = rangeWidth / range;
  offset = toDouble_0(sub_1(date, rangeStartDate));
  left = p_0 * offset;
  return left;
}

function $getLeftPositionPercentageStringForDate_0(this$static, date, contentWidth){
  var calc, relativeLeft, timelineLeft, width_0;
  timelineLeft = $getLeftPositionForDate(date, $getResolutionWidth(this$static), this$static.startDate, this$static.endDate);
  relativeLeft = $convertRelativeLeftPosition(this$static, timelineLeft, contentWidth);
  width_0 = $getResolutionWidth(this$static);
  calc = $createCalcCssValue(this$static, width_0, relativeLeft);
  if (calc != null) {
    return calc;
  }
  return 100 / width_0 * relativeLeft + '' + ($clinit_Style$Unit() , '%');
}

function $getLeftPositionPercentageStringForDate_1(this$static, date, rangeWidth, rangeStartDate, rangeEndDate){
  var calc, rangeLeft;
  rangeLeft = $getLeftPositionForDate(date, rangeWidth, rangeStartDate, rangeEndDate);
  calc = $createCalcCssValue(this$static, rangeWidth, rangeLeft);
  if (calc != null) {
    return calc;
  }
  return 100 / rangeWidth * rangeLeft + '' + ($clinit_Style$Unit() , '%');
}

function $getResolutionDivWidth(this$static){
  if (!this$static.timelineOverflowingHorizontally) {
    return getBoundingClientRectWidth(this$static.resolutionDiv);
  }
  return this$static.blocksInRange * this$static.minResolutionWidth;
}

function $getResolutionWidth(this$static){
  var width_0;
  if (!this$static.timelineOverflowingHorizontally) {
    return $calculateTimelineWidth(this$static);
  }
  width_0 = $getResolutionDivWidth(this$static);
  this$static.calcPixels && !!this$static.resSpacerDiv && !!$getParentElement(this$static.resSpacerDiv) && $equals($getParentElement(this$static.resSpacerDiv), this$static.resolutionDiv) && (width_0 = width_0 - getBoundingClientRectWidth(this$static.resSpacerDiv));
  return width_0;
}

function $getScrollOverflowForRegularResoultionBlock(this$static, positionLeftSnapshot, firstResBlockShort){
  var firstBlockWidth, overflow, positionLeft;
  firstBlockWidth = this$static.firstResBlockCount > 0 && this$static.resolution == ($clinit_Resolution() , Week) && this$static.firstResBlockCount < 7?this$static.timelineOverflowingHorizontally?this$static.firstResBlockCount * this$static.minResolutionWidth:getBoundingClientRectWidth($getFirstResolutionElement(this$static)):this$static.timelineOverflowingHorizontally?this$static.resBlockMinWidthPx:getBoundingClientRectWidth($getFirstResolutionElement(this$static));
  positionLeft = positionLeftSnapshot - (firstResBlockShort?firstBlockWidth:0);
  overflow = positionLeft % this$static.resBlockWidthPx;
  if (firstResBlockShort) {
    overflow += firstBlockWidth;
    this$static.firstWeekBlockHidden = true;
  }
  return overflow;
}

function $getWeekday(this$static, dayCounter){
  if (dayCounter == this$static.firstDayOfWeek) {
    return $clinit_TimelineWidget$Weekday() , First;
  }
  if (dayCounter == this$static.lastDayOfWeek) {
    return $clinit_TimelineWidget$Weekday() , Last;
  }
  return $clinit_TimelineWidget$Weekday() , Between;
}

function $getWidth(this$static, multiplier){
  return this$static.timelineOverflowingHorizontally?multiplier * this$static.minResolutionWidth + ($clinit_Style$Unit() , 'px'):this$static.calcPixels?multiplier * this$static.dayOrHourWidthPx + ($clinit_Style$Unit() , 'px'):$getCssPercentageWidth_0(this$static, this$static.blocksInRange, this$static.dayWidthPercentage, multiplier);
}

function $getWidthPercentageStringForDateInterval_0(this$static, interval){
  var range;
  range = toDouble_0(sub_1(this$static.endDate, this$static.startDate));
  return $getWidthPercentageStringForDateInterval_1(this$static, interval, range);
}

function $getWidthPercentageStringForDateInterval_1(this$static, interval, range){
  var calc;
  calc = $createCalcCssValue(this$static, range, toDouble_0(interval));
  if (calc != null) {
    return calc;
  }
  return 100 / range * toDouble_0(interval) + '' + ($clinit_Style$Unit() , '%');
}

function $hideSpacerBlocks(this$static){
  var e, e$iterator, entry, outerIter;
  for (e$iterator = (outerIter = (new AbstractMap$1(this$static.spacerBlocks.map_0)).this$01.entrySet().iterator() , new AbstractMap$1$1(outerIter)); e$iterator.val$outerIter2.hasNext_0();) {
    e = (entry = castTo(e$iterator.val$outerIter2.next_1(), 16) , castToJso(entry.getKey()));
    e.style['display'] = ($clinit_Style$Display() , 'none');
  }
}

function $injectLeftStyle(this$static){
  if (this$static.ie || !!this$static.styleElementForLeft) {
    return;
  }
  this$static.styleElementForLeft = ($clinit_StyleInjector() , $push(toInjectAtEnd, '.col { } ') , flush(toInjectAtEnd));
  flush(null);
}

function $injectStyle(this$static){
  if (this$static.ie || !!this$static.styleElement) {
    return;
  }
  this$static.styleElement = ($clinit_StyleInjector() , $push(toInjectAtEnd, '.f-col { } .c-col { } .l-col { } .col { } ') , flush(toInjectAtEnd));
  flush(null);
}

function $isChanged(this$static, resolution, startDate, endDate, firstDayOfWeek, firstDayOfRange, firstHourOfRange, locale){
  var resolutionChanged;
  resolutionChanged = this$static.resolution != resolution;
  resolutionChanged && (this$static.minResolutionWidth = -1);
  if (this$static.forceUpdateFlag) {
    this$static.forceUpdateFlag = false;
    return true;
  }
  return resolutionChanged || neq(this$static.startDate, startDate) || neq(this$static.endDate, endDate) || this$static.firstDayOfWeek != firstDayOfWeek || this$static.firstDayOfRange != firstDayOfRange || this$static.firstHourOfRange != firstHourOfRange || this$static.locale == null && locale != null || this$static.locale != null && !$equals_0(this$static.locale, locale);
}

function $isValidChildIndex(index_0, childCount){
  return index_0 >= 0 && index_0 < childCount;
}

function $isWeekEnd(dayCounter){
  return dayCounter == 1 || dayCounter == 7;
}

function $prepareTimelineForHourResolution(this$static, startDate, endDate, resBlockRegisterer){
  var adjusted, currentDay, currentMonth, currentYear, date, day, index_0, m, month, nextHour, pos, year;
  this$static.blocksInRange = 0;
  this$static.resolutionBlockCount = 0;
  this$static.firstResBlockCount = 0;
  this$static.lastResBlockCount = 0;
  currentYear = null;
  currentMonth = null;
  currentDay = null;
  pos = startDate;
  index_0 = 0;
  while (compare_0(pos, endDate) <= 0) {
    date = new Date_1(pos);
    nextHour = new Date_1(add_1(pos, $intern_29));
    gt(fromDouble_0(nextHour.jsdate.getTime()), endDate);
    $registerHourResolutionBlock(resBlockRegisterer.this$01);
    resBlockRegisterer.hourCounter = $wnd.Math.max((resBlockRegisterer.hourCounter + 1) % 25, 1);
    this$static.yearRowVisible && (currentYear = (year = $formatDate_0(this$static.localeDataProvider, date, (!this$static.yearDateTimeFormat && (this$static.yearDateTimeFormat = ($clinit_DateTimeFormat() , getFormat('yyyy', $getDateTimeFormatInfo(($clinit_LocaleInfo() , $clinit_LocaleInfo() , instance_1))))) , this$static.yearDateTimeFormat)) , $addBlock(currentYear, year, date, this$static.yearRowData, new TimelineWidget$8(this$static))));
    this$static.monthRowVisible && (currentMonth = (month = (m = $formatDate_0(this$static.localeDataProvider, date, (!this$static.monthDateTimeFormat && (this$static.monthDateTimeFormat = ($clinit_DateTimeFormat() , getFormat('M', $getDateTimeFormatInfo(($clinit_LocaleInfo() , $clinit_LocaleInfo() , instance_1))))) , this$static.monthDateTimeFormat)) , __parseAndValidateInt(m) - 1) , $addBlock(currentMonth, '' + month, date, this$static.monthRowData, new TimelineWidget$7(this$static, month))));
    this$static.resolution == ($clinit_Resolution() , Hour) && (currentDay = (day = (adjusted = new Date_1($adjustToMiddleOfDay(this$static, fromDouble_0(date.jsdate.getTime()))) , $formatDate_0(this$static.localeDataProvider, adjusted, (!this$static.dayDateTimeFormat && (this$static.dayDateTimeFormat = ($clinit_DateTimeFormat() , getFormat('d', $getDateTimeFormatInfo(($clinit_LocaleInfo() , $clinit_LocaleInfo() , instance_1))))) , this$static.dayDateTimeFormat))) , $addBlock(currentDay, day, date, this$static.dayRowData, new TimelineWidget$6(this$static))));
    pos = fromDouble_0(nextHour.jsdate.getTime());
    ++index_0;
  }
}

function $prepareTimelineForResolution(this$static, startDate, endDate, resBlockRegisterer){
  var adjusted, currentDay, currentMonth, currentYear, date, day, dstAdjusted, index_0, isDST, isPreviousDst, lastTimelineBlock, m, month, pos, year;
  this$static.blocksInRange = 0;
  this$static.resolutionBlockCount = 0;
  this$static.firstResBlockCount = 0;
  this$static.lastResBlockCount = 0;
  currentYear = null;
  currentMonth = null;
  currentDay = null;
  pos = $adjustToMiddleOfDay(this$static, startDate);
  index_0 = 0;
  lastTimelineBlock = false;
  isPreviousDst = $getDaylightAdjustment(this$static.localeDataProvider.timeZone, new Date_1(startDate)) > 0;
  while (!lastTimelineBlock) {
    dstAdjusted = $getDSTAdjustedDate(this$static, isPreviousDst, pos);
    date = new Date_1(dstAdjusted);
    pos = dstAdjusted;
    isDST = $getDaylightAdjustment(this$static.localeDataProvider.timeZone, date) > 0;
    lastTimelineBlock = gt($getDSTAdjustedDate(this$static, isDST, add_1(fromDouble_0(date.jsdate.getTime()), $intern_10)), endDate);
    $registerResolutionBlock(resBlockRegisterer, index_0, lastTimelineBlock);
    this$static.yearRowVisible && (currentYear = (year = $formatDate_0(this$static.localeDataProvider, date, (!this$static.yearDateTimeFormat && (this$static.yearDateTimeFormat = ($clinit_DateTimeFormat() , getFormat('yyyy', $getDateTimeFormatInfo(($clinit_LocaleInfo() , $clinit_LocaleInfo() , instance_1))))) , this$static.yearDateTimeFormat)) , $addBlock(currentYear, year, date, this$static.yearRowData, new TimelineWidget$8(this$static))));
    this$static.monthRowVisible && (currentMonth = (month = (m = $formatDate_0(this$static.localeDataProvider, date, (!this$static.monthDateTimeFormat && (this$static.monthDateTimeFormat = ($clinit_DateTimeFormat() , getFormat('M', $getDateTimeFormatInfo(($clinit_LocaleInfo() , $clinit_LocaleInfo() , instance_1))))) , this$static.monthDateTimeFormat)) , __parseAndValidateInt(m) - 1) , $addBlock(currentMonth, '' + month, date, this$static.monthRowData, new TimelineWidget$7(this$static, month))));
    this$static.resolution == ($clinit_Resolution() , Hour) && (currentDay = (day = (adjusted = new Date_1($adjustToMiddleOfDay(this$static, fromDouble_0(date.jsdate.getTime()))) , $formatDate_0(this$static.localeDataProvider, adjusted, (!this$static.dayDateTimeFormat && (this$static.dayDateTimeFormat = ($clinit_DateTimeFormat() , getFormat('d', $getDateTimeFormatInfo(($clinit_LocaleInfo() , $clinit_LocaleInfo() , instance_1))))) , this$static.dayDateTimeFormat))) , $addBlock(currentDay, day, date, this$static.dayRowData, new TimelineWidget$6(this$static))));
    isPreviousDst = isDST;
    pos = add_1(pos, $intern_10);
    ++index_0;
  }
}

function $registerDayResolutionBlock(this$static){
  ++this$static.blocksInRange;
  ++this$static.resolutionBlockCount;
}

function $registerHourResolutionBlock(this$static){
  ++this$static.blocksInRange;
  ++this$static.resolutionBlockCount;
}

function $registerWeekResolutionBlock(this$static, index_0, weekDay, lastBlock, firstWeek){
  (index_0 == 0 || weekDay == ($clinit_TimelineWidget$Weekday() , First)) && ++this$static.resolutionBlockCount;
  firstWeek && (weekDay == ($clinit_TimelineWidget$Weekday() , Last) || lastBlock)?(this$static.firstResBlockCount = index_0 + 1):lastBlock && (this$static.lastResBlockCount = (index_0 + 1 - this$static.firstResBlockCount) % 7);
  ++this$static.blocksInRange;
}

function $setAlwaysCalculatePixelWidths_0(this$static, calcPx){
  this$static.calcPixels = calcPx;
}

function $setBrowserInfo_0(this$static, ie){
  this$static.ie = ie;
}

function $setCssPercentageWidth(element, nValue, pct){
  pct != null?(element.style['width'] = pct , undefined):(element.style['width'] = nValue + ($clinit_Style$Unit() , '%') , undefined);
}

function $setCssPercentageWidth_0(this$static, element, daysInRange, width_0, position){
  var pct;
  pct = $createCalcCssValue(this$static, daysInRange, position);
  pct != null?(element.style['width'] = pct , undefined):(element.style['width'] = position * width_0 + ($clinit_Style$Unit() , '%') , undefined);
}

function $setDayFormat_1(this$static, dayFormat){
  this$static.dayFormat = dayFormat;
}

function $setMinWidth(this$static, minWidth){
  this$static.minWidth = minWidth;
  ($clinit_DOM() , this$static.element).style['minWidth'] = this$static.minWidth + 'px';
  this$static.resolutionDiv.style['minWidth'] = this$static.minWidth + 'px';
}

function $setMonthFormat_1(this$static, monthFormat){
  this$static.monthFormat = monthFormat;
}

function $setMonthRowVisible_1(this$static, monthRowVisible){
  this$static.monthRowVisible = monthRowVisible;
}

function $setNoticeVerticalScrollbarWidth(this$static, noticeVerticalScrollbarWidth){
  noticeVerticalScrollbarWidth?(($clinit_DOM() , this$static.element).style['marginRight'] = (maybeRecalculateNativeScrollbarSize() , nativeWidth + ($clinit_Style$Unit() , 'px')) , undefined):(($clinit_DOM() , this$static.element).style['marginRight'] = '' , undefined);
}

function $setParentElement(this$static, element){
  this$static.parentElement = element;
}

function $setScrollLeft_0(this$static, left){
  if (this$static.positionLeft == left) {
    return;
  }
  this$static.positionLeft = left;
  ($clinit_DOM() , this$static.element).style['left'] = -left + ($clinit_Style$Unit() , 'px');
  $schedule(this$static.lazyResolutionPaint, 20);
}

function $setWeekFormat_1(this$static, weekFormat){
  this$static.weekFormat = weekFormat;
}

function $setWidth_1(this$static, resBlockWidthPx, pct, element){
  if (this$static.timelineOverflowingHorizontally) {
    element.style['width'] = this$static.resBlockMinWidthPx + ($clinit_Style$Unit() , 'px');
  }
   else {
    if (this$static.calcPixels) {
      element.style['width'] = resBlockWidthPx + ($clinit_Style$Unit() , 'px');
    }
     else {
      this$static.ie && (element.style['flex'] = '1' , undefined);
      $setCssPercentageWidth(element, this$static.resBlockWidthPercentage, pct);
    }
  }
}

function $setWidth_2(this$static, element, multiplier){
  this$static.timelineOverflowingHorizontally?(element.style['width'] = multiplier * this$static.minResolutionWidth + ($clinit_Style$Unit() , 'px') , undefined):this$static.calcPixels?(element.style['width'] = multiplier * this$static.dayOrHourWidthPx + ($clinit_Style$Unit() , 'px') , undefined):$setCssPercentageWidth_0(this$static, element, this$static.blocksInRange, this$static.dayWidthPercentage, multiplier);
}

function $setYearFormat_1(this$static, yearFormat){
  this$static.yearFormat = yearFormat;
}

function $setYearRowVisible_1(this$static, yearRowVisible){
  this$static.yearRowVisible = yearRowVisible;
}

function $showAllResolutionBlocks(this$static){
  if (this$static.styleElementForLeft) {
    this$static.parentElement.setAttribute('col.position', 'relative');
    this$static.parentElement.setAttribute('col-left', '0');
  }
  $fillTimelineForResolution(this$static, this$static.startDate, this$static.endDate, 0);
}

function $showResolutionBlocksOnView(this$static){
  var containerWidth, datePos, datePos0, firstResBlockShort, left, leftDate, noticeDst, overflow, overflow0, positionLeftSnapshot, overflow_0;
  positionLeftSnapshot = this$static.positionLeft;
  datePos0 = positionLeftSnapshot;
  this$static.firstWeekBlockHidden = false;
  left = round_int(positionLeftSnapshot);
  if (positionLeftSnapshot > 0 && this$static.resBlockWidthPx > 0) {
    firstResBlockShort = this$static.firstResBlockCount > 0 && this$static.resolution == ($clinit_Resolution() , Week) && this$static.firstResBlockCount < 7;
    overflow0 = (firstResBlockShort && left <= (this$static.firstResBlockCount > 0 && this$static.resolution == ($clinit_Resolution() , Week) && this$static.firstResBlockCount < 7?this$static.timelineOverflowingHorizontally?this$static.firstResBlockCount * this$static.minResolutionWidth:getBoundingClientRectWidth($getFirstResolutionElement(this$static)):this$static.timelineOverflowingHorizontally?this$static.resBlockMinWidthPx:getBoundingClientRectWidth($getFirstResolutionElement(this$static)))?(overflow = (overflow_0 = positionLeftSnapshot % (this$static.firstResBlockCount > 0 && this$static.resolution == ($clinit_Resolution() , Week) && this$static.firstResBlockCount < 7?this$static.timelineOverflowingHorizontally?this$static.firstResBlockCount * this$static.minResolutionWidth:getBoundingClientRectWidth($getFirstResolutionElement(this$static)):this$static.timelineOverflowingHorizontally?this$static.resBlockMinWidthPx:getBoundingClientRectWidth($getFirstResolutionElement(this$static))) , overflow_0 == 0 && (overflow_0 = this$static.firstResBlockCount > 0 && this$static.resolution == ($clinit_Resolution() , Week) && this$static.firstResBlockCount < 7?this$static.timelineOverflowingHorizontally?this$static.firstResBlockCount * this$static.minResolutionWidth:getBoundingClientRectWidth($getFirstResolutionElement(this$static)):this$static.timelineOverflowingHorizontally?this$static.resBlockMinWidthPx:getBoundingClientRectWidth($getFirstResolutionElement(this$static))) , overflow_0)):(overflow = $getScrollOverflowForRegularResoultionBlock(this$static, positionLeftSnapshot, firstResBlockShort)) , overflow);
    left = round_int(positionLeftSnapshot - overflow0);
    datePos0 = (this$static.resolution == ($clinit_Resolution() , Week)?(datePos = left + this$static.dayOrHourWidthPx / 2):(datePos = left + this$static.resBlockWidthPx / 2) , datePos);
  }
  datePos0 < 0 && (datePos0 = positionLeftSnapshot);
  noticeDst = this$static.resolution == ($clinit_Resolution() , Hour);
  leftDate = $getDateForLeftPosition_0(this$static, datePos0, noticeDst);
  containerWidth = getBoundingClientRectWidth(this$static.parentElement);
  $fillTimelineForResolution(this$static, leftDate, min_1(this$static.endDate, $getDateForLeftPosition_0(this$static, datePos0 + containerWidth, noticeDst)), left);
  if (this$static.styleElementForLeft) {
    this$static.parentElement.setAttribute('col-position', 'relative');
    $setAttribute(this$static.parentElement, 'col-left', '' + left);
  }
  $ensureNamesAreInitialized(Lorg_tltv_gantt_client_TimelineWidget_2_classLit);
}

function $toCssCalcOrNumberString(this$static, value_0, unit){
  if (this$static.ie) {
    return 'calc(' + value_0 + unit + ')';
  }
  return value_0 + unit;
}

function $toNormalDate(this$static, zonedDate){
  return sub_1(zonedDate, $getDaylightAdjustment_0(this$static.localeDataProvider, new Date_1(zonedDate)));
}

function $update_0(this$static, resolution, startDate, endDate, firstDayOfRange, firstHourOfRange, localeDataProvider){
  if (!localeDataProvider) {
    $ensureNamesAreInitialized(Lorg_tltv_gantt_client_TimelineWidget_2_classLit);
    return;
  }
  if ($isChanged(this$static, resolution, startDate, endDate, $getFirstDayOfWeek(localeDataProvider), firstDayOfRange, firstHourOfRange, localeDataProvider.locale)) {
    $clear_0(this$static);
    $ensureNamesAreInitialized(Lorg_tltv_gantt_client_TimelineWidget_2_classLit);
  }
   else {
    return;
  }
  $ensureNamesAreInitialized(Lorg_tltv_gantt_client_TimelineWidget_2_classLit);
  $injectStyle(this$static);
  $injectLeftStyle(this$static);
  if (this$static.styleElementForLeft) {
    this$static.parentElement.setAttribute('col-position', 'relative');
    this$static.parentElement.setAttribute('col-left', '0');
  }
  this$static.localeDataProvider = localeDataProvider;
  this$static.locale = localeDataProvider.locale;
  this$static.resolution = resolution;
  this$static.startDate = startDate;
  this$static.endDate = endDate;
  this$static.normalStartDate = sub_1(startDate, $getDaylightAdjustment_0(this$static.localeDataProvider, new Date_1(startDate)));
  this$static.normalEndDate = sub_1(endDate, $getDaylightAdjustment_0(this$static.localeDataProvider, new Date_1(endDate)));
  this$static.firstDayOfWeek = $getFirstDayOfWeek(localeDataProvider);
  this$static.lastDayOfWeek = this$static.firstDayOfWeek == 1?7:$wnd.Math.max((this$static.firstDayOfWeek - 1) % 8, 1);
  this$static.firstDayOfRange = firstDayOfRange;
  this$static.firstHourOfRange = firstHourOfRange;
  this$static.monthNames = $getMonthNames(localeDataProvider);
  $getWeekdayNames(localeDataProvider);
  this$static.resolutionDiv = ($clinit_DOM() , $doc.createElement('div'));
  this$static.resolutionDiv.className = 'row resolution';
  this$static.minResolutionWidth < 0 && (this$static.minResolutionWidth = $calculateResolutionMinWidth(this$static));
  if (resolution == ($clinit_Resolution() , Day) || resolution == Week) {
    $prepareTimelineForResolution(this$static, startDate, endDate, new TimelineWidget$3(this$static));
  }
   else if (resolution == Hour) {
    this$static.firstDay = true;
    $prepareTimelineForHourResolution(this$static, startDate, endDate, new TimelineWidget$2(this$static));
  }
   else {
    $ensureNamesAreInitialized(Lorg_tltv_gantt_client_TimelineWidget_2_classLit);
    return;
  }
  this$static.yearRowVisible && $appendTimelineBlocks(this$static, this$static.yearRowData);
  this$static.monthRowVisible && $appendTimelineBlocks(this$static, this$static.monthRowData);
  this$static.resolution == Hour && $appendTimelineBlocks(this$static, this$static.dayRowData);
  $appendChild(this$static.element, this$static.resolutionDiv);
  $ensureNamesAreInitialized(Lorg_tltv_gantt_client_TimelineWidget_2_classLit);
  $updateWidths(this$static);
  $ensureNamesAreInitialized(Lorg_tltv_gantt_client_TimelineWidget_2_classLit);
}

function $updateBlockWidths(this$static, rowData){
  var entry, entry$iterator;
  for (entry$iterator = new LinkedHashMap$EntrySet$EntryIterator(new LinkedHashMap$EntrySet(rowData.blocks)); entry$iterator.next_0 != entry$iterator.this$11.this$01.head_0;) {
    entry = $next_1(entry$iterator);
    $setWidth_2(this$static, castToJso(entry.value_0), $getBlockLength(rowData, castToString(entry.key)).value_0);
  }
}

function $updateResolutionBlockWidths(this$static, pct){
  var center, count, first, firstResBlockIsShort, i_0, last, lastIndex, lastResBlockIsShort, resBlock;
  if (!this$static.styleElement) {
    this$static.timelineOverflowingHorizontally?(this$static.resolutionDiv.style['display'] = '' , undefined):(this$static.resolutionDiv.style['display'] = 'flex' , undefined);
    firstResBlockIsShort = this$static.firstResBlockCount > 0 && this$static.resolution == ($clinit_Resolution() , Week) && this$static.firstResBlockCount < 7;
    lastResBlockIsShort = this$static.lastResBlockCount > 0 && this$static.resolution == ($clinit_Resolution() , Week) && this$static.lastResBlockCount < 7;
    count = this$static.resolutionDiv.childNodes.length;
    !!this$static.resSpacerDiv && !!$getParentElement(this$static.resSpacerDiv) && $equals($getParentElement(this$static.resSpacerDiv), this$static.resolutionDiv) && --count;
    lastIndex = count - 1;
    for (i_0 = 0; i_0 < count; i_0++) {
      resBlock = $getChild(this$static.resolutionDiv, i_0);
      firstResBlockIsShort && i_0 == 0?$setWidth_2(this$static, resBlock, this$static.firstResBlockCount):lastResBlockIsShort && i_0 == lastIndex?$setWidth_2(this$static, resBlock, this$static.lastResBlockCount):$setWidth_1(this$static, this$static.resBlockWidthPx, pct, resBlock);
    }
  }
   else {
    center = this$static.timelineOverflowingHorizontally?this$static.resBlockMinWidthPx + ($clinit_Style$Unit() , 'px'):this$static.calcPixels?this$static.resBlockWidthPx + ($clinit_Style$Unit() , 'px'):$getCssPercentageWidth(this$static.resBlockWidthPercentage, pct);
    first = center;
    last = center;
    this$static.firstResBlockCount > 0 && this$static.resolution == ($clinit_Resolution() , Week) && this$static.firstResBlockCount < 7 && (first = $getWidth(this$static, this$static.firstResBlockCount));
    this$static.lastResBlockCount > 0 && this$static.resolution == ($clinit_Resolution() , Week) && this$static.lastResBlockCount < 7 && (last = $getWidth(this$static, this$static.lastResBlockCount));
    $setAttribute(this$static.parentElement, 'col-center-width', center);
    $setAttribute(this$static.parentElement, 'col-first-width', first);
    $setAttribute(this$static.parentElement, 'col-last-width', last);
  }
}

function $updateSpacerBlocks(this$static, dayWidthPx){
  var e, e$iterator, entry, outerIter, spaceLeft;
  spaceLeft = $getResolutionDivWidth(this$static) - this$static.blocksInRange * dayWidthPx;
  if (spaceLeft > 0) {
    for (e$iterator = (outerIter = (new AbstractMap$1(this$static.spacerBlocks.map_0)).this$01.entrySet().iterator() , new AbstractMap$1$1(outerIter)); e$iterator.val$outerIter2.hasNext_0();) {
      e = (entry = castTo(e$iterator.val$outerIter2.next_1(), 16) , castToJso(entry.getKey()));
      e.style['display'] = '';
      e.style['width'] = spaceLeft + ($clinit_Style$Unit() , 'px');
    }
    this$static.resSpacerDiv = $createResolutionBlock();
    $addClassName(this$static.resSpacerDiv, 'spacer');
    this$static.resSpacerDiv.style['width'] = spaceLeft + ($clinit_Style$Unit() , 'px');
    this$static.resSpacerDiv.textContent = ' ';
    $appendChild(this$static.resolutionDiv, this$static.resSpacerDiv);
  }
   else {
    $hideSpacerBlocks(this$static);
  }
}

function $updateWidths(this$static){
  var pct;
  if (!this$static.resolutionDiv) {
    $ensureNamesAreInitialized(Lorg_tltv_gantt_client_TimelineWidget_2_classLit);
    return;
  }
  $ensureNamesAreInitialized(Lorg_tltv_gantt_client_TimelineWidget_2_classLit);
  $removeAllChildren(this$static.resolutionDiv);
  $setMinWidth(this$static, this$static.blocksInRange * this$static.minResolutionWidth);
  this$static.timelineOverflowingHorizontally = getBoundingClientRectWidth(this$static.resolutionDiv) > getBoundingClientRectWidth(this$static.parentElement);
  $createTimelineElementsOnVisibleArea(this$static);
  this$static.timelineOverflowingHorizontally?$showResolutionBlocksOnView(this$static):$showAllResolutionBlocks(this$static);
  !!this$static.resSpacerDiv && !!$getParentElement(this$static.resSpacerDiv) && $equals($getParentElement(this$static.resSpacerDiv), this$static.resolutionDiv) && $removeFromParent(this$static.resSpacerDiv);
  this$static.dayWidthPercentage = 100 / this$static.blocksInRange;
  this$static.dayOrHourWidthPx = $calculateDayOrHourResolutionBlockWidthPx(this$static, this$static.blocksInRange);
  this$static.resBlockMinWidthPx = this$static.minResolutionWidth;
  this$static.resBlockWidthPx = $calculateActualResolutionBlockWidthPx(this$static, this$static.dayOrHourWidthPx);
  this$static.resBlockWidthPercentage = 100 / this$static.resolutionBlockCount;
  pct = $createCalcCssValue_0(this$static, this$static.resolutionBlockCount);
  if (this$static.resolution == ($clinit_Resolution() , Week)) {
    this$static.resBlockMinWidthPx = 7 * this$static.minResolutionWidth;
    this$static.resBlockWidthPercentage = this$static.dayWidthPercentage * 7;
    pct = $createCalcCssValue(this$static, this$static.blocksInRange, 7);
  }
  $updateResolutionBlockWidths(this$static, pct);
  this$static.yearRowVisible && $updateBlockWidths(this$static, this$static.yearRowData);
  this$static.monthRowVisible && $updateBlockWidths(this$static, this$static.monthRowData);
  this$static.resolution == Hour && $updateBlockWidths(this$static, this$static.dayRowData);
  this$static.calcPixels && $updateSpacerBlocks(this$static, this$static.dayOrHourWidthPx);
  $ensureNamesAreInitialized(Lorg_tltv_gantt_client_TimelineWidget_2_classLit);
}

function TimelineWidget(){
  this.spacerBlocks = new HashSet;
  this.yearRowData = new TimelineWidget$BlockRowData;
  this.monthRowData = new TimelineWidget$BlockRowData;
  this.dayRowData = new TimelineWidget$BlockRowData;
  this.lazyResolutionPaint = new TimelineWidget$1(this);
  $setElement(this, ($clinit_DOM() , $doc.createElement('div')));
  this.element.className = 'timeline';
}

defineClass(116, 20, $intern_23, TimelineWidget);
_.onUnload = function onUnload_0(){
  $onDetach(this);
  !!this.styleElement && $removeFromParent(this.styleElement);
  !!this.styleElementForLeft && $removeFromParent(this.styleElementForLeft);
}
;
_.blocksInRange = 0;
_.calcPixels = false;
_.dayOrHourWidthPx = 0;
_.dayWidthPercentage = 0;
_.endDate = 0;
_.firstDay = false;
_.firstDayOfRange = 0;
_.firstDayOfWeek = 0;
_.firstHourOfRange = 0;
_.firstResBlockCount = 0;
_.firstWeekBlockHidden = false;
_.forceUpdateFlag = false;
_.ie = false;
_.lastDayOfWeek = 0;
_.lastResBlockCount = 0;
_.minResolutionWidth = -1;
_.minWidth = -1;
_.monthRowVisible = false;
_.normalEndDate = 0;
_.normalStartDate = 0;
_.positionLeft = 0;
_.resBlockMinWidthPx = 0;
_.resBlockWidthPercentage = 0;
_.resBlockWidthPx = 0;
_.resolutionBlockCount = 0;
_.resolutionWeekDayblockWidth = 4;
_.startDate = 0;
_.timelineOverflowingHorizontally = false;
_.yearRowVisible = false;
var Lorg_tltv_gantt_client_TimelineWidget_2_classLit = createForClass('org.tltv.gantt.client', 'TimelineWidget', 116, Lcom_google_gwt_user_client_ui_Widget_2_classLit);
function TimelineWidget$1(this$0){
  this.this$01 = this$0;
  Timer.call(this);
}

defineClass(249, 48, {}, TimelineWidget$1);
_.run = function run_6(){
  $fillVisibleTimeline(this.this$01);
}
;
var Lorg_tltv_gantt_client_TimelineWidget$1_2_classLit = createForClass('org.tltv.gantt.client', 'TimelineWidget/1', 249, Lcom_google_gwt_user_client_Timer_2_classLit);
function TimelineWidget$2(this$0){
  this.this$01 = this$0;
  this.hourCounter = this.this$01.firstHourOfRange;
}

defineClass(250, 1, {}, TimelineWidget$2);
_.hourCounter = 0;
var Lorg_tltv_gantt_client_TimelineWidget$2_2_classLit = createForClass('org.tltv.gantt.client', 'TimelineWidget/2', 250, Ljava_lang_Object_2_classLit);
function $registerResolutionBlock(this$static, index_0, lastTimelineBlock){
  this$static.weekday = $getWeekday(this$static.this$01, this$static.dayCounter);
  if (this$static.this$01.resolution == ($clinit_Resolution() , Week)) {
    $registerWeekResolutionBlock(this$static.this$01, index_0, this$static.weekday, lastTimelineBlock, this$static.firstWeek);
    this$static.firstWeek && (this$static.weekday == ($clinit_TimelineWidget$Weekday() , Last) || lastTimelineBlock) && (this$static.firstWeek = false);
  }
   else {
    $registerDayResolutionBlock(this$static.this$01);
  }
  this$static.dayCounter = $wnd.Math.max((this$static.dayCounter + 1) % 8, 1);
}

function TimelineWidget$3(this$0){
  this.this$01 = this$0;
  this.dayCounter = this.this$01.firstDayOfRange;
}

defineClass(251, 1, {}, TimelineWidget$3);
_.dayCounter = 0;
_.firstWeek = true;
var Lorg_tltv_gantt_client_TimelineWidget$3_2_classLit = createForClass('org.tltv.gantt.client', 'TimelineWidget/3', 251, Ljava_lang_Object_2_classLit);
function $fillResolutionBlock(this$static, index_0, date, lastTimelineBlock){
  var childCount, resBlock;
  childCount = this$static.this$01.resolutionDiv.childNodes.length;
  if (index_0 >= 0 && index_0 < childCount) {
    resBlock = $getChild(this$static.this$01.resolutionDiv, index_0);
    $fillHourResolutionBlock(this$static.this$01, resBlock, date, index_0, this$static.hourCounter, lastTimelineBlock, this$static.val$left4, this$static.even);
    this$static.hourCounter = (this$static.hourCounter + 1) % 24;
    this$static.even = !this$static.even;
  }
   else {
    return;
  }
}

function $getFirstHourOfVisibleRange(this$static, startDate){
  var hours, normalDate;
  normalDate = $toNormalDate(this$static.this$01, startDate);
  if (lt(this$static.this$01.normalStartDate, normalDate)) {
    hours = toInt_0(div_0(sub_1(normalDate, this$static.this$01.normalStartDate), $intern_29));
    return (this$static.this$01.firstHourOfRange + hours) % 24;
  }
  return this$static.this$01.firstHourOfRange;
}

function $isEven(this$static, startDate){
  var hours, normalDate;
  normalDate = $toNormalDate(this$static.this$01, startDate);
  if (lt(this$static.this$01.normalStartDate, normalDate)) {
    hours = toInt_0(div_0(sub_1(normalDate, this$static.this$01.normalStartDate), $intern_29));
    return hours % 2 == 1;
  }
  return false;
}

function TimelineWidget$4(this$0, val$startDate, val$left){
  this.this$01 = this$0;
  this.val$startDate2 = val$startDate;
  this.val$left4 = val$left;
  this.hourCounter = $getFirstHourOfVisibleRange(this, this.val$startDate2);
  this.even = $isEven(this, this.val$startDate2);
}

defineClass(252, 1, {}, TimelineWidget$4);
_.even = false;
_.hourCounter = 0;
_.val$left4 = 0;
_.val$startDate2 = 0;
var Lorg_tltv_gantt_client_TimelineWidget$4_2_classLit = createForClass('org.tltv.gantt.client', 'TimelineWidget/4', 252, Ljava_lang_Object_2_classLit);
function $calcDaysLeftInFirstWeek(this$static, startDay){
  var daysLeftInWeek, i_0;
  daysLeftInWeek = 0;
  if (startDay != this$static.this$01.firstDayOfWeek) {
    for (i_0 = startDay;; i_0++) {
      ++daysLeftInWeek;
      if ($wnd.Math.max(i_0 % 8, 1) == this$static.this$01.lastDayOfWeek) {
        break;
      }
    }
  }
  return daysLeftInWeek;
}

function $fillDayBlock(this$static, left, index_0, date){
  var childCount, resBlock;
  childCount = this$static.this$01.resolutionDiv.childNodes.length;
  if (index_0 >= 0 && index_0 < childCount) {
    resBlock = $getChild(this$static.this$01.resolutionDiv, index_0);
    $fillDayResolutionBlock(this$static.this$01, resBlock, date, $isWeekEnd(this$static.dayCounter), left);
  }
   else {
    return;
  }
}

function $fillResolutionBlock_0(this$static, index_0, date, lastTimelineBlock){
  try {
    this$static.weekday = $getWeekday(this$static.this$01, this$static.dayCounter);
    this$static.this$01.resolution == ($clinit_Resolution() , Week)?$fillWeekBlock(this$static, this$static.val$left4, index_0, date, lastTimelineBlock):$fillDayBlock(this$static, this$static.val$left4, index_0, date);
  }
   finally {
    this$static.dayCounter = $wnd.Math.max((this$static.dayCounter + 1) % 8, 1);
  }
}

function $fillWeekBlock(this$static, left, index_0, date, lastTimelineBlock){
  var childCount, resBlock;
  resBlock = null;
  if (index_0 > 0 && this$static.weekday == ($clinit_TimelineWidget$Weekday() , First)) {
    ++this$static.weekIndex;
    this$static.firstWeek = false;
    this$static.even = !this$static.even;
  }
  if (index_0 == 0 || this$static.weekday == ($clinit_TimelineWidget$Weekday() , First)) {
    childCount = this$static.this$01.resolutionDiv.childNodes.length;
    if ($isValidChildIndex(this$static.weekIndex, childCount)) {
      resBlock = $getChild(this$static.this$01.resolutionDiv, this$static.weekIndex);
    }
     else {
      return;
    }
  }
  $fillWeekResolutionBlock(this$static.this$01, resBlock, date, this$static.weekday, this$static.firstWeek, lastTimelineBlock, left, this$static.even);
}

function $getFirstDayOfVisibleRange(this$static, startDate){
  var days, visibleRangeNormalStartDate;
  visibleRangeNormalStartDate = $toNormalDate(this$static.this$01, startDate);
  if (lt(this$static.this$01.normalStartDate, visibleRangeNormalStartDate)) {
    days = toInt_0(div_0(sub_1(visibleRangeNormalStartDate, this$static.this$01.normalStartDate), $intern_10));
    return (this$static.this$01.firstDayOfRange - 1 + days) % 7 + 1;
  }
  return this$static.this$01.firstDayOfRange;
}

function $isEven_0(this$static, startDate, startDay){
  var daysHidden, daysLeftInFirstWeek, even, visibleRangeNormalStartDate, weeks;
  visibleRangeNormalStartDate = $toNormalDate(this$static.this$01, startDate);
  if (lt(this$static.this$01.normalStartDate, visibleRangeNormalStartDate)) {
    daysHidden = toInt_0(div_0(sub_1(visibleRangeNormalStartDate, this$static.this$01.normalStartDate), $intern_10));
    if (daysHidden == 0) {
      return false;
    }
    daysLeftInFirstWeek = $calcDaysLeftInFirstWeek(this$static, startDay);
    daysHidden > daysLeftInFirstWeek && (daysHidden -= daysLeftInFirstWeek);
    weeks = daysHidden / 7 | 0;
    even = weeks % 2 == 1;
    return this$static.this$01.firstWeekBlockHidden?!even:even;
  }
  return false;
}

function TimelineWidget$5(this$0, val$startDate, val$left){
  this.this$01 = this$0;
  this.val$startDate2 = val$startDate;
  this.val$left4 = val$left;
  this.dayCounter = $getFirstDayOfVisibleRange(this, this.val$startDate2);
  this.even = $isEven_0(this, this.val$startDate2, this.this$01.firstDayOfRange);
}

defineClass(253, 1, {}, TimelineWidget$5);
_.dayCounter = 0;
_.even = false;
_.firstWeek = true;
_.val$left4 = 0;
_.val$startDate2 = 0;
_.weekIndex = 0;
var Lorg_tltv_gantt_client_TimelineWidget$5_2_classLit = createForClass('org.tltv.gantt.client', 'TimelineWidget/5', 253, Ljava_lang_Object_2_classLit);
function TimelineWidget$6(this$0){
  this.this$01 = this$0;
}

defineClass(117, 1, {}, TimelineWidget$6);
_.run_0 = function run_7(day, key, date){
  $addDayBlock(this.this$01, key, $formatDayCaption(this.this$01, day, date));
}
;
var Lorg_tltv_gantt_client_TimelineWidget$6_2_classLit = createForClass('org.tltv.gantt.client', 'TimelineWidget/6', 117, Ljava_lang_Object_2_classLit);
function TimelineWidget$7(this$0, val$month){
  this.this$01 = this$0;
  this.val$month2 = val$month;
}

defineClass(118, 1, {}, TimelineWidget$7);
_.run_0 = function run_8(target, key, date){
  $addMonthBlock(this.this$01, key, $formatMonthCaption(this.this$01, this.val$month2, date));
}
;
_.val$month2 = 0;
var Lorg_tltv_gantt_client_TimelineWidget$7_2_classLit = createForClass('org.tltv.gantt.client', 'TimelineWidget/7', 118, Ljava_lang_Object_2_classLit);
function TimelineWidget$8(this$0){
  this.this$01 = this$0;
}

defineClass(119, 1, {}, TimelineWidget$8);
_.run_0 = function run_9(year, key, date){
  $addYearBlock(this.this$01, key, $formatYearCaption(this.this$01, year, date));
}
;
var Lorg_tltv_gantt_client_TimelineWidget$8_2_classLit = createForClass('org.tltv.gantt.client', 'TimelineWidget/8', 119, Ljava_lang_Object_2_classLit);
function $clear_1(this$static){
  $clear(this$static.blocks);
  $clear(this$static.blockLength);
}

function $getBlockLength(this$static, key){
  return castTo($get_6(this$static.blockLength, key), 43);
}

function $setBlockLength(this$static, key, length_0){
  $put_3(this$static.blockLength, key, length_0);
}

function TimelineWidget$BlockRowData(){
  this.blocks = new LinkedHashMap;
  this.blockLength = new LinkedHashMap;
}

defineClass(92, 1, {}, TimelineWidget$BlockRowData);
var Lorg_tltv_gantt_client_TimelineWidget$BlockRowData_2_classLit = createForClass('org.tltv.gantt.client', 'TimelineWidget/BlockRowData', 92, Ljava_lang_Object_2_classLit);
function $clinit_TimelineWidget$Weekday(){
  $clinit_TimelineWidget$Weekday = emptyMethod;
  First = new TimelineWidget$Weekday('First', 0);
  Between = new TimelineWidget$Weekday('Between', 1);
  Last = new TimelineWidget$Weekday('Last', 2);
}

function TimelineWidget$Weekday(enum$name, enum$ordinal){
  Enum.call(this, enum$name, enum$ordinal);
}

function values_8(){
  $clinit_TimelineWidget$Weekday();
  return stampJavaTypeInfo(getClassLiteralForArray(Lorg_tltv_gantt_client_TimelineWidget$Weekday_2_classLit, 1), $intern_0, 69, 0, [First, Between, Last]);
}

defineClass(69, 5, {3:1, 6:1, 5:1, 69:1}, TimelineWidget$Weekday);
var Between, First, Last;
var Lorg_tltv_gantt_client_TimelineWidget$Weekday_2_classLit = createForEnum('org.tltv.gantt.client', 'TimelineWidget/Weekday', 69, Ljava_lang_Enum_2_classLit, values_8);
function $equals_2(this$static, obj){
  var other;
  if (this$static === obj) {
    return true;
  }
  if (obj == null) {
    return false;
  }
  if (!instanceOf(obj, 46)) {
    return false;
  }
  other = castTo(obj, 46);
  if (this$static.uid == null) {
    if (other.uid != null) {
      return false;
    }
  }
   else if (!$equals_0(this$static.uid, other.uid)) {
    return false;
  }
  return true;
}

function $read(this$static, json){
  'uid' in json.jsObject && $setUid(this$static, $get_0(json, 'uid').isString().value_0);
  'caption' in json.jsObject && !$get_0(json, 'caption').isNull() && $setCaption(this$static, $get_0(json, 'caption').isString().value_0);
  'description' in json.jsObject && !$get_0(json, 'description').isNull() && $setDescription(this$static, $get_0(json, 'description').isString().value_0);
  'backgroundColor' in json.jsObject && !$get_0(json, 'backgroundColor').isNull() && $setBackgroundColor(this$static, $get_0(json, 'backgroundColor').isString().value_0);
  'styleName' in json.jsObject && !$get_0(json, 'styleName').isNull() && $setStyleName(this$static, $get_0(json, 'styleName').isString().value_0);
  'identifier' in json.jsObject && !$get_0(json, 'identifier').isNull() && $setIdentifier(this$static, valueOf_1($longValue($get_0(json, 'identifier').isNumber().value_0)));
  'startDate' in json.jsObject && !$get_0(json, 'startDate').isNull() && $setStartDate_1(this$static, $longValue($get_0(json, 'startDate').isNumber().value_0));
  'endDate' in json.jsObject && !$get_0(json, 'endDate').isNull() && $setEndDate_1(this$static, $longValue($get_0(json, 'endDate').isNumber().value_0));
  'captionMode' in json.jsObject && !$get_0(json, 'captionMode').isNull() && $setCaptionMode(this$static, valueOf_5($get_0(json, 'captionMode').isString().value_0));
  'resizable' in json.jsObject && !$get_0(json, 'resizable').isNull() && $setResizable(this$static, $get_0(json, 'resizable').isBoolean().value_0);
  'movable' in json.jsObject && !$get_0(json, 'movable').isNull() && $setMovable(this$static, $get_0(json, 'movable').isBoolean().value_0);
  'showProgress' in json.jsObject && !$get_0(json, 'showProgress').isNull() && $setShowProgress(this$static, $get_0(json, 'showProgress').isBoolean().value_0);
  'progress' in json.jsObject && !$get_0(json, 'progress').isNull() && $setProgress_0(this$static, $get_0(json, 'progress').isNumber().value_0);
}

function $setBackgroundColor(this$static, backgroundColor){
  backgroundColor != null && $trim(backgroundColor).length != 0 && !$equals_0($trim(backgroundColor).substr(0, '#'.length), '#') && (backgroundColor = '#' + backgroundColor);
  this$static.backgroundColor = backgroundColor;
}

function $setCaption(this$static, caption){
  this$static.caption = caption;
}

function $setCaptionMode(this$static, captionMode){
  this$static.captionMode = captionMode;
}

function $setDescription(this$static, description){
  this$static.description = description;
}

function $setEndDate_1(this$static, endDate){
  this$static.endDate = endDate;
}

function $setIdentifier(this$static, identifier){
  this$static.identifier = identifier;
}

function $setMovable(this$static, movable){
  this$static.movable = movable;
}

function $setProgress_0(this$static, progress){
  this$static.progress = progress;
}

function $setResizable(this$static, resizable){
  this$static.resizable = resizable;
}

function $setShowProgress(this$static, showProgress){
  this$static.showProgress = showProgress;
}

function $setStartDate_1(this$static, startDate){
  this$static.startDate = startDate;
}

function $setStyleName(this$static, styleName){
  this$static.styleName = styleName;
}

function $setUid(this$static, uid){
  this$static.uid = uid;
}

function $toJson(this$static){
  var json;
  json = $toJsonReference(this$static);
  this$static.caption != null && $put(json, 'caption', new JSONString(this$static.caption));
  this$static.description != null && $put(json, 'description', new JSONString(this$static.description));
  this$static.backgroundColor != null && $put(json, 'backgroundColor', new JSONString(this$static.backgroundColor));
  this$static.styleName != null && $put(json, 'styleName', new JSONString(this$static.styleName));
  !!this$static.identifier && $put(json, 'identifier', new JSONNumber(toDouble_0(this$static.identifier.value_0)));
  $put(json, 'startDate', new JSONNumber(toDouble_0(this$static.startDate)));
  $put(json, 'endDate', new JSONNumber(toDouble_0(this$static.endDate)));
  !!this$static.captionMode && $put(json, 'captionMode', new JSONString($name(this$static.captionMode)));
  $put(json, 'resizable', ($clinit_JSONBoolean() , this$static.resizable?TRUE:FALSE));
  $put(json, 'movable', this$static.movable?TRUE:FALSE);
  $put(json, 'showProgress', this$static.showProgress?TRUE:FALSE);
  $put(json, 'progress', new JSONNumber(this$static.progress));
  return json;
}

function $toJsonReference(this$static){
  var json;
  json = new JSONObject;
  $put(json, 'uid', new JSONString(this$static.uid));
  return json;
}

function AbstractStep(){
  this.captionMode = ($clinit_AbstractStep$CaptionMode() , TEXT_0);
}

defineClass(46, 1, {3:1, 46:1});
_.equals_0 = function equals_17(obj){
  return $equals_2(this, obj);
}
;
_.hashCode_0 = function hashCode_20(){
  var result;
  result = 31 + (this.uid == null?0:getHashCode_1(this.uid));
  return result;
}
;
_.toJson = function toJson(){
  return $toJson(this);
}
;
_.backgroundColor = '#A8D9FF';
_.endDate = -1;
_.movable = true;
_.progress = 0;
_.resizable = true;
_.showProgress = false;
_.startDate = -1;
_.substep = false;
var Lorg_tltv_gantt_client_shared_AbstractStep_2_classLit = createForClass('org.tltv.gantt.client.shared', 'AbstractStep', 46, Ljava_lang_Object_2_classLit);
function $clinit_AbstractStep$CaptionMode(){
  $clinit_AbstractStep$CaptionMode = emptyMethod;
  TEXT_0 = new AbstractStep$CaptionMode('TEXT', 0);
  HTML = new AbstractStep$CaptionMode('HTML', 1);
}

function AbstractStep$CaptionMode(enum$name, enum$ordinal){
  Enum.call(this, enum$name, enum$ordinal);
}

function valueOf_5(name_0){
  $clinit_AbstractStep$CaptionMode();
  return valueOf(($clinit_AbstractStep$CaptionMode$Map() , $MAP), name_0);
}

function values_9(){
  $clinit_AbstractStep$CaptionMode();
  return stampJavaTypeInfo(getClassLiteralForArray(Lorg_tltv_gantt_client_shared_AbstractStep$CaptionMode_2_classLit, 1), $intern_0, 66, 0, [TEXT_0, HTML]);
}

defineClass(66, 5, {3:1, 6:1, 5:1, 66:1}, AbstractStep$CaptionMode);
var HTML, TEXT_0;
var Lorg_tltv_gantt_client_shared_AbstractStep$CaptionMode_2_classLit = createForEnum('org.tltv.gantt.client.shared', 'AbstractStep/CaptionMode', 66, Ljava_lang_Enum_2_classLit, values_9);
function $clinit_AbstractStep$CaptionMode$Map(){
  $clinit_AbstractStep$CaptionMode$Map = emptyMethod;
  $MAP = createValueOfMap(($clinit_AbstractStep$CaptionMode() , stampJavaTypeInfo(getClassLiteralForArray(Lorg_tltv_gantt_client_shared_AbstractStep$CaptionMode_2_classLit, 1), $intern_0, 66, 0, [TEXT_0, HTML])));
}

var $MAP;
function deferred(f, test_0){
  function nextTimeout(delayms){
    setTimeout(function(){
      test_0()?f && f():nextTimeout(10);
    }
    , delayms);
  }

  nextTimeout(0);
}

function getBoundingClientRectHeight(element){
  if (!element) {
    return 0;
  }
  if (element.getBoundingClientRect) {
    var rect = element.getBoundingClientRect();
    return rect.bottom - rect.top;
  }
   else {
    return element.offsetHeight;
  }
}

function getBoundingClientRectLeft(element){
  if (!element) {
    return 0;
  }
  if (element.getBoundingClientRect) {
    var rect = element.getBoundingClientRect();
    return rect.left;
  }
   else {
    return element.offsetLeft;
  }
}

function getBoundingClientRectRight(element){
  if (!element) {
    return 0;
  }
  if (element.getBoundingClientRect) {
    var rect = element.getBoundingClientRect();
    return rect.right;
  }
   else {
    return element.offsetLeft + element.offsetWidth;
  }
}

function getBoundingClientRectWidth(element){
  if (!element) {
    return 0;
  }
  if (element.getBoundingClientRect) {
    var rect = element.getBoundingClientRect();
    return rect.right - rect.left;
  }
   else {
    return element.offsetWidth;
  }
}

function getElementFromPoint(shadowRoot, clientX, clientY){
  var el;
  shadowRoot?(el = shadowRoot.elementFromPoint(clientX, clientY)):(el = $wnd.document.elementFromPoint(clientX, clientY));
  el != null && el.nodeType == 3 && (el = el.parentNode);
  return el;
}

function getEventTarget(event_0){
  if (event_0.path) {
    return event_0.path[0];
  }
  return event_0.target;
}

function getHost(node){
  if (node.host) {
    return node.host;
  }
  return null;
}

function getTouchOrMouseClientX(event_0){
  return event_0.type.indexOf('touch') != -1?(event_0.changedTouches[0].clientX || 0) | 0:(event_0.clientX || 0) | 0;
}

function getTouchOrMouseClientY(event_0){
  return event_0.type.indexOf('touch') != -1?(event_0.changedTouches[0].clientY || 0) | 0:(event_0.clientY || 0) | 0;
}

function getWeekNumber(d, firstDayOfWeek){
  var daysToTursday, weekNo, yearStart;
  d = new Date_0(d.jsdate.getFullYear() - 1900, d.jsdate.getMonth(), d.jsdate.getDate());
  firstDayOfWeek == 1?(daysToTursday = 4 - d.jsdate.getDay()):(daysToTursday = 4 - (d.jsdate.getDay() == 0?7:d.jsdate.getDay()));
  $setDate(d, d.jsdate.getDate() + daysToTursday);
  yearStart = new Date_0(d.jsdate.getFullYear() - 1900, 0, 1);
  weekNo = $wnd.Math.ceil((toDouble_0(sub_1(fromDouble_0(d.jsdate.getTime()), fromDouble_0(yearStart.jsdate.getTime()))) / $intern_10 + 1) / 7);
  return round_int(weekNo);
}

function toStringArray(array){
  var i_0, value_0;
  value_0 = initUnidimensionalArray(Ljava_lang_String_2_classLit, $intern_0, 2, array.jsArray.length, 6, 1);
  for (i_0 = 0; i_0 < array.jsArray.length; i_0++) {
    value_0[i_0] = $get(array, i_0).isString().value_0;
  }
  return value_0;
}

function whenReady_0(f, e){
  function nextTimeout(delayms){
    setTimeout(function(){
      isRegisteredElement(e) && e.readyAndConnected?f && f(e):nextTimeout(10);
    }
    , delayms);
  }

  function registered(){
    e?nextTimeout(0):f && f();
  }

  function done(){
    $wnd.HTMLImports.whenReady(registered);
  }

  function loadPolyfill(){
    var s = $doc.createElement('script');
    s.src = absoluteHref('webcomponentsjs/webcomponents-lite.min.js');
    s.onreadystatechange = s.onload = done;
    $doc.head.appendChild(s);
  }

  !$wnd.HTMLImports?($clinit_Polymer() , hasHtmlImports)?registered():loadPolyfill():done();
}

function whenReadyAndConnected(f, e){
  function nextTimeout(delayms){
    setTimeout(function(){
      e.readyAndConnected?f && f(e):nextTimeout(10);
    }
    , delayms);
  }

  function registered(){
    e?nextTimeout(0):f && f();
  }

  e.readyAndConnected?f && f(e):registered();
}

function $clinit_Resolution(){
  $clinit_Resolution = emptyMethod;
  Day = new Resolution('Day', 0);
  Week = new Resolution('Week', 1);
  Hour = new Resolution('Hour', 2);
}

function Resolution(enum$name, enum$ordinal){
  Enum.call(this, enum$name, enum$ordinal);
}

function valueOf_6(name_0){
  $clinit_Resolution();
  return valueOf(($clinit_Resolution$Map() , $MAP_0), name_0);
}

function values_10(){
  $clinit_Resolution();
  return stampJavaTypeInfo(getClassLiteralForArray(Lorg_tltv_gantt_client_shared_Resolution_2_classLit, 1), $intern_0, 57, 0, [Day, Week, Hour]);
}

defineClass(57, 5, {3:1, 6:1, 5:1, 57:1}, Resolution);
var Day, Hour, Week;
var Lorg_tltv_gantt_client_shared_Resolution_2_classLit = createForEnum('org.tltv.gantt.client.shared', 'Resolution', 57, Ljava_lang_Enum_2_classLit, values_10);
function $clinit_Resolution$Map(){
  $clinit_Resolution$Map = emptyMethod;
  $MAP_0 = createValueOfMap(($clinit_Resolution() , stampJavaTypeInfo(getClassLiteralForArray(Lorg_tltv_gantt_client_shared_Resolution_2_classLit, 1), $intern_0, 57, 0, [Day, Week, Hour])));
}

var $MAP_0;
function $addSubStep(this$static, subStep){
  var added, entry, obs$iterator, outerIter;
  subStep.substep = true;
  subStep.owner = this$static;
  added = $add_4(this$static.subSteps, subStep);
  if (added) {
    for (obs$iterator = (outerIter = (new AbstractMap$1(this$static.subStepObserver.map_0)).this$01.entrySet().iterator() , new AbstractMap$1$1(outerIter)); obs$iterator.val$outerIter2.hasNext_0();) {
      entry = castTo(obs$iterator.val$outerIter2.next_1(), 16);
      throwClassCastExceptionUnlessNull(entry.getKey());
      null.$_nullMethod();
    }
  }
  return added;
}

function $read_0(this$static, json){
  var i_0, object, subs;
  $read(this$static, json);
  if ('predecessor' in json.jsObject && !$get_0(json, 'predecessor').isNull()) {
    object = $get_0(json, 'predecessor').isObject();
    'uid' in object.jsObject && $setPredecessor(this$static, toStep(object.jsObject));
  }
  if ('subSteps' in json.jsObject && !!$get_0(json, 'subSteps')) {
    subs = $get_0(json, 'subSteps').isArray_0();
    for (i_0 = 0; i_0 < subs.jsArray.length; i_0++) {
      $addSubStep(this$static, toStep_0($get(subs, i_0).isObject().jsObject));
    }
  }
}

function $setPredecessor(this$static, predecessor){
  this$static.predecessor = predecessor;
}

function $toJson_0(this$static){
  var i_0, json, s, s$iterator, subs;
  json = $toJson(this$static);
  !!this$static.predecessor && $put(json, 'predecessor', $toJson_0(this$static.predecessor));
  subs = new JSONArray;
  i_0 = 0;
  for (s$iterator = ($clinit_Collections() , new Collections$UnmodifiableCollectionIterator($listIterator((new Collections$UnmodifiableList(this$static.subSteps)).coll, 0))); $hasNext(s$iterator.it);) {
    s = castTo($next_2(s$iterator.it), 67);
    $set(subs, i_0++, $toJson_1(s));
  }
  $put(json, 'subSteps', subs);
  return json;
}

function Step(){
  AbstractStep.call(this);
  this.subSteps = new LinkedList;
  this.subStepObserver = new HashSet;
}

function toStep(o){
  var s;
  s = new Step;
  $read_0(s, new JSONObject_0(o));
  return s;
}

defineClass(8, 46, {3:1, 46:1, 8:1}, Step);
_.toJson = function toJson_0(){
  return $toJson_0(this);
}
;
var Lorg_tltv_gantt_client_shared_Step_2_classLit = createForClass('org.tltv.gantt.client.shared', 'Step', 8, Lorg_tltv_gantt_client_shared_AbstractStep_2_classLit);
function $read_1(this$static, json){
  $read(this$static, json);
  'owner' in json.jsObject && !$get_0(json, 'owner').isNull() && $setOwner(this$static, toStep($get_0(json, 'owner').isObject().jsObject));
}

function $setOwner(this$static, owner){
  this$static.owner = owner;
}

function $toJson_1(this$static){
  var json;
  json = $toJson(this$static);
  $put(json, 'substep', ($clinit_JSONBoolean() , this$static.substep?TRUE:FALSE));
  !!this$static.owner && $put(json, 'owner', $toJsonReference(this$static.owner));
  return json;
}

function SubStep(){
  AbstractStep.call(this);
  this.substep = true;
}

function toStep_0(o){
  var s;
  s = new SubStep;
  $read_1(s, new JSONObject_0(o));
  return s;
}

defineClass(67, 46, {3:1, 46:1, 67:1}, SubStep);
_.toJson = function toJson_1(){
  return $toJson_1(this);
}
;
var Lorg_tltv_gantt_client_shared_SubStep_2_classLit = createForClass('org.tltv.gantt.client.shared', 'SubStep', 67, Lorg_tltv_gantt_client_shared_AbstractStep_2_classLit);
var C_classLit = createForPrimitive('char', 'C');
var I_classLit = createForPrimitive('int', 'I');
var J_classLit = createForPrimitive('long', 'J');
var $entry = ($clinit_Impl() , entry_0);
var gwtOnLoad = gwtOnLoad = gwtOnLoad_0;
addInitFunctions(init);
setGwtProperty('permProps', [[['locale', 'default'], ['user.agent', 'safari']]]);
$sendStats('moduleStartup', 'moduleEvalEnd');
gwtOnLoad(__gwtModuleFunction.__errFn, __gwtModuleFunction.__moduleName, __gwtModuleFunction.__moduleBase, __gwtModuleFunction.__softPermutationId,__gwtModuleFunction.__computePropValue);
$sendStats('moduleStartup', 'end');
$gwt && $gwt.permProps && __gwtModuleFunction.__moduleStartupDone($gwt.permProps);
//# sourceURL=org.tltv.gantt.GanttLib-0.js

