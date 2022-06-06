import 'package:flutter/material.dart';
import 'package:flutter_localizations/flutter_localizations.dart';
import 'delegate_language_zh_cn.dart';
import 'language_zh_cn.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {

    return MaterialApp(
      onGenerateTitle: (context){
        return LanguageZhCnLocalizations.of(context).appBarContent;
      },
      localizationsDelegates: [
        GlobalMaterialLocalizations.delegate,
        GlobalWidgetsLocalizations.delegate,

        LanguageZhCnLocalizationsDelegate.delegate
      ],
      supportedLocales: [const Locale('en', 'US'), const Locale('zh', 'CN')],
      localeListResolutionCallback: (currentLocale, supportedLocales){
        debugPrint("CurrentLocale: $currentLocale, SupportedLocales: $supportedLocales");
      },

      title: 'Flutter Demo',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: MyHomePage(),
    );
  }
}

class MyHomePage extends StatefulWidget {

  @override
  _MyHomePageState createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  int _counter = 0;

  void _incrementCounter() {
    setState(() {
      _counter++;
    });
  }

  @override
  Widget build(BuildContext context) {

    // 获取当前区域和语言
    Locale currentLocale = Localizations.localeOf(context);
    debugPrint("Country code: ${currentLocale.countryCode}   Language Code: ${currentLocale.languageCode}");

    return Scaffold(
      appBar: AppBar(
        title: Text(LanguageZhCnLocalizations.of(context).appBarContent),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            Text(
              LanguageZhCnLocalizations.of(context).counterDescribe,
            ),
            Text(
              '$_counter',
              style: Theme.of(context).textTheme.display1,
            ),
          ],
        ),
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: _incrementCounter,
        tooltip: 'Increment',
        child: Icon(Icons.add),
      ),
    );
  }
}
