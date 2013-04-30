
PATH_TO_SERVER='/home/ganesan/Softwares/jboss-6.0.0.Final'


echo "1. Cleanup folders and file inside the tmp folders"

rm -fr $PATH_TO_SERVER/server/default/tmp/*

echo "2. Cleanup folders and file inside the work folders"

rm -fr $PATH_TO_SERVER/server/default/work/*

echo "3. Cleanup folders and file inside the data folders"

rm -fr $PATH_TO_SERVER/server/default/data/*
