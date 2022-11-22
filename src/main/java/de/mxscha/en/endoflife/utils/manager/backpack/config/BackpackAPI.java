package de.mxscha.en.endoflife.utils.manager.backpack.config;

import de.mxscha.en.endoflife.EndoflifeCore;
import de.mxscha.en.endoflife.utils.database.mysql.MySQL;

public class BackpackAPI {

    private MySQL mySQL;

    public void createTables() {
        this.mySQL = EndoflifeCore.getInstance().getMySQL();
        mySQL.update("CREATE TABLE IF NOT EXISTS backpacks (uuid VARCHAR(36) PRIMARY KEY, items VARCHAR(6555))");
    }
}
