module Gen where

build :: Int -> String  
build x = "  val f"++show x++" = new FIFO1"++
  "(\"F"++show x++"\","++show x++",None)\n  connect(f"++show x++",n"++show
  x++",\"F"++show x++"-i\")\n  val n"++show (x+1)++
  "  = new  Node[CC](\"n"++show (x+1)++"\") with NodeCC\n  connect(f"++show
  x++",n"++show (x+1)++",\"F"++show x++"-o\")\n"

builds = concat . map build
  
putBuild = putStrLn . builds


release :: Int -> String
release x = "  n"++show x++" ! Release(\"\")\n  f"++show x++" ! Release(\"\")\n"

releases = concat . map release

putRelease = putStrLn . releases



start :: Int -> String
start x = "  n"++show x++".start\n  f"++show x++".start\n"

starts = concat . map start

putStart = putStrLn . starts



kill :: Int -> String
kill x = "    n"++show x++" ! Kill()\n    f"++show x++" ! Kill()\n"

kills = concat . map kill

putKill = putStrLn . kills