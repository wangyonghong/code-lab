import 'package:flutter/material.dart';
import 'package:intl/intl.dart';
import 'messages_all.dart';

class LanguageZhCnLocalizations {
  String localeName;

  LanguageZhCnLocalizations(Locale locale) : localeName = locale.toString();

  static Future<LanguageZhCnLocalizations> load(Locale locale) {
    final String name =
        locale.countryCode.isEmpty ? locale.languageCode : locale.toString();
    final String localeName = Intl.canonicalizedLocale(name);

    return initializeMessages(localeName).then((_) {
      Intl.defaultLocale = localeName;
      return new LanguageZhCnLocalizations(locale);
    });
  }

  static LanguageZhCnLocalizations of(BuildContext context) {
    return Localizations.of<LanguageZhCnLocalizations>(
        context, LanguageZhCnLocalizations);
  }

  String get title {
    return Intl.message(
      'Flutter APP',
      name: 'title',
      desc: 'Title for the Demo application',
    );
  }

  String get appBarContent {
    return Intl.message("Flutter Demo Home Page",
        name: 'appBarContent',
        desc: "Flutter Demo Home Page",
        locale: localeName);
  }

  String get counterDescribe {
    return Intl.message("You have clicked the button this many times:",
        name: 'counterDescribe',
        desc: "You have clicked the button this many times:",
        locale: localeName);
  }
}

class LanguageZhCnDelegate
    extends LocalizationsDelegate<LanguageZhCnLocalizations> {
  const LanguageZhCnDelegate();

  @override
  bool isSupported(Locale locale) {
    return ['en', 'zh'].contains(locale.languageCode);
  }

  @override
  Future<LanguageZhCnLocalizations> load(Locale locale) {
    return LanguageZhCnLocalizations.load(locale);
  }

  @override
  bool shouldReload(LanguageZhCnDelegate old) {
    return false;
  }
}
