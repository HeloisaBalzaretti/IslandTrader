1. Ensure you are in the root project directory. This directory contains this README, the src and doc directories.

2. Run the following command to compile the Java source code and place the resulting compiled classes into the
   bin directory:

     javac -d bin -cp src islandtrader/Main.java

3. To start IslandTrader with a graphical user interface run:

     java -cp bin src.islandtrader.Main

   To start IslandTrader with a command line interface run:

     java -cp bin src.islandtrader.Main cmd
4-You may experience some of the fonts in labels are not showing properly, I did not enforce display configs in the project.

Ensure Java 14 is installed on the machine
Open the zip folder in a terminal window
Run "java -jar mba213_islandtrader.jar"
