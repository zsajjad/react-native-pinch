'use strict';

import { NativeModules } from 'react-native';

var RNPinch = {
	me: function (url, obj, callback){
		NativeModules.RNPinch.fetch(url, obj, (err, res) => {

		});
	}
};


module.exports =  RNPinch;