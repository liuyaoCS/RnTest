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
} from 'react-native';
import ToastAndroid from './js/MyToastAndroid';
class HelloWorld extends React.Component {
    constructor(props) {
        super(props);
        this.state = { myButtonOpacity: 1, };
    }

    _onPressButton() {
        console.log("You tapped the button!");
        alert("clicked");
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
    componentDidMount(){
      // this.setTimeout(
      //     () => { console.log('I do not leak!'); },
      //     500
      // );
        ToastAndroid.toast('w',0);
  }

}
class HelloWorld1 extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            bounceValue: new Animated.Value(0.5),
        };
    }
    componentDidMount() {
        this.state.bounceValue.setValue(1);     // Start large
        Animated.spring(                          // Base: spring, decay, timing
            this.state.bounceValue,                 // Animate `bounceValue`
            {
                toValue: 0.9,                         // Animate to smaller size
                friction: 1,                          // Bouncier spring
            }
        ).start();                                // Start the animation
    }
    render() {
    return (
      <View style={styles.container}>
        <Text style={styles.hello}>Hello,react-native animatoin!</Text>
		<Animated.Image                         // Base: Image, Text, View
            source={{uri: 'https://facebook.github.io/react/img/logo_og.png'}}
            style={{
                flex: 1,
                transform: [                        // `transform` is an ordered array
                    {scale: this.state.bounceValue},  // Map `bounceValue` to `scale`
                ]
            }}
        />
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

AppRegistry.registerComponent('HelloWorld', () => HelloWorld);
AppRegistry.registerComponent('HelloWorld1', () => HelloWorld1);