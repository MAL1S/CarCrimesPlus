package com.example.carcrimesplus

import android.content.Context
import androidx.constraintlayout.widget.Group
import com.example.carcrimesplus.rv.content.items.ImageContent
import com.example.carcrimesplus.rv.content.items.TextContent
import com.example.carcrimesplus.rv.navdrawer.items.GroupNavItem
import com.example.carcrimesplus.rv.navdrawer.items.NavItem
import com.example.carcrimesplus.rv.navdrawer.items.ScreenNavItem
import com.example.carcrimesplus.rv.navdrawer.items.SubScreenNavItem
import com.example.carcrimesplus.utils.AssetReader

class ContentInflater(context: Context) {

    private val assetReader = AssetReader(context)

    fun inflateDescription(isSelected: Boolean = false): ScreenNavItem {
        return ScreenNavItem(
            name = "О программе",
            selected = isSelected,
            content = listOf(
                TextContent(assetReader.read("description.txt"))
            )
        )
    }

    fun inflateWeapon(): NavItem {
        return GroupNavItem(
            name = "Оружие",
            items = listOf(
                inflateWeaponDefinitions(),
                inflateWeaponExample()
            )
        )
    }

    fun inflateCriminalLaw(): NavItem {
        return ScreenNavItem(
            name = "Уголовное законодательство Российской Федерации",
            selected = false,
            content = listOf(
                TextContent(assetReader.read("criminal_law.txt"))
            )
        )
    }

    fun inflateCriminalCharacteristics(): NavItem {
        return GroupNavItem(
            name = "Криминалистическая характеристика",
            items = listOf(
                inflateCriminalSubject(),
                inflateCriminalMethod(),
                inflateCriminalPersonality()
            )
        )
    }

    fun inflateTrialPractice(): NavItem {
        return ScreenNavItem(
            name = "Судебная практика",
            selected = false,
            content = listOf(
                TextContent(assetReader.read("trial_practice.txt"))
            )
        )
    }

    fun inflateCriminalHelpInfo(): NavItem {
        return GroupNavItem(
            name = "Справочная информация",
            items = listOf(
                inflateNormActs(),
                inflateLiteratureList()
            )
        )
    }

    fun inflateAuthors(): NavItem {
        return ScreenNavItem(
            name = "Авторы",
            selected = false,
            content = listOf(
                TextContent(assetReader.read("authors.txt"))
            )
        )
    }

    private fun inflateWeaponDefinitions(): SubScreenNavItem {
        return SubScreenNavItem(
            name = "Основные понятия",
            selected = false,
            content = listOf(
                TextContent(assetReader.read("weapon_definitions.txt"))
            )
        )
    }

    private fun inflateWeaponExample(): SubScreenNavItem {
        return SubScreenNavItem(
            name = "Наглядный пример",
            selected = false,
            content = listOf(
                TextContent(assetReader.read("weapon_example_1.txt")),
                ImageContent(R.drawable.img_weapon_example_1),
                ImageContent(R.drawable.img_weapon_example_2),
                ImageContent(R.drawable.img_weapon_example_3),
                ImageContent(R.drawable.img_weapon_example_4),
                ImageContent(R.drawable.img_weapon_example_5),
            )
        )
    }

    private fun inflateCriminalSubject(): SubScreenNavItem {
        return SubScreenNavItem(
            name = "Предмет преступного посягательства",
            selected = false,
            content = listOf(
                TextContent(assetReader.read("criminal_subject.txt"))
            )
        )
    }

    private fun inflateCriminalMethod(): SubScreenNavItem {
        return SubScreenNavItem(
            name = "Способ совершения преступления",
            selected = false,
            content = listOf(
                TextContent(assetReader.read("criminal_method.txt"))
            )
        )
    }

    private fun inflateCriminalPersonality(): SubScreenNavItem {
        return SubScreenNavItem(
            name = "Личность преступника",
            selected = false,
            content = listOf(
                TextContent(assetReader.read("criminal_personality.txt"))
            )
        )
    }

    private fun inflateNormActs(): SubScreenNavItem {
        return SubScreenNavItem(
            name = "Нормативные правовые акты",
            selected = false,
            content = listOf(
                TextContent(assetReader.read("norm_acts.txt"))
            )
        )
    }

    private fun inflateLiteratureList(): SubScreenNavItem {
        return SubScreenNavItem(
            name = "Рекомендуемая к изучению литература",
            selected = false,
            content = listOf(
                TextContent(assetReader.read("literature_list.txt"))
            )
        )
    }
}