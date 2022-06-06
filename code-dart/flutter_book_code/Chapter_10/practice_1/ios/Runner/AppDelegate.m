#include "AppDelegate.h"
#include "GeneratedPluginRegistrant.h"

#import <Flutter/Flutter.h>

@implementation AppDelegate

NSArray* params;

- (BOOL)application:(UIApplication *)application
didFinishLaunchingWithOptions:(NSDictionary *)launchOptions {
    [GeneratedPluginRegistrant registerWithRegistry:self];
    // Override point for customization after application launch.
    FlutterViewController* controller = (FlutterViewController*)self.window.rootViewController;
    FlutterMethodChannel* batteryChannel = [FlutterMethodChannel
                                            methodChannelWithName:@"methodchannel.practice.com/calc"
                                            binaryMessenger:controller];
    
    [batteryChannel setMethodCallHandler:^(FlutterMethodCall* call, FlutterResult result) {
        if ([@"getCalcResult" isEqualToString:call.method]) {
            params = call.arguments;
            int calcResult = [self getCalcResult];
            
            if (calcResult == 0) {
                result([FlutterError errorWithCode:@"无法计算"
                                           message:@"无法计算相加结果"
                                           details:nil]);
            } else {
                result(@(calcResult));
            }
        } else {
            result(FlutterMethodNotImplemented);
        }
    }];
    return [super application:application didFinishLaunchingWithOptions:launchOptions];
}

- (int)getCalcResult{
    NSInteger num_1 = [[params objectAtIndex:0] integerValue];
    NSInteger num_2 = [[params objectAtIndex:1] integerValue];
    return num_1 + num_2;
}

@end
