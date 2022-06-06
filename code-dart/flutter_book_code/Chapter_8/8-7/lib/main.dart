import 'package:flutter/material.dart';
import 'package:flutter_nfc_reader/flutter_nfc_reader.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: "NFC Reader",
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: MyHomePage(title: "NFC Reader"),
    );
  }
}

class MyHomePage extends StatefulWidget {
  MyHomePage({Key key, this.title}) : super(key: key);

  final String title;

  @override
  _MyHomePageState createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  NfcData _nfcData;

  // 开始读取NFC信息
  Future<void> startNFC() async {
    NfcData response;

    setState(() {
      _nfcData = NfcData();
      _nfcData.status = NFCStatus.reading;
    });

    try {
      response = await FlutterNfcReader.read;
    } catch (e) {
      debugPrint("error when read NFC ${e.toString()}");
    }

    setState(() {
      _nfcData = response;
    });
  }

  // 停止读取NFC信息
  Future<void> stopNFC() async {
    NfcData response;
    try {
      response = await FlutterNfcReader.stop;
    } catch (e) {
      debugPrint("error when read NFC ${e.toString()}");
      response.status = NFCStatus.error;
    }
    setState(() {
      _nfcData = response;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.title),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            RaisedButton(child: Text("开始读取NFC信息"), onPressed: startNFC),
            RaisedButton(child: Text("停止读取NFC信息"), onPressed: stopNFC),
            _nfcData == null ? Text("请点击开始读取NFC信息按钮") : Text("状态：${_nfcData.status.toString()}\nID：${_nfcData.id}\n内容：${_nfcData.content}")
          ],
        ),
      ),
    );
  }
}
