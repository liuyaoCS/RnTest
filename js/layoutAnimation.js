/**
 * Created by Administrator on 2016/12/12 0012.
 */
'use strict';
import React from 'react';
import {
    AppRegistry,
    StyleSheet,
    Text,
    Image,
    View,
    TouchableHighlight,
    TouchableOpacity,
    LayoutAnimation,
    Platform,
    UIManager
} from 'react-native';

export default class MLayoutAnimation extends React.Component {

    constructor() {
        super();

        this.state = {
            index: 0,
        }

        if (Platform.OS === 'android') {
            UIManager.setLayoutAnimationEnabledExperimental(true);
        }
    }

    onPress(index) {

        // Uncomment to animate the next state change.
        // Or use a Custom Layout Animation
        // LayoutAnimation.configureNext(CustomLayoutAnimation);
        LayoutAnimation.configureNext(LayoutAnimation.Presets.spring);

        this.setState({index: index});
    }

    renderButton(index) {
        return (
            <TouchableOpacity style={styles.button} onPress={() => this.onPress(index)}>
                <Text>{index}</Text>
            </TouchableOpacity>
        );
    }

    renderCircle(key) {
        var size = 50;
        return (
            <View key={key} style={{width: size, height: size, borderRadius: size / 2.0, backgroundColor: 'sandybrown', margin: 20}}/>
        );
    }

    render() {

        var leftStyle = this.state.index === 0 ? {flex: 1} : {width: 20};
        var middleStyle = this.state.index === 2 ? {width: 20} : {flex: 1};
        var rightStyle = {flex: 1};

        var whiteHeight = this.state.index * 40;

        var circles = [];
        for (var i = 0; i < (5 + this.state.index); i++) {
            circles.push(this.renderCircle(i));
        }

        return (
            <View style={styles.container}>
                <View style={styles.topButtons}>
                    {this.renderButton(0)}
                    {this.renderButton(1)}
                    {this.renderButton(2)}
                </View>
                <View style={styles.content}>
                    <View style={{flexDirection: 'row', height: 100}}>
                        <View style={[leftStyle, {backgroundColor: 'firebrick'}]}/>
                        <View style={[middleStyle, {backgroundColor: 'seagreen'}]}/>
                        <View style={[rightStyle, {backgroundColor: 'steelblue'}]}/>
                    </View>
                    <View style={{height: whiteHeight, justifyContent: 'center', alignItems: 'center', overflow: 'hidden'}} removeClippedSubviews={true}>
                        <View>
                            <Text>Stuff Goes Here</Text>
                        </View>
                    </View>
                    <View style={styles.circleContainer}>
                        {circles}
                    </View>
                </View>
            </View>
        );
    }
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        flexDirection: 'column',
        justifyContent: 'center',
        alignItems: 'center',
        backgroundColor: '#F5FCFF',
    },
    topButtons: {
        marginTop: 22,
        flexDirection: 'row',
        alignItems: 'center',
        justifyContent: 'center',
        backgroundColor: 'lightblue',
    },
    button: {
        flex: 1,
        height: 60,
        margin: 8,
        alignItems: 'center',
        justifyContent: 'center',
        backgroundColor: 'white',
    },
    content: {
        flex: 1,
        alignSelf:'stretch'
    },
    circleContainer: {
        flex: 1,
        flexDirection: 'row',
        flexWrap: 'wrap',
        justifyContent: 'center',
        alignItems: 'center',
        padding: 30,
    },
});
