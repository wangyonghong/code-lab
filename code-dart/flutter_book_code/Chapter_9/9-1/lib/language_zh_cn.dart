import 'package:flutter/material.dart';

class LanguageZhCnLocalizations {

  bool isZh = false;

  LanguageZhCnLocalizations(this.isZh);

  static LanguageZhCnLocalizations of(BuildContext context){
    return Localizations.of<LanguageZhCnLocalizations>(context, LanguageZhCnLocalizations);
  }

  String get appBarContent{
    return isZh? "Flutter演示首页":"Flutter Demo Home Page";
  }

  String get counterDescribe{
    return isZh? "你点击按钮的次数为：":"You have clicked the button this many times:";
  }

}