/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 */

import React, { Component } from 'react';
import {
  AppRegistry,
  StyleSheet,
  Text,
  View,
  TouchableHighlight,
  ToastAndroid,
} from 'react-native';
var { NativeModules } = require('react-native');
class CustomButton extends Component {
  render() {
    return (
      <TouchableHighlight
        style={styles.button}
        underlayColor="#a5a5a5"
        onPress={this.props.onPress}>
        <Text style={styles.buttonText}>{this.props.text}</Text>
      </TouchableHighlight>
    );
  }
}

export default class MCommunication extends Component {

  render() {
    return (
      <View>
        <Text style={styles.welcome}>
           React/JS与原生交互,数据通信实例
        </Text>
        <CustomButton
          text="点击跳转到Activity界面"
          onPress={()=>NativeModules.IntentModule.startActivityFromJS("com.ly.rntest.activity.communication.TwoActivity","我是从JS传过来的参数信息.456")}
        />
        <CustomButton
          text="点击跳转到Activity界面,并且等待数据返回..."
          onPress={()=>NativeModules.IntentModule.startActivityFromJSGetResult("com.ly.rntest.activity.communication.ThreeActivity",200,(msg) => {
                    ToastAndroid.show('JS界面:从Activity中传输过来的数据为:'+msg,ToastAndroid.SHORT);
                  },
                   (result) => {
                    ToastAndroid.show('JS界面:错误信息为:'+result,ToastAndroid.SHORT);
                  })}
        />
      </View>
    );
  }
}
const styles = StyleSheet.create({
  welcome: {
    fontSize: 20,
    textAlign: 'center',
    margin: 10,
  },
   button: {
    margin:5,
    padding: 15,
    borderBottomWidth: StyleSheet.hairlineWidth,
    borderBottomColor: '#cdcdcd',
  },
});

