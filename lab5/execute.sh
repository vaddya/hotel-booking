#!/bin/bash
sql="$PWD/sql"
data="$PWD/data"
scripts="$(ls sql)"
for script in ${scripts}
do
    sudo -u postgres psql \
         -U postgres \
         -d hotel_booking \
         -f ${sql}/${script} \
            > ${data}/${script%%.*}.txt
done