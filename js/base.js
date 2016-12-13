'use strict';

import React from 'react';
//noinspection JSUnresolvedVariable
import {
  AppRegistry,
  StyleSheet,
  Text,
  Image,
  Animated,
  TouchableHighlight,
  View,
  ToastAndroid
} from 'react-native';

export  default class MBase extends React.Component {
    constructor(props) {
        super(props);
        this.state = { myButtonOpacity: 1, };
    }

    _onPressButton() {
        console.log("You tapped the button!");
        ToastAndroid.show('You tapped the button!', ToastAndroid.LONG);
    }

    render() {
    return (
      <View style={styles.container}>
        <Text style={styles.hello}>Hello,react-native android World!</Text>
		<Image source={{uri: 'https://facebook.github.io/react/img/logo_og.png'}} style={styles.image} >
          <Text style={styles.inner}>i am inner text</Text>
        </Image>
        <TouchableHighlight onPress={this._onPressButton}>
          <Text>Button</Text>
        </TouchableHighlight>
        
      </View>
    )
  }

}

var styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
  },
  hello: {
    fontSize: 20,
    textAlign: 'center',
    margin: 10,
  },
  image:{
	width:100,
	height:100,
  },
  inner:{
    color:'#ffffff',
    fontSize:20
  }
});

