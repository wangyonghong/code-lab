import 'package:flutter/material.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: MyHomePage(title: 'Flutter Demo Home Page'),
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
  List<Genres> genres;
  List<Chip> selectedGenres;
  List<Chip> unSelectedGenres;

  @override
  void initState() {
    super.initState();
    genres = new List();
    genres.add(new Genres("disco", false));
    genres.add(new Genres("blues", false));
    genres.add(new Genres("rhythm", false));
    genres.add(new Genres("soul", false));
    genres.add(new Genres("rock", false));
    genres.add(new Genres("rock steady", false));
    genres.add(new Genres("reggae", false));
    genres.add(new Genres("rap", false));
    genres.add(new Genres("gospel", false));
    genres.add(new Genres("jazz", false));
    buildSelect();
  }

  void reset() {
    buildSelect();
    setState(() {});
  }

  buildSelect() {
    selectedGenres = new List();
    unSelectedGenres = new List();
    for (var i = 0; i < genres.length; i++) {
      if (genres[i].isSelect) {
        addSelect(genres[i]);
      } else {
        addUnselect(genres[i]);
      }
    }
    setState(() {});
  }

  List<Chip> addSelect(Genres genre) {
    selectedGenres.add(new Chip(
      padding: EdgeInsets.all(5.0),
      label: Text(genre.name),
      deleteIcon: Icon(Icons.delete),
      deleteIconColor: Colors.red,
      elevation: 10.0,
      onDeleted: () {
        genres[genres.indexOf(genre)].isSelect =
            !genres[genres.indexOf(genre)].isSelect;
        buildSelect();
        setState(() {});
      },
    ));
  }

  List<Chip> addUnselect(Genres genre) {
    unSelectedGenres.add(new Chip(
      padding: EdgeInsets.all(5.0),
      label: Text(genre.name),
      deleteIcon: Icon(Icons.add),
      elevation: 10.0,
      deleteIconColor: Colors.blue,
      onDeleted: () {
        genres[genres.indexOf(genre)].isSelect =
            !genres[genres.indexOf(genre)].isSelect;
        buildSelect();
        setState(() {});
      },
    ));
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.title),
      ),
      body: Column(
        children: <Widget>[
          Container(child: Text("已选流派"), alignment: Alignment.centerLeft),
          selectedGenres == null
              ? Text("已选流派")
              : Wrap(children: selectedGenres),
          Divider(),
          Container(child: Text("所有流派"), alignment: Alignment.centerLeft),
          unSelectedGenres == null
              ? Text("所有流派")
              : Wrap(children: unSelectedGenres)
        ],
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: reset,
        tooltip: "reset",
        child: Icon(Icons.refresh),
      ),
    );
  }
}

class Genres {
  String name;
  bool isSelect;

  Genres(this.name, this.isSelect);
}
