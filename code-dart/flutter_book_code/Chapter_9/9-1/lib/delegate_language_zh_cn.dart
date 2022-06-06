import 'package:flutter/material.dart';
import 'package:demo_global_support/language_zh_cn.dart';
import 'package:flutter/foundation.dart';

class LanguageZhCnLocalizationsDelegate extends LocalizationsDelegate<LanguageZhCnLocalizations>{

  const LanguageZhCnLocalizationsDelegate();

  @override
  bool isSupported(Locale locale) {
    return ['en','zh'].contains(locale.languageCode);
  }

  @override
  Future<LanguageZhCnLocalizations> load(Locale locale) {
    return SynchronousFuture<LanguageZhCnLocalizations>(
        LanguageZhCnLocalizations(locale.languageCode == "zh"));
  }

  @override
  bool shouldReload(LocalizationsDelegate<LanguageZhCnLocalizations> old) {
    return false;
  }

  static LanguageZhCnLocalizationsDelegate delegate = const LanguageZhCnLocalizationsDelegate();
}