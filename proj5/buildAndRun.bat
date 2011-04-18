@echo off

set jasFile=LogoJVM1.j

echo $ Rebuilding compiler
call make 
@echo off
echo $
echo $ Compiling "%1" against compiler
call run < %1 > %jasFile%
@echo off
echo $
echo $ Compiling to JVM and running program
call runjas %jasFile%

@echo on
