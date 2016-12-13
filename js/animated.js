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

export default class MAnimated extends React.Component {
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

