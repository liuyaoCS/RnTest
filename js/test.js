'use strict';

import React, { Component } from 'react';
import {
  AppRegistry,
  StyleSheet,
  Text,
  View,
  Image,
  DrawerLayoutAndroid,
  ListView, ScrollView
} from 'react-native';

export default class MTest extends Component {

  // 构造
    constructor(props) {
      super(props);
      // 初始状态
        this.state = {};
        this.a=new Array();
        this.a['id']=0;
        this.a['name']="ly";

        this.b=new Array();
        this.ok=true;

        this.json=[
            {name:'ly',index:0},
            {name:'ll',index:1}
        ];

    }

    render() {
      return (
          <View alignItems="center" >
              {
                  this.json.map(
                      (item) => <Text key={item.index}>{item.name}</Text>
                  )
              }
          </View>


       );
    }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#F5FCFF',
  },
  welcome: {
    fontSize: 20,
    textAlign: 'center',
    margin: 10,
  },
   elements:{
    alignItems: "flex-start",
    color:"#000000",
  },
  instructions: {
    textAlign: 'center',
    color: '#333333',
    marginBottom: 5,
  },
  base: {
    width: 38,
    height: 38,
  },
  background: {
    backgroundColor: '#ff0000',
  },
  active: {
    borderWidth: 2,
    borderColor: '#00ffff',
  },
});

