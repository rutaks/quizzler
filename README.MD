# Quizzler

<img src="./previews/quizzler-round.png" width="140" height="140">

A Native Android Quiz App

## Structure

- `build.gradle` - root gradle config file
- `settings.gradle` - root gradle settings file
- `app` - our only project in this repo
- `app/build.gradle` - project gradle config file
- `app/src` - main project source directory
- `app/src/main` - main project flavour
- `app/src/main/AndroidManifest.xml` - manifest file
- `app/src/main/java` - java source directory
- `app/src/main/res` - resources directory

## Installation

- Clone The Project & Enter In the project

```
> git clone https://github.com/rutaks/quizzler.git
> cd quizzler/
```

- Open the project through Android Studio, Sync Files with the project.
- Run The Project Using the play icon on android studio

## Screenshots

<img src="./previews/1.jpg" width="220" height="440"> 
<img src="./previews/2.jpg" width="220" height="440"> 
<img src="./previews/3.jpg" width="220" height="440"> 
<img src="./previews/4.jpg" width="220" height="440">

## Building

It is recommended that you run Gradle with the `--daemon` option, as starting
up the tool from scratch often takes at least a few seconds. You can kill the
java process that it leaves running once you are done running your commands.

Tasks work much like Make targets, so you may concatenate them. Tasks are not
re-done if multiple targets in a single command require them. For example,
running `assemble install` will not compile the apk twice even though
`install` depends on `assemble`.

#### Clean

    gradle clean

#### Debug

This compiles a debugging apk in `build/outputs/apk/` signed with a debug key,
ready to be installed for testing purposes.

    gradle assembleDebug

You can also install it on your attached device:

    gradle installDebug

#### Release

This compiles an unsigned release (non-debugging) apk in `build/outputs/apk/`.
It's not signed, you must sign it before it can be installed by any users.

    gradle assembleRelease

#### Test

Were you to add automated java tests, you could configure them in your
`build.gradle` file and run them within gradle as well.

    gradle test

## Further reading

- [Build System Overview](https://developer.android.com/sdk/installing/studio-build.html)
- [Gradle Plugin User Guide](http://tools.android.com/tech-docs/new-build-system/user-guide)
- [Gradle Plugin Release Notes](http://tools.android.com/tech-docs/new-build-system)
