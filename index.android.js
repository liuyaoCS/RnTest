/**
 * Created by Administrator on 2016/12/12 0012.
 */
'use strict';
import React from 'react';
import {
    AppRegistry,
} from 'react-native';

import MBase from './js/base.js'
import MAnimated from './js/animated.js'
import MLayoutAnimation from './js/layoutAnimation.js'
import MCommunication from './js/communication.js'
import MNet from './js/net.js'

AppRegistry.registerComponent('MBase', () => MBase);
AppRegistry.registerComponent('MAnimated', () => MAnimated);
AppRegistry.registerComponent('MLayoutAnimation', () => MLayoutAnimation);
AppRegistry.registerComponent('MCommunication', () => MCommunication);
AppRegistry.registerComponent('MNet', () => MNet);